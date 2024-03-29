<%@ include file="init.jsp" %>

<div class="m-searchAjax m-searchAjax--results">
    <div>
        <div id="as-total-items">
            <!-- EMPTY BY DEFAULT -->
        </div>

        <div class="m-results-wrapper ema-documentos-ajax ema-ajaxsearch">
            <div class="ema-table__wrapper">
                <table class="ema-table__ajaxsearch">
                    <thead>
                     <tr>
                      <th><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.objects.result.subject" /></th>
                      <th><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.objects.result.dateactivity" /></th>
                      <th><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.objects.result.numeroEmpleados" /></th>
                      <th><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.objects.result.denominacionFromacion" /></th>
                      <th><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.objects.result.email" /></th>
                      <th><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.objects.result.status" /></th>
                      <th><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.objects.result.datesent" /></th>
                      <th></th>
                     </tr>
                    </thead>
                    <tbody id="as-wrapper">
                        <!-- -->
                    </tbody>
                </table>
            </div>
            <div id="wrapper-not-result" class="d-none">
                <liferay-ui:message key="no-results" />
            </div>
        </div>

    </div>
</div>

<template id="as-total-items-template" class="d-none">
    <div class="m-ajaxresults-header-element">
        <liferay-ui:message key="es.emasesa.intranet.ajaxsearch.found-sing" /> #total-items# <liferay-ui:message key="es.emasesa.intranet.ajaxsearch.document" />
    </div>

    <div class="m-ajaxresults-header-elements">
        <liferay-ui:message key="es.emasesa.intranet.ajaxsearch.found-plur" /> #total-items# <liferay-ui:message key="es.emasesa.intranet.ajaxsearch.documents" />
    </div>

    <div class="d-none"> #items-page# documentos por página</div>
</template>


<template id="as-template">
    <tr>
        <td>#asunto#</td>
        <td>#fechaActividad#</td>
         <td>#numeroEmpleadosALosQueSolicita#</td>
         <td>#denominacion#</td>
         <td>#email#</td>
        <td><span class="ema-pill-estado #estado-code#">#estado#</span></td>
        <td>#fechaEnvio#</td>
        <td class="ema-td-dropdown">
            <button class="ema-button-moreoptions">
                <i class="fa-solid fa-ellipsis-vertical"></i>
            </button>
            <ul class="ema-desplegable-moreoptions">
                    <li class="#isHidden#">
                        <button href="#marcarLeido" class="markReadButton" data-solicitudId="#objectEntryId#">
                            <i class="fa-solid fa-check"></i>
                            <liferay-ui:message key="es.emasesa.intranet.ajaxsearch.objects.result.markRead" />
                        </button>
                    </li>
                <li>
                    <button onclick="openEditDialog('#urlEditar#')">
                        <i class="fa-solid fa-edit"></i>
                        <liferay-ui:message key="es.emasesa.intranet.ajaxsearch.objects.result.edit" />
                    </button>
                </li>
                <li>
                    <a href="#eliminar#" class="removeButton" data-solicitudId="#objectEntryId#">
                        <i class="fa-solid fa-trash"></i>
                        <liferay-ui:message key="es.emasesa.intranet.ajaxsearch.objects.result.delete" />
                    </a>
                </li>
            </ul>
            <a href="#urlVisualizar#" class="ema-enlace-visualizar"><i class="fa-solid fa-eye"></i></a>
        </td>
    </tr>
</template>

<script>
ajaxSearchGlobalConfig = {
    _preAppendItem : function ( newItem , jsonItem ) {return newItem} ,
    _postdrawItem : function ( jsonItem ) {} ,
    _predrawAll : function ( payload ) {} ,
    _postdrawAll : function ( payload ) {
        addClickFunctionality();
        checkStatus();
    }
}
$( document )
    .ready( function () {
        var options = $( ".results-pagination-select-container .results-pagination-select option" );
        for ( var i = 0 ; i < options.length ; i++ ) {
            var urlParams = new URLSearchParams( window.location.search );
            var curPage = urlParams.get( 'currentPage' );
            if ( curPage == null ) {
                curPage = 1;
            }
            var value = options[ i ].value;
            if ( value == curPage ) {
                options[ i ].selected = true;
            }
        }
        $( document )
            .mouseup( function ( e ) {
                var container = $( ".ema-desplegable-moreoptions" );
                if ( !container.is( e.target ) && container.has( e.target ).length === 0 ) {
                    container.removeClass( 'show' );
                }
            } );
    } );

