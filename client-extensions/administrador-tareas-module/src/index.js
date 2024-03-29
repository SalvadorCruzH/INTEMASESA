import React from 'react';
import { createRoot } from 'react-dom/client';

import TareasModule from './common/components/TareasModule.js';

document.body.classList.add('administrador-tareas-page')

const App = (props) => {
	console.log(props);
	return (
		<>
			{Liferay.ThemeDisplay.isSignedIn() &&
                <TareasModule/>
			}
		</>
	);
};

class WebComponent extends HTMLElement {
	connectedCallback() {

        createRoot(this).render(
            <App />,
            this
        );
    }
    disconnectedCallback() {
           this.root.unmount();
        }
}

const ELEMENT_ID = 'administrador-tareas-module';

if (!customElements.get(ELEMENT_ID)) {
	customElements.define(ELEMENT_ID, WebComponent);
}
