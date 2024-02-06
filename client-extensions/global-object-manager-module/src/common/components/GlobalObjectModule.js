import React from 'react';
import * as Constants from "../js/Constants";
import ObjectApi from "../services/ObjectApi";

class GlobalObjectModule extends React.Component {
    constructor() {
        super();
        this.state = {
            configuration: null,
            mode: 1,
            objectEntryId: null
        }
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
            let objectMapping = {};
            try{
                objectMapping = JSON.parse(this.state.configuration.objectMapping);
            }catch(e){
                objectMapping = this.state.configuration.objectMapping;
            }
            var objectCallUrl = Constants.oauthUserAgent.URL_DEFAULT+objectMapping[objectType].url;
            ObjectApi.getObject(objectEntryId,objectCallUrl, this.setObject, this.errorHandler);
        }
        this.state = {
            mode: mode,
            objectEntryId: objectEntryId
        }

    }

    setObject = (object) => {

        let mode = Number(this.state.mode);
        if(mode === 1){
            document.querySelectorAll(".form-control").forEach(((element) => element.readOnly = true));
            document.querySelector(".lfr-layout-structure-item-inputs-submit-button").classList.add("d-none");
            document.querySelectorAll(".btn-secondary").disabled = true;
            if(document.getElementById("generate-otp-button")){
                document.getElementById("generate-otp-button").classList.add("d-none");
            }
        }else if(mode === 2){
            var input = document.createElement("input");
            input.setAttribute("type", "hidden");
            input.setAttribute("name", "classPK");
            input.setAttribute("value", this.state.objectEntryId);

            if(document.querySelector(".lfr-layout-structure-item-form")){
                document.querySelector(".lfr-layout-structure-item-form").appendChild(input);
            }
        }
        let modeOpened = Number(this.state.mode);
        Object.keys(object).forEach(function(key) {
            if(object[key] != null){
            var input = document.querySelector("[name='"+key+"']");
                if(input){
                    if(modeOpened !== 2){
                        $('.component-button.text-break').hide();
                    }
                    if(modeOpened === 1){
                        input.setAttribute('disabled', 'disabled');
                    }

                    if(input.type === 'file') {
                        let parentInput = input.closest('div');
                        parentInput = parentInput.closest('div');
                        parentInput.setAttribute('style', 'display: none !important');
                        parentInput.nextElementSibling.setAttribute('style', 'display: none !important');
                        let parentParentInput = parentInput.parentNode;
                        let linkDocument = document.createElement("a");
                        linkDocument.setAttribute('href', object[key]['link']['href']);
                        linkDocument.setAttribute('class', 'linkAsButton');
                        linkDocument.setAttribute('target', '_blank');
                        linkDocument.innerHTML= object[key]['link']['label'];
                        parentParentInput.appendChild(linkDocument);
                    }else if(input.type === 'hidden'){
                        let inputsRadioCheck = document.querySelectorAll("[name='"+key+"-1']");
                        if(inputsRadioCheck){
                            let keyRadioChecked = '';
                            if (Array.isArray(object[key])) {
                                let aux = object[key];
                                keyRadioChecked = aux[0]['key'];
                            }else{
                                keyRadioChecked = object[key]['key'];
                            }
                            inputsRadioCheck.forEach(function(inputRadio) {
                                if(inputRadio.dataset.optionValue === keyRadioChecked){
                                    inputRadio.checked = 'checked';
                                    simulateGlobalClick(inputRadio);
                                }
                                if(modeOpened === 1) {
                                    inputRadio.disabled = 'disabled';
                                }
                            });
                            input.value = keyRadioChecked;
                        }
                    } else if(input.type === 'checkbox'){
                        let inputsRadio = document.querySelectorAll("[name='"+key+"-1']");
                        if(inputsRadio){
                            let keyRadioChecked = '';
                            if (Array.isArray(object[key])) {
                                let aux = object[key];
                                keyRadioChecked = aux[0]['key'];
                            }else{
                                keyRadioChecked = object[key]['key'];
                            }

                            inputsRadio.forEach(function(inputRadio) {
                                if(inputRadio.dataset.optionValue === keyRadioChecked){
                                    inputRadio.checked = 'checked';
                                    simulateGlobalClick(inputRadio);
                                }
                                if(modeOpened === 1) {
                                    inputRadio.disabled = 'disabled';
                                }
                            });
                            input.value = keyRadioChecked;
                        }
                    } else if(input.type === 'button' && modeOpened !== 2){
                        input.style.display = 'none';
                    } else if (input.type === 'date') {
                        if (!isNaN(new Date(object[key]).getDate())) {
                            let date = new Date(object[key]);
                            input.value = date.toISOString().slice(0,10);
                        } else {
                            input.value = object[key];
                        }
                    } else {
                        if(typeof object[key] === 'object'){
                            input.value = object[key]['name'];
                        } else {
                            input.value = object[key];
                        }
                    }

                    if (modeOpened === 1 && key === "listadoSolicitudes"){
                        var tbody = $("#table-solicitudes tbody");
                        var data = JSON.parse(object[key]);
                        $.each(data, function(index, item) {
                            var newRow = $('<tr>');

                            newRow.append('<td></td>');
                            newRow.append('<td></td>');
                            newRow.append('<td>' + item.solicitante + '</td>');
                            newRow.append('<td>' + item.parte + '</td>');
                            newRow.append('<td>' + item.fecha + '</td>');
                            newRow.append('<td>' + item.valor + '</td>');
                            newRow.append('<td>' + item.detalles + '</td>');

                            tbody.append(newRow);
                        });
                    }else if (modeOpened === 2 && key === "listadoSolicitudes"){
                        var tbody = $("#table-solicitudes tbody");
                        var data = JSON.parse(object[key]);
                        const checkboxEstado = '<input type="checkbox">';
                        $.each(data, function(index, item) {
                            var newRow = $('<tr>');
                            newRow.append('<td>' + checkboxEstado + '</td>');
                            newRow.append('<td></td>');
                            newRow.append('<td>' + item.solicitante + '</td>');
                            newRow.append('<td>' + item.parte + '</td>');
                            newRow.append('<td>' + item.fecha + '</td>');
                            newRow.append('<td>' + item.valor + '</td>');
                            newRow.append('<td>' + item.detalles + '</td>');

                            tbody.append(newRow);
                        });
                    }else if (modeOpened === 1 && key === "listadoSolicitudesJubilados"){
                        var tbody = $("#table-solicitudes tbody");
                        var data = JSON.parse(object[key]);
                        const checkboxEstado = '<input type="checkbox">';
                        $.each(data, function(index, item) {
                            var newRow = $('<tr>');
                            newRow.append('<td></td>');
                            newRow.append('<td>' + item.matricula + '</td>');
                            newRow.append('<td>' + item.solicitante + '</td>');
                            newRow.append('<td>' + item.inicio + '</td>');
                            newRow.append('<td>' + item.fin + '</td>');
                            newRow.append('<td>' + item.duracion + '</td>');
                            newRow.append('<td>' + item.observaciones + '</td>');

                            tbody.append(newRow);
                        });
                    }else if (modeOpened === 1 && key === "listadoBeneficiarios"){
                         var tbody = $("#table-solicitudes tbody");
                         var data = JSON.parse(object[key]);
                         const checkboxEstado = '<input type="checkbox">';
                         $.each(data, function(index, item) {
                             var newRow = $('<tr>');
                             newRow.append('<td></td>');
                             newRow.append('<td>' + item.prelacion + '</td>');
                             newRow.append('<td>' + item.nombreBeneficiario + '</td>');
                             newRow.append('<td>' + item.NIF + '</td>');

                             tbody.append(newRow);
                         });
                     }else if (modeOpened === 1 && key === "listadoDeRecursos"){
                       var tbody = $("#table-solicitudes tbody");
                       var data = JSON.parse(object[key]);
                       const checkboxEstado = '<input type="checkbox">';
                       $.each(data, function(index, item) {
                           var newRow = $('<tr>');
                           newRow.append('<td></td>');
                           newRow.append('<td>' + item.nombreDeServicio + '</td>');
                           newRow.append('<td>' + item.prioridadRecurso + '</td>');
                           newRow.append('<td>' + item.descripcion + '</td>');

                           tbody.append(newRow);
                       });
                   }
                }
            }
        });
        if(modeOpened !== 2){
            document.querySelectorAll(".lfr-layout-structure-item-form button").forEach(function(buttonInput) {
                buttonInput.style.display = 'none';
            });
        }
    }

    loadConfiguration = (result) => {
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
