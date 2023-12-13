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
                   input.value = object[key];
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
