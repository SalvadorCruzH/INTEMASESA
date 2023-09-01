
/*
 * This function gets loaded when all the HTML, not including the portlets, is
 * loaded.
 */
AUI().ready(function () {
    menuFuntion.init();

   
    var menuItems = document.querySelectorAll('.i-mainNavigation__ul .i-mainNavigation__li.children');
   
    Array.prototype.forEach.call(menuItems, function(el, i){
        el.querySelector('a').addEventListener("click",  function(event){
            var parentNodeLink = this.parentNode;
         
            if(parentNodeLink.classList.contains('show')){
                parentNodeLink.classList.remove('show');
                parentNodeLink.querySelector('.i-mainNavigation__submenuContainer').classList.remove('open');
                this.setAttribute('aria-expanded', "false");

            }else {
                menuItems.forEach(function(element){
                    element.classList.remove('show');
                    element.querySelector('.i-mainNavigation__submenuContainer').classList.remove('open');
                });
                parentNodeLink.classList.add('show');
                parentNodeLink.querySelector('a').setAttribute('aria-expanded', "true");
                parentNodeLink.querySelector('.i-mainNavigation__submenuContainer').classList.add('open');
                this.setAttribute('aria-expanded', "true");
                
            }
            event.preventDefault();
            return false;
        
        });
    });

    window.addEventListener('click', function(e){
        if(e.target.className === "i-mainNavigation__submenuContainer open") {
            menuItems.forEach(function(element){
                element.classList.remove('show');
                element.querySelector('.i-mainNavigation__submenuContainer').classList.remove('open');
            });
        }
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


 /**
 * Menu mobile
 */

 var menuFuntion = (function () {

	var _toggleBtn = document.querySelector("#i-menuButton");
	var _closeBtn = document.querySelector("#i-menuMobile__closeButton");
	var _mobileNavigation = document.querySelector("#i-menuMobile");
	var _body = document.querySelector("body");

	var _menuToggle = function() {
		if (typeof _toggleBtn != "undefined" && _toggleBtn != undefined) {
			_toggleBtn.addEventListener("click", function () {
		
				_mobileNavigation.classList.add("show-navigation");
			
				  _body.classList.add("menu-open");
			
				if (_mobileNavigation.classList.contains('hidden')) {
					_mobileNavigation.classList.remove('hidden');
					
					setTimeout(function () {
						_mobileNavigation.classList.remove('visuallyhidden');
						
					}, 20);

				  } 
				});
		
				$('#i-menuMobile, #i-menuButton').click(function(event){
					event.stopPropagation();
				});
		}
		
		if (typeof _closeBtn != "undefined" && _closeBtn != undefined) {
			
			_closeBtn.addEventListener("click", function () {
				_mobileNavigation.classList.remove("show-navigation");
		
				_body.classList.remove("menu-open");
					$('#i-menuButton').css("pointer-events", "none")
		
					_mobileNavigation.classList.add('visuallyhidden'); 
					
				
					_mobileNavigation.addEventListener('webkitTransitionEnd', function(e) {

						if (_mobileNavigation.classList.contains('visuallyhidden')){
							_mobileNavigation.classList.add('hidden');
						}
						
					
					}, {
					capture: false,
					once: true,
					passive: false
					});   
					_mobileNavigation.addEventListener('transitionend', function(e) {
						if (_mobileNavigation.classList.contains('visuallyhidden')){
							_mobileNavigation.classList.add('hidden');
						}
						
					}, {
					capture: false,
					once: true,
					passive: false
					});
					setTimeout(function (){
						$('#i-menuButton').css("pointer-events", "auto")
					}, 500);
				});
		}

		window.addEventListener('click', function(e){
			const menu = document.querySelector("#i-menuMobile");
			if (menu && !menu.classList.contains("hidden") && !menu.contains(e.target)){
				if (document.querySelector("i-menuMobile__closeButton"))
					document.querySelector("i-menuMobile__closeButton").click();
			}
		});
	}


	var _initial = function() {
		if((window.innerWidth > 1200) && (_body.classList.contains("menu-open"))) {
			_body.classList.remove("menu-open");
		  }
		  if((window.innerWidth < 1200) && (_mobileNavigation.classList.contains("show-navigation"))) {
			_body.classList.add("menu-open");
		  }
	}
	$(window).resize(function () {
		_initial();   
	});

	var _init = function () {
        _menuToggle();
		
	}

	return {
		init: _init,
	};
})();