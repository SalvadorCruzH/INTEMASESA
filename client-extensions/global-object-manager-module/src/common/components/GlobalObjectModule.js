import React from 'react';
import ObjectApi from "../services/ObjectApi";
import * as Constants from "../js/Constants";

class GlobalObjectModule extends React.Component {
    constructor() {
        super();
        this.state = {
                    configuration: null,
                    mode: 1,
                    objectEntryId: null
                }

        console.debug("Cargando módulo objecto");

        EmasesaApi.getConfiguration(this.loadConfiguration, this.errorHandler)


    }

    loadObject = () => {

      var searchParams = new URLSearchParams(window.location.search);
      var mode = searchParams.get("mode");

      if(!mode || mode == null){
         mode = 1;
      }else if(mode>3){
        mode = 1;
      }

      var objectEntryId;
        if(searchParams.has('objectEntryId') && searchParams.has('objectType')){
            objectEntryId = searchParams.get("objectEntryId");
            var objectType = searchParams.get("objectType");
            var objectMapping = JSON.parse(this.state.configuration.objectMapping);
            var objectCallUrl = Constants.oauthUserAgent.URL_DEFAULT+objectMapping[objectType].url;
            console.log(objectCallUrl);
            ObjectApi.getObject(objectEntryId,objectCallUrl, this.setObject, this.errorHandler);

        }
        this.state = {
            mode: mode,
            objectEntryId: objectEntryId
        }

    }

    setObject = (object) => {

        if(this.state.mode==1){
            document.querySelectorAll(".form-control").forEach(((element) => element.readOnly = true));
            document.querySelector(".lfr-layout-structure-item-inputs-submit-button").classList.add("d-none");
            document.querySelectorAll(".btn-secondary").disabled = true;
            if(document.getElementById("generate-otp-button")){
                document.getElementById("generate-otp-button").classList.add("d-none");
            }


        }else if(this.state.mode==2){
            var input = document.createElement("input");

            input.setAttribute("type", "hidden");

            input.setAttribute("name", "classPK");

            input.setAttribute("value", this.state.objectEntryId);

            if(document.querySelector(".lfr-layout-structure-item-form")){
                document.querySelector(".lfr-layout-structure-item-form").appendChild(input);
            }
        }

        Object.keys(object).forEach(function(key) {
            if(object[key] != null){
            var input = document.querySelector("[name='"+key+"']");
                if(input){
                    $('.component-button.text-break').hide();
                    $('.seleccionParte').hide();
                    $('.penoso').hide();
                    $('.locomocion').hide();
                    $('.toxico').hide();
                    $('.trabajoPantalla').hide();
                    $('.volante').hide();
                    $('.text-input.attach-listadoSolicitudes').hide();

                    input.value = object[key];
                    if (key == "listadoSolicitudes"){
                        var tbody = $("#table-solicitudes tbody");
                        var data = JSON.parse(object[key]);
                        $.each(data, function(index, item) {
                            var newRow = $('<tr>');

                            newRow.append('<td></td>');
                            newRow.append('<td></td>');
                            newRow.append('<td>' + item.solicitante + '</td>');
                            newRow.append('<td>' + item.parte + '</td>');
                            newRow.append('<td>' + item.fechaactividad + '</td>');
                            newRow.append('<td>' + item.valor + '</td>');
                            newRow.append('<td>' + item.detalles + '</td>');

                            tbody.append(newRow);
                        });
                    }
                }
            }
        });

    }

    loadConfiguration = (result) => {
            console.debug("configurationData");
            console.debug(result);
            if (result) {
             this.state = {
                configuration: result
             }

                this.loadObject();
            }
        }

    errorHandler = (error) => {

                Liferay.Util.openToast({
                    message: Liferay.Language.get('global.error.config'),
                    title: Liferay.Language.get('global.error'),
                    toastProps: {
                        autoClose: 5000,
                    },
                    type: 'danger',
                });

        }

    render() {
        return (
            <>
            </>
        )
    }
}

export default GlobalObjectModule;
