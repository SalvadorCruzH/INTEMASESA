
$(function() {
    menuFuntion.init();
	menuDesktop.init();

});


 /**
 * Menu mobile
 */

 var menuFuntion = (function () {

	var _toggleBtn = document.querySelector("#i-menuButton");
	var _closeBtn = document.querySelector("#i-menuMobile__closeButton");
	var _mobileNavigation = document.querySelector("#i-menuMobile");
	var _body = document.querySelector("body");
	var _menuLiMobile = document.querySelectorAll('.i-menuMobile__navWrapper .i-navMainMobile__li.children .i-navMainMobile__link');

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
			console.log("estoy haciendo click");
			const menu = document.querySelector("#i-menuMobile");
			console.log(menu);
			if (menu && !menu.classList.contains("hidden") && !menu.contains(e.target)){
				if (document.querySelector("#i-menuMobile__closeButton"))
					document.querySelector("#i-menuMobile__closeButton").click();
					console.log("estoy haciendo click fuera del menu");
			}
		});
	}

	var _navInLevelOne = function() {
		Array.prototype.forEach.call(_menuLiMobile, function(el, i){
			el.addEventListener("click",  function(event){
	
				var parentLi = this.parentNode;
				var listUlchild = this.nextElementSibling
				
				if(parentLi.classList.contains('li-open')){
					parentLi.classList.remove('li-open');
					listUlchild.classList.remove('nav-open');
					el.classList.remove('link-open');
					el.setAttribute('aria-expanded', "false");

				}else {
					_menuLiMobile.forEach(function(element){
						element.parentNode.classList.remove('li-open');
					
						element.nextElementSibling.classList.remove('nav-open');
						element.setAttribute('aria-expanded', "false");
						element.classList.remove('link-open');
					});
					parentLi.classList.add('li-open');
					listUlchild.classList.add('nav-open');
					el.classList.add('link-open');
					el.setAttribute('aria-expanded', "true");				
				}
				event.preventDefault();
			
			});
		
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
		_navInLevelOne();
		
	}

	return {
		init: _init,
	};
})();

/**
* Menu desktop
*/

var menuDesktop = (function () {
	var _menuItems = document.querySelectorAll('.i-mainNavigation__ul .i-mainNavigation__li.children');
	
	var _navDeskLevelOne = function() {

		Array.prototype.forEach.call(_menuItems, function(el, i){
			el.querySelector('a').addEventListener("click",  function(event){
				var parentNodeLink = this.parentNode;
			
				if(parentNodeLink.classList.contains('show')){
					parentNodeLink.classList.remove('show');
					parentNodeLink.querySelector('.i-mainNavigation__submenuContainer').classList.remove('open');
					this.setAttribute('aria-expanded', "false");
	
				}else {
					_menuItems.forEach(function(element){
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

	}

	var _closeMenuDesktop = function() {	
		window.addEventListener('click', function(e){
			if(e.target.className === "i-mainNavigation__submenuContainer open") {
				_menuItems.forEach(function(element){
					element.classList.remove('show');
					element.querySelector('.i-mainNavigation__submenuContainer').classList.remove('open');
				});
			}
		}); 
	}

 
	var _init = function () {
        _navDeskLevelOne();
		_closeMenuDesktop();   
	}

	return {
		init: _init,
	};
})();