var addClickFunctionality = function () {
    var moreOptionsTd = $( '.ema-td-dropdown' );
    moreOptionsTd.each( function () {
        $( this )
            .children( '.ema-button-moreoptions' )
            .on( 'click' , function () {
                $( this )
                    .siblings( '.ema-desplegable-moreoptions' )
                    .toggleClass( 'show' );
            } );
    } );
}

var checkStatus = function () {
    $( 'tbody#as-wrapper tr' )
        .each( function () {
            var estado = $( this )
                .find( '.ema-pill-estado' );
            if ( estado.hasClass( "success" ) ) {
                $( this )
                    .find( ".ema-button-moreoptions" )
                    .remove();
                $( this )
                    .find( ".ema-desplegable-moreoptions" )
                    .remove();
            } else if ( estado.hasClass( "danger" ) ) {
                $( this )
                    .find( ".ema-button-moreoptions" )
                    .remove();
                $( this )
                    .find( ".ema-desplegable-moreoptions" )
                    .remove();
            } else {
                $( this )
                    .find( '.ema-enlace-visualizar' )
                    .remove();
            }
        } );
}


var openEditDialog = function ( url ) {
    Liferay.Util.openWindow( {
        dialog : {
            destroyOnHide : true ,
            modal : true ,
            after : {
                render : function ( event ) {
                    //
                }
            }
        } ,
        id : 'EditInfoIntDialog' ,
        refreshWindow : window ,
        title : 'Consultar' ,
        uri : url
    } );
}

$(document).on('click', '.removeButton', function(){
const objectEntryId = $(this).data("solicitudid");
    const url = `/o/object-entry-util/delete-object-entry/` + objectEntryId;

    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error en la solicitud');
                Liferay.Util.openToast({
                    type: 'danger',
                    title: '<liferay-ui:message key='es.emasesa.intranet.ajaxsearch.messages.error.title'/>',
                    message: '<liferay-ui:message key='es.emasesa.intranet.ajaxsearch.messages.error.message'/>'
                });
            }
            return response.json();
        })
        .then(data => {
            $(this).closest("tr").remove();
            Liferay.Util.openToast({
                type: 'success',
                title: '<liferay-ui:message key='es.emasesa.intranet.ajaxsearch.messages.success.title'/>',
                message: '<liferay-ui:message key='es.emasesa.intranet.ajaxsearch.messages.success.message'/>'
            });
        })
        .catch(error => {
            Liferay.Util.openToast({
                type: 'danger',
                title: '<liferay-ui:message key='es.emasesa.intranet.ajaxsearch.messages.error.title'/>',
                message: '<liferay-ui:message key='es.emasesa.intranet.ajaxsearch.messages.error.message'/>'
            });
        });
});

$(document).on('click', '.markReadButton', function(){
    const objectEntryId = $(this).data("solicitudid");
    const url = `/o/object-entry-util/mark-read/` + objectEntryId;

    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error en la solicitud');
                Liferay.Util.openToast({
                    type: 'danger',
                    title: '<liferay-ui:message key='es.emasesa.intranet.ajaxsearch.messages.error.title'/>',
                    message: '<liferay-ui:message key='es.emasesa.intranet.ajaxsearch.messages.error.message'/>'
                });
            }
            return response.json();
        })
        .then(data => {
            Liferay.Util.openToast({
                type: 'success',
                title: '<liferay-ui:message key='es.emasesa.intranet.ajaxsearch.messages.success.title'/>',
                message: '<liferay-ui:message key='es.emasesa.intranet.ajaxsearch.messages.success.message'/>'
            });
        })
        .catch(error => {
            Liferay.Util.openToast({
                type: 'danger',
                title: '<liferay-ui:message key='es.emasesa.intranet.ajaxsearch.messages.error.title'/>',
                message: '<liferay-ui:message key='es.emasesa.intranet.ajaxsearch.messages.error.message'/>'
            });
        });
});

</script>