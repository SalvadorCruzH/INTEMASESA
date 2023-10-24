import React from 'react';
import {createRoot} from 'react-dom/client';
import GlobalModule from "./common/components/GlobalModule";


const App = () => {
	return (
		<>
			{Liferay.ThemeDisplay.isSignedIn() && (
                <GlobalModule />
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
}

const ELEMENT_ID = 'global-module';

if (!customElements.get(ELEMENT_ID)) {
	customElements.define(ELEMENT_ID, WebComponent);
}
