import { FieldBase } from 'dynamic-data-mapping-form-field-type/FieldBase/ReactFieldBase.es';
import { useSyncValue } from 'dynamic-data-mapping-form-field-type/hooks/useSyncValue.es';
import React, { useEffect, useState, useRef } from 'react';

const Select = ({ name, onChange, predefinedValue, value, options }) => {

	const selectRef = useRef(null);
	const timeStamp = Date.now();
	useEffect(() => {
		if (selectRef.current) {
			$(selectRef.current).select2();
			$(selectRef.current).on('select2:selecting', function(e) {
                onChange(e.params.args.data.id);
              });
		}

		return () => {
			if (selectRef.current) {
				$(selectRef.current).select2('destroy');
			}
		};
	}, []);

	return (
		<select
			className="ddm-rest-select form-control select"
			ref={selectRef}
			id={name + timeStamp}
			onChange={onChange}
			value={value ? value : predefinedValue}
			name={name}
		>
			{options.map((option) => (
				<option key={option.value} value={option.value}>
					{option.label}
				</option>
			))}
		</select>
	);
};

const Main = ({	  label,
				  name,
				  predefinedValue,
				  value,
				  readOnly,
				  ...otherProps
			  }) => {
	const [currentValue, setCurrentValue] = useSyncValue(
		value ? value : predefinedValue
	);
	const { restUrl, restKey, restValue } = otherProps;
	const [options, setOptions] = useState([]);

	useEffect(() => {
		fetch(restUrl)
			.then((response) => {
				if (!response.ok) {
					throw new Error('Error de red');
				}
				return response.json();
			})
			.then((data) => {
				if (data && data.data && Array.isArray(data.data)) {
					const dataArray = data.data.map((item) => ({
						value: item.v,
						label: item.k,
					}));
					setOptions(dataArray);

				} else {
					console.error('La estructura de datos no es la esperada.');
				}
			})
			.catch((error) => {
				console.error('Error al cargar datos desde la API:', error);
			});
	}, [restUrl]);

    function handleChange(val){
     setCurrentValue(val);
    }


	return (
		<FieldBase label={label}
				   name={name}
				   predefinedValue={predefinedValue}
				   {...otherProps}>
			<Select
				name={`${name}_field`}
				onChange={handleChange}
				readOnly={readOnly}
				predefinedValue={predefinedValue}
				value={currentValue}
				options={options}
			/>
			<input name={name} type="hidden" value={currentValue} />
		</FieldBase>
	);
};

Main.displayName = 'Select';

export default Main;
