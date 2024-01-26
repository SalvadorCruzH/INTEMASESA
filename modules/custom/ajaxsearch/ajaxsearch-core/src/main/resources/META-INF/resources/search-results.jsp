<%@ include file="init.jsp" %>

<liferay-portlet:resourceURL var="getDataActionURL" id="/default/get-data" copyCurrentRenderParameters="false"/>

<jsp:include page="${resultsPage}" flush="true" />

<%@ include file="pagination.jspf" %>

<template id="default-empty-results">
    <h6><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.results.no-results" /></h6>
</template>

<script>
    var ajaxSearchFeature = (function () {
        /** VARS **/
        var _emptyValue = "~";
        var _emptyResultsTemplateId="default-empty-results";
        var _templateId = "as-template";
        var _contentDisplayId = "as-wrapper";
        var _totalItemsId = "as-total-items";
        var _totalItemsTplId = "as-total-items-template";
        var _loadNumber = 0;

        var _showEmptyMsg = function(show){
            if (show){
                var template=$("#"+_emptyResultsTemplateId);
                var itemPlaced=$("#"+_contentDisplayId);
                var emptyMessage = $(template.html());
                emptyMessage.addClass("as-empty-message");
                emptyMessage.appendTo(itemPlaced);
                $(".results-pagination-wrapper").hide();
                $(".m-results-wrapper").hide();
            } else {
                $(".as-empty-message").remove();
                $(".results-pagination-wrapper").show();
                $(".m-results-wrapper").show();
            }
        }

        var _setCurrentPage = function(numPage){
            $("#asCurrentPage").val(numPage)

            var options = $(".results-pagination-select-container .results-pagination-select option");
            for (var i = 0; i < options.length; i++) {
                var value = options[i].value;
                if (value == numPage) {
                    $(options[i]).attr("selected", "selected");
                }
            }
        }

        var _getCurrentPage = function(reset) {
            var curPage = 1;
            if (!reset){
                curPage = $("#asCurrentPage").val();
            } else {
                $("#asCurrentPage").val(curPage);
            }
            return curPage;
        }

        /** EXTRACT FIELDS **/
        var _getData = function(reset){
            var dataFromFields = {"currentPage": _getCurrentPage(reset)};
            var ajaxSearchFields = $('[data-as-id]');
            $.each(ajaxSearchFields, function(item, index){
            	if ($(this).is("select")){
                    _addDataFromFields(dataFromFields,$(this).data("as-id"), $(this).data("as-blank"), $(this).val());
                } else if ($(this).is(":not(input)")){
                    var theVal = $(this).find("input[type=hidden]").first().val();
                    _addDataFromFields(dataFromFields,$(this).data("as-id"), $(this).data("as-blank"), theVal);
                } else {
                    if ($(this).is("input[type='radio']") || $(this).is("input[type='checkbox']")) {
                        if ($(this).is(":checked")) {
                            _addDataFromFields(dataFromFields, $(this).data("as-id"),
                                               $(this).data("as-blank"), $(this).val());
                        }
                    } else {
                        _addDataFromFields(dataFromFields,$(this).data("as-id"),$(this).data("as-blank"), $(this).val());
                    }
                }
            });
            return dataFromFields;
        }

        var _addDataFromFields = function(dataFromFields, theKey, blankVal, theVal){
            if (theKey in dataFromFields) {
                dataFromFields[theKey] += ","+_addDataValueFromFields(blankVal, theVal);
            } else {
                dataFromFields[theKey] = _addDataValueFromFields(blankVal, theVal);
            }
        }

        var _addDataValueFromFields = function(blankVal, theVal){
            var returnedVal;
            if (typeof blankVal == "undefined" || !blankVal){
                returnedVal = theVal;
            } else {
                returnedVal = _emptyValue;
            }
            return returnedVal;
        }

        /** SEARCH FEATURE **/
        var _reloadView = function(resourceData, doScroll, reloadPagination){
            if (resourceData.status == 0) {
                var payload = resourceData.payload;
                var arrayListResult = payload.content;
                _clearView();

                // Call custom _predrawAll
                if(typeof ajaxSearchGlobalConfig != "undefined" && ajaxSearchGlobalConfig != null
                    && ajaxSearchGlobalConfig._predrawAll != null
                    && typeof ajaxSearchGlobalConfig._predrawAll == "function") {
                    ajaxSearchGlobalConfig._predrawAll(payload);
                }

                _reloadTotalItems(payload.totalItems, payload.itemsPage);

                $.each(arrayListResult, function(item, index){
                    _drawItem(this);

                    // Call custom _postDrawItem
                    if(typeof ajaxSearchGlobalConfig != "undefined" && ajaxSearchGlobalConfig != null
                        && ajaxSearchGlobalConfig._postdrawItem != null
                        && typeof ajaxSearchGlobalConfig._postdrawItem == "function") {
                        ajaxSearchGlobalConfig._postdrawItem(this);
                    }
                });

                if (reloadPagination) {
                    _reloadPagination(payload.currentPage, parseInt(payload.totalItems), payload.itemsPage);
                }

                // Call custom _postdrawAll
                if(typeof ajaxSearchGlobalConfig != "undefined" && ajaxSearchGlobalConfig != null
                    && ajaxSearchGlobalConfig._postdrawAll != null
                    && typeof ajaxSearchGlobalConfig._postdrawAll == "function") {
                    ajaxSearchGlobalConfig._postdrawAll(payload);
                }

                // Header fixes
                $("#"+_totalItemsId+" .m-ajaxresults-header-element").toggleClass("d-none", (payload.totalItems !== "1"));
                $("#"+_totalItemsId+" .m-ajaxresults-header-elements").toggleClass("d-none", (payload.totalItems == "1"));
                $("#"+_totalItemsId+" .m-ajaxresults-header-page").toggleClass("d-none", (payload.totalPages === 0));

                // Fix accesibility
                if (typeof accFeature != "undefined"){
                    accFeature.init();
                }
                
                // Fix scroll
                if (typeof doScroll !== "undefined" && doScroll) {
                	$('html, body').animate({
              			scrollTop: $(".m-searchAjax--results").offset().top
              		}, 500);
                }
            }
        }

        var _disableStoreStatus = function() {
            return (typeof ajaxSearchGlobalConfig != "undefined"
                && ajaxSearchGlobalConfig != null
                && ajaxSearchGlobalConfig._disableStoreStatus != null
                && typeof ajaxSearchGlobalConfig._disableStoreStatus == "boolean"
                && ajaxSearchGlobalConfig._disableStoreStatus);
        }

        var _storeStatus = function(curParams) {
            var cleanHistoryParams = {};
            Object.keys(curParams).forEach(function(key) {
                if ((key === "currentPage" && curParams[key] != 1)
                    || (key !== "currentPage" && curParams[key])){
                    cleanHistoryParams[key]=curParams[key];
                }
            })
            if (window.history && window.history.replaceState) {
                if (Object.keys(cleanHistoryParams).length > 0) {
                    history.replaceState(null, null, "?" + $.param(cleanHistoryParams).replaceAll("="+_emptyValue, ""));
                } else {
                    history.replaceState(null, null, "?");
                }
            }
        }

        var _fixFormData = function(formData){
            var namespacedFormData = {};
            Object.keys(formData).forEach(function(key) {
                namespacedFormData["${renderResponse.namespace}"+key]=formData[key];
            })
            return namespacedFormData;
        }

        var _loadingOn = function(){
            if ($(".ajaxsearch-loading-animation").length>0) {
                $(".ajaxsearch-input-button").hide();
                $(".loading-animation").show();
            }
            if ($(".em-loading-overlay").length>0) {
                //remove class hide
                $(".em-loading-overlay").removeClass("hide");
            }
        }

        var _notFirstLoad;
        var _loadingOff = function(){
            if ($(".ajaxsearch-loading-animation").length>0){
                $(".ajaxsearch-input-button").show();
                $(".ajaxsearch-loading-animation").hide();
            }
            if ($(".em-loading-overlay").length>0) {
                //addClass hide
                $(".em-loading-overlay").addClass("hide");
            }

        }

        var _clearView = function(){
            $("#"+_contentDisplayId).empty();
        }

        var _drawItem = function(jsonItem){
            var template=$("#"+_templateId);
            var itemPlaced=$("#"+_contentDisplayId);
            var templateHtmlString = template.html();
            for(key in jsonItem) {
                while (true) {
                    templateHtmlString = templateHtmlString.replace("#"+key+"#", jsonItem[key]);
                    if (templateHtmlString.indexOf("#"+key+"#")==-1){
                        break;
                    }
                }
            }

            var item = $(templateHtmlString);

            // Call custom _preAppendItem
            if(typeof ajaxSearchGlobalConfig != "undefined" && ajaxSearchGlobalConfig != null
                && ajaxSearchGlobalConfig._preAppendItem != null
                && typeof ajaxSearchGlobalConfig._preAppendItem == "function") {
                let newItem = ajaxSearchGlobalConfig._preAppendItem(item, jsonItem);
                item = newItem != null ? newItem : item;
            }
            item.appendTo(itemPlaced);
        }

        var _ajaxSearch = function(formData, doScroll, reloadPagination) {
            _loadingOn();
            AUI().io.request('${getDataActionURL}',{
                dataType: 'json',
                method: 'POST',
                data: _fixFormData(formData),
                cache: false,
                on: {
                    success: function() {
                        var data=this.get('responseData');
                        _reloadView(data, doScroll, reloadPagination);
                        if (!_disableStoreStatus()){
                            _storeStatus(formData);
                        }
                        _loadingOff();
                        _loadNumber++;
                    },
                    error:function(data, textStatus, XMLHttpRequest) {
                        alert("Error");
                    }
                }
            });
        }

        var _doSearch = function(reset, doScroll, reloadPagination) {
            if (typeof reset === "undefined") {
                _ajaxSearch(_getData(true), false, true);
            } else {
                _ajaxSearch(_getData(reset), doScroll, reloadPagination);
            }
        }

        /** TOTAL ITEMS **/
        var _reloadTotalItems = function(totalItems, itemsPage) {
            $("#"+_totalItemsId).empty();
            if(totalItems != "") {
                _drawTotalItem(totalItems, itemsPage);
            }
            _showEmptyMsg(totalItems=="0")
        }

        var _drawTotalItem = function(totalItems, itemsPage){
            var template=$("#"+_totalItemsTplId);
            var itemPlaced=$("#"+_totalItemsId);
            var templateHtmlString = template.html();

            templateHtmlString = templateHtmlString.replaceAll("#total-items#", totalItems);
            templateHtmlString = templateHtmlString.replaceAll("#items-page#", itemsPage);

            itemPlaced.append(templateHtmlString);
        }

        /** SELECT PAGINATION **/
        var _appendSelectToPagination = function(curTotal) {
            $(".results-pagination-select-container").remove();
            var paginationContainer = $(".results-pagination-wrapper");
            var newSelect = $("<select class='results-pagination-select'></select>");
            var newSelectContainer = $("<div class='results-pagination-select-container'></div>");
            for (var i = 1; i <= curTotal; i++) {
                var option = $("<option value='"+i+"'><liferay-ui:message key='es.emasesa.intranet.ajaxsearch.pagination.page' /> "+i+" <liferay-ui:message key='es.emasesa.intranet.ajaxsearch.pagination.of' /> "+curTotal+"</option>");
                newSelect.append(option);
            }
            newSelect.on("change", function(){
                _paginationFeature.go($(this).val());
            });
            newSelectContainer.append(newSelect);
            paginationContainer.prepend(newSelectContainer);
        }

        /** PAGINATION **/
        var _paginationFeature = (function () {

            /** PAGINATION SOURCE **/
            var _curSource = [];
            var _getSource = function(maxPage) {
                var result = [];
                var maxInt = maxPage+1;
                if (maxPage == _curSource.length) {
                    result = _curSource;
                } else {
                    if (0<maxInt && maxInt < 10000) {
                        for (var i = 1; i < maxInt; i++) {
                            result.push(i);
                        }
                        _curSource = result;
                    }
                }
                return result;
            };

            var _getPagContainer = function() {
                return $('#emasesa_pagination');
            }

            var _goToPage = function(pageNum, callback) {
                _getPagContainer().pagination('go', pageNum);
            };

            var _resetPagination = function(curPage, curTotal, pageSize){
                var options = {
                    dataSource: _getSource(curTotal),
                    pageSize: pageSize,
                    pageNumber: curPage,
                    resetPageNumberOnInit: true,
                    hideOnlyOnePage: false,
                    autoHidePrevious: false,
                    autoHideNext: false,
                    autoHideFirst: false,
                    autoHideLast: false,
                    showPageNumbers: false,
                    nextText:"<liferay-ui:message key='es.emasesa.intranet.ajaxsearch.pagination.next' /><i class='fa-solid fa-chevron-right fa-xs'></i>",
                    prevText:"<i class='fa-solid fa-chevron-left fa-xs'></i><liferay-ui:message key='es.emasesa.intranet.ajaxsearch.pagination.prev' />",

                    callback: function (response, pagination) {
                        var dataHtml = '<ul>';
                        $.each(response, function (index, item) {
                            dataHtml += '<li>' + item + '</li>';
                        });
                        dataHtml += '</ul>';
                        _getPagContainer().prev().html(dataHtml);
                        _setCurrentPage(pagination.pageNumber);
                        _doSearch(false, false, false);
                    },
                    afterRender: function(){
                        _appendSelectToPagination(Math.ceil(curTotal/pageSize))
                    }
                };

                _getPagContainer().pagination(options);

            }

            // Page number (1..n), curTotal=totalItems (0..n), pageSize (1..n)
            var _init = function (curPage, curTotal, pageSize) {
                _resetPagination(curPage, curTotal, pageSize);
            };

            return {
                init: _init,
                go: _goToPage
            };
        })();

        // TODO CHECK
        var _fixPaginationAccesible = function(){
            var allPages = $('.results-pagination li');
            allPages.each(function() {
                var liElement = $(this);
                var curPage = $(this).data("lp");
                var aElement = $(liElement.children("a").first());

                if (liElement.hasClass("disabled")){
                    aElement.attr("aria-hidden", 'true');
                    aElement.attr("tabindex", '-1');
                }

                if (liElement.hasClass("next")){
                    aElement.attr("aria-label", '<liferay-ui:message key="es.camara.intranet.main.pagination.next"/>');
                } else if (liElement.hasClass("prev")) {
                    aElement.attr("aria-label", '<liferay-ui:message key="es.camara.intranet.main.pagination.prev"/>');
                } else if (liElement.hasClass("last")) {
                    aElement.attr("aria-label", '<liferay-ui:message key="es.camara.intranet.main.pagination.last"/>');
                } else if (liElement.hasClass("first")) {
                    aElement.attr("aria-label", '<liferay-ui:message key="es.camara.intranet.main.pagination.first"/>');
                } else if (liElement.hasClass("active")) {
                    aElement.attr("aria-current", "true");
                    aElement.attr("aria-label", '<liferay-ui:message key="es.camara.intranet.main.pagination.goto-active"/> '+curPage);
                } else {
                    aElement.removeAttr("aria-current");
                    aElement.attr("aria-label", '<liferay-ui:message key="es.camara.intranet.main.pagination.goto"/> '+curPage);
                }
                aElement.attr("title", aElement.attr("aria-label"));
            });
        }

        var _reloadPagination = function(curPage, curTotal, pageSize) {
            _paginationFeature.init(curPage, curTotal, pageSize);
            $("#"+_totalItemsId).focus();
        }

        var _init = function () {
            _doSearch(false, false, true);
        };

        return {
            init: _init,
            doSearch: _doSearch
        };
    })();
    AUI().use('aui-base','aui-io-request', function(){
        ajaxSearchFeature.init();
    });
</script>
