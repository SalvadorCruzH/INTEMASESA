import React from 'react';
import {createRoot} from 'react-dom/client';
import NotificationsModule from "./common/components/NotificationsModule";


const App = () => {
	return (
		<>
			{Liferay.ThemeDisplay.isSignedIn() && (
                <NotificationsModule />
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

const ELEMENT_ID = 'notifications-module';

if (!customElements.get(ELEMENT_ID)) {
	customElements.define(ELEMENT_ID, WebComponent);
}
