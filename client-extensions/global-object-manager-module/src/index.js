import React from 'react';
import {createRoot} from 'react-dom/client';
import GlobalObjectModule from "./common/components/GlobalObjectModule";


const App = () => {
	return (
		<>
			{Liferay.ThemeDisplay.isSignedIn() && (
                <GlobalObjectModule />
			)}
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

const ELEMENT_ID = 'global-object-module';

if (!customElements.get(ELEMENT_ID)) {
	customElements.define(ELEMENT_ID, WebComponent);
}
