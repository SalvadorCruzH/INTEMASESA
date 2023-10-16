document.addEventListener('DOMContentLoaded', function () {
        const addTelefonoButton = document.getElementById('addTelefonoButton');
        const telefonoRepetibleContainer = document.getElementById('telefonoRepetibleContainer');
        const guardarButton = document.querySelector('button[data-lfr-editable-id="submit-button-text"]'); 

        const telefonoActividadInput = document.querySelector('input[name="telefonoActividad"]');
        const telefonoRepetibleInputs = []; 

        addTelefonoButton.addEventListener('click', function () {
            const newTelefonoInput = document.createElement('input');
            newTelefonoInput.type = 'text';
            newTelefonoInput.className = 'form-control';
            newTelefonoInput.placeholder = 'Ingrese un teléfono';
            newTelefonoInput.name = 'telefonoRepetible'; 
            
            telefonoRepetibleContainer.appendChild(newTelefonoInput);

            telefonoRepetibleInputs.push(newTelefonoInput);
        });

        guardarButton.addEventListener('click', function () {
            // Recopila los valores de todos los campos de teléfono repetibles
            const telefonoRepetibleValues = telefonoRepetibleInputs.map(function (input) {
                return input.value;
            });
            const combinedValues = telefonoRepetibleValues.join(',');
            const valorTelefonoActividad = telefonoActividadInput.value;
            const valoresFinales = [valorTelefonoActividad, combinedValues].filter(Boolean).join(',');

            telefonoActividadInput.value = valoresFinales;
        });
    });