import { FieldBase } from 'dynamic-data-mapping-form-field-type/FieldBase/ReactFieldBase.es';
import { useSyncValue } from 'dynamic-data-mapping-form-field-type/hooks/useSyncValue.es';
import React, { useEffect, useState, useRef } from 'react';

const Select = ({ name, onChange, predefinedValue, value, options }) => {
	const selectRef = useRef(null);

	useEffect(() => {
		if (selectRef.current) {
			$(selectRef.current).select2();
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


	return (
		<FieldBase label={label} predefinedValue={predefinedValue} {...otherProps}>
			<Select
				name={name}
				onChange={(event) => {
					setCurrentValue(event.target.value);
					onChange(event);
				}}
				readOnly={readOnly}
				predefinedValue={predefinedValue}
				value={currentValue}
				options={options}
			/>
		</FieldBase>
	);
};

Main.displayName = 'Select';

export default Main;
