document.addEventListener('DOMContentLoaded', function () {
        const addButton = document.getElementById('addButton');
        const fieldRepetibleContainer = document.getElementById('fieldRepetibleContainer');
        const guardarButton = document.querySelector('button[data-lfr-editable-id="submit-button-text"]'); 
		const fieldName = configuration.fieldName;
        const fieldInput = document.querySelector('input[name="'+configuration.fieldName+'"]');
		console.log("configuration.fieldName: " + configuration.fieldName)
	console.log("fieldInput: " + fieldInput)
        const fieldRepetibleInputs = []; 

        addButton.addEventListener('click', function () {
            const newFieldInput = document.createElement('input');
            newFieldInput.type = 'text';
            newFieldInput.className = 'form-control';
            newFieldInput.placeholder = configuration.placeholder;
            newFieldInput.name = 'fieldRepetible'; 
            
            fieldRepetibleContainer.appendChild(newFieldInput);

            fieldRepetibleInputs.push(newFieldInput);
        });

        guardarButton.addEventListener('click', function () {
            const fieldRepetibleValues = fieldRepetibleInputs.map(function (input) {
                return input.value;
            });
            const combinedValues = fieldRepetibleValues.join(',');
            const valorField = fieldInput.value;
            const valoresFinales = [valorField, combinedValues].filter(Boolean).join(',');

            fieldInput.value = valoresFinales;
        });
    });