<%@ include file="init.jsp" %>

<div class="m-searchAjax m-searchAjax--results">
    <div class="c-messages">
        <div id="as-total-items" class="d-none">
            <!-- EMPTY BY DEFAULT -->
        </div>

        <div class="m-results-wrapper c-publisher c-messages-left loading">
            <div id="as-wrapper" class="c-publisher__list m-dFlexWrap m-listBaseNoStyles c-recent-messages-list">
                <!-- -->
            </div>
            <div id="wrapper-loading" class="wrapper-loading d-none">
              <div class="spinner-border text-primary" role="status">
                <span class="sr-only">Cargando...</span>
              </div>
            </div>
            <div id="wrapper-not-result" class="d-none">
                <liferay-ui:message key="no-results" />
            </div>
        </div>

    </div>
</div>

<template id="as-total-items-template">
    <div class="m-ajaxresults-header-element">
        #total-items# resultado
    </div>

    <div class="m-ajaxresults-header-elements">
        #total-items# resultados
    </div>

    <div> #items-page# elementos por página</div>
</template>


<template id="as-template">
    <div class="c-recent-messages-item" data-im-id="#chatId#">

        <div class="c-recent-messages-item__user #read#">
            <img src="#portrait#"  class="c-recent-messages-item__photo"/>

            <div>
                <span class="user-name"> #userName#  +#userCount#</span>
                <span class="job-title"> #jobTitle# </span>
            </div>
            <span class="messages-icon"></span>
        </div>
        <p class="c-recent-messages-item__body">
            #description#
        </p>

        <p class="c-recent-messages-item__group #showFromGroup#">
            #fromGroup#
        </p>
        <p class="c-recent-messages-item__date">
            #createDate#
        </p>
    </div>
</template>

<script>
    ajaxSearchGlobalConfig = {
        _preAppendItem : function (newItem, jsonItem) {
            if(!newItem.find(".c-recent-messages-item__group").hasClass("from-group")){
                newItem.find(".c-recent-messages-item__group").toggle();
            }
            return newItem
         },
        _postdrawItem : function (jsonItem) {},
        _predrawAll : function (payload) {},
        _postdrawAll : function (payload) {
            $('.c-messages-left').removeClass("loading");
            if(payload.currentPage == 1){
                if(!localStorage.getItem("firstLoad")){
                    localStorage.setItem("firstLoad", true);
                    $(".c-recent-messages-list").children().first().click();
                }
            }
            console.log(payload);
         }

    }

    Liferay.on('es_camara_intranet_group_chat_ChatPortlet:portletRefreshed', function(){
          $('#wrapper-loading').addClass("d-none");
          $('.c-messages-left').removeClass("loading");
    });
    $(document).ready(function(){
        localStorage.removeItem("firstLoad");
    });



</script>