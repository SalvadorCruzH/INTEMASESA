<%@ include file="init.jsp" %>

<div class="m-searchAjax m-searchAjax--results">
    <div>
        <div id="as-total-items" class="d-none">
            <!-- EMPTY BY DEFAULT -->
        </div>

        <div class="m-results-wrapper c-publisher c-messages-contacts">
            <ul id="as-wrapper" class="c-publisher__list m-dFlexWrap m-listBaseNoStyles c-messages-contacts-list">
                <!-- -->
            </ul>
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
    <li class="c-messages-contacts-item">
        <div class="checkbox">
            <input class="user-selection" type="checkbox" name="user-selected" data-user-id="#userId#"/>
            <label for="checkbox-header"></label>
        </div>
        <div class="user-portrait">
            <img src="/image/#userPortrait#"/>
            <span class="user-name"> #fullName#</span>
            <span class="job-title"> #jobTitle#</span>
        </div>

    </li>
</template>

<script>
    ajaxSearchGlobalConfig = {
        _preAppendItem : function (newItem, jsonItem) {return newItem},
        _postdrawItem : function (jsonItem) {},
        _predrawAll : function (payload) {},
        _postdrawAll : function (payload) {checkboxEvents();}
    }


    function checkboxEvents(){
        let usersEl= $(".users-id-to");
        let userVal = usersEl.val();
        let users = userVal.split(",");

        users.forEach(function(item, index, arr){
            let userItem = $("input[data-user-id='"+item+"']");
            if(userItem){
                userItem.attr("checked",true);
            }
        });

    }
</script>