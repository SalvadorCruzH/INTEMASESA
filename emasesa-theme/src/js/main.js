
/*
 * This function gets loaded when all the HTML, not including the portlets, is
 * loaded.
 */
AUI().ready(function () {
   
    var menuItems = document.querySelectorAll('.i-mainNavigation__ul .i-mainNavigation__li.children');
    var closeMenuBtn = document.querySelector('#i-closeButton');
   
    Array.prototype.forEach.call(menuItems, function(el, i){
        el.querySelector('a').addEventListener("click",  function(event){
            var parentNodeLink = this.parentNode;
         

            if(parentNodeLink.classList.contains('show')){
                parentNodeLink.classList.remove('show');
                parentNodeLink.querySelector('.i-mainNavigation__submenuContainer').classList.remove('open');
                this.setAttribute('aria-expanded', "false");
                console.log("estoy abierto y me cierran");

        
            }else {
                menuItems.forEach(function(element){
                    element.classList.remove('show');
                    element.querySelector('.i-mainNavigation__submenuContainer').classList.remove('open');
                    console.log("no he hecho click en el que estaba abierto, cierro los demás por si acaso");
                });
                parentNodeLink.classList.add('show');
                parentNodeLink.querySelector('a').setAttribute('aria-expanded', "true");
                parentNodeLink.querySelector('.i-mainNavigation__submenuContainer').classList.add('open');
                this.setAttribute('aria-expanded', "true");
                console.log("y abro el que he hecho click");
            }
            event.preventDefault();
            return false;
        
        });
    });


});

/*
 * This function gets loaded after each and every portlet on the page.
 *
 * portletId: the current portlet's id
 * node: the Alloy Node object of the current portlet
 */
Liferay.Portlet.ready(function (_portletId, _node) {});

/*
 * This function gets loaded when everything, including the portlets, is on
 * the page.
 */
Liferay.on('allPortletsReady', function () {});
