<%@ include file="init.jsp" %>

<%@ page contentType="text/html; charset=UTF-8" %>

<div class="m-searchAjax m-searchAjax--results">
    <div class="c-messages">
        <div id="as-total-items" class="d-none">
            <!-- EMPTY BY DEFAULT -->
        </div>
        <div class="c-directory">
        <div class="loading-animation"></div>
            <div class="c-directory-wrapper container">
                <!-- las columnas -->
                <div class="c-directory-column">
                    <div id="as-wrapper" class="">
                        <!-- -->
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<template id="as-total-items-template">
    <div class="m-ajaxresults-header-element">
        #total-items# resultado
    </div>
    <div class="m-ajaxresults-header-elements">
        #total-items# resultados
    </div>
    <div> #items-page# elementos por página</div>
</template>


<template id="as-template">
    <li class="c-directory-list-item">
        <a href="#" class="c-directory-link" id="directorio-id-#id#" onClick="showDirectoryDetail(this.id); return false;">#title#</a>
    </li>
    <div class="c-directory-detail" id="directory-detail-id-#id#">
        <h3 class="c-directory-city">#title#
        <span class="close-button" onclick="showDirectoryDetail(this.id);" style="float: right; padding-right: 15px; cursor: pointer;">&times;</span>
        </h3>
        #html#
        <div class="c-directory-info-wrapper">
            <p class="c-directory-info-title">Fecha de última actualización</p>
            <p class="c-directory-info-text">#fecha-modificacion#</p>
        </div>
    </div>
</template>

<style type="text/css">
.c-directory-detail {
  display: none;
}

.c-directory-column {
  visibility: hidden;
}

.loading-animation{
    display: none;
}
</style>

<script>
ajaxSearchGlobalConfig = {
    _preAppendItem: function(newItem, jsonItem) {
        const queryString = window.location.search;
        const urlParams = new URLSearchParams(queryString);
    },
    _postdrawItem: function(jsonItem) {},
    _predrawAll: function(payload) {},
    _postdrawAll: function(payload) {}
};

function showDirectoryDetail(linkId) {
    var id = linkId.replace('directorio-id-', '');
    var divId = 'directory-detail-id-' + id;

    var $directoryDetail = $('#' + divId);
    var $directoryWrapper = $('.c-directory-wrapper');
    var $directoryListItem = $('#' + linkId).closest('.c-directory-list-item');

    // Verificar si el detalle actual está visible
    var isDetailVisible = $directoryDetail.is(':visible');

    $('.c-directory-detail').not($directoryDetail).hide();
    $directoryDetail.toggle();

    var direccionText = $('#' + divId).find('#mapID').text();
    var $locationLink = $('#' + divId).find('.c-directory-location-link');

    if ($locationLink.attr('href') === '') {
        $locationLink.attr('href', 'https://maps.google.com/?q=' + direccionText);
    }
    var hasVisibleDetail = $('.c-directory-detail').is(':visible');
    $directoryWrapper.toggleClass('selected', hasVisibleDetail && !isDetailVisible);

    var $columnThreeItems = $('.c-directory-list-item:nth-child(3)');

    if ($directoryDetail.is(':visible')) {
        $columnThreeItems.css('max-width', '151px');
    } else {
        $columnThreeItems.css('max-width', '');
    }
}

function moveDetalle() {
    var directoryElement = document.querySelector('.c-directory');
    var directoryWrapperElement = document.querySelector('.c-directory-wrapper');
    var directoryDetailElements = document.querySelectorAll('.c-directory-detail');

    directoryDetailElements.forEach(function(directoryDetailElement) {
        directoryWrapperElement.insertAdjacentHTML('afterend', directoryDetailElement.outerHTML);
        directoryDetailElement.remove();
    });
}

function agruparDirectorios(maxColumnas) {
    var directoryItems = document.getElementsByClassName("c-directory-list-item");

    // Crear un objeto para almacenar los datos de cada elemento
    var itemsData = [];

    // Obtener los datos de cada elemento existente
    for (var i = 0; i < directoryItems.length; i++) {
        var item = directoryItems[i];
        var link = item.querySelector(".c-directory-link");
        var detail = item.nextElementSibling;

        // Obtener los datos relevantes del elemento
        var data = {
            linkHTML: link.outerHTML,
            detailHTML: detail ? detail.outerHTML : null
        };

        itemsData.push(data);
    }

    // Obtener el div existente con la clase c-directory-wrapper
    var directoryWrapper = document.querySelector(".c-directory-wrapper");

    // Limpiar el contenido existente del div
    directoryWrapper.innerHTML = '';

    // Organizar los elementos en grupos por letra inicial
    var groups = {};
    for (var i = 0; i < itemsData.length; i++) {
        var data = itemsData[i];
        var linkHTML = data.linkHTML;
        var title = linkHTML.match(/>(.*?)<\/a>/)[1];
        var firstLetter = title.charAt(0).toUpperCase();

        // Obtener la letra para agrupar palabras con y sin tilde
        var letra = getLetra(firstLetter);

        if (!groups[letra]) {
            groups[letra] = [];
        }

        groups[letra].push(data);
    }

    // Obtener las letras iniciales en orden alfabético
    var initials = Object.keys(groups).sort();

    // Calcular el número de elementos por columna
    var itemCount = Math.ceil(initials.length / maxColumnas);

    // Crear las columnas y distribuir los elementos
    for (var i = 0; i < maxColumnas; i++) {
        // Crear el div de la columna
        var columnDiv = document.createElement("div");
        columnDiv.className = "c-directory-column";
        columnDiv.style.visibility = "hidden"; // Ocultar la columna inicialmente
        directoryWrapper.appendChild(columnDiv);

        // Agregar los elementos a la columna actual
        for (var j = i * itemCount; j < (i + 1) * itemCount && j < initials.length; j++) {
            var letter = initials[j];
            var items = groups[letter];

            // Crear el elemento <ul> con la clase "c-directory-list"
            var ul = document.createElement("ul");
            ul.className = "c-directory-list";

            // Agregar la letra inicial como encabezado
            var header = document.createElement("li");
            header.className = "c-directory-list-item_title";
            header.innerHTML = letter;
            ul.appendChild(header);

            // Crear un conjunto para almacenar los títulos ya agregados
            var addedTitles = new Set();

            // Agregar los elementos del grupo sin duplicados
            for (var k = 0; k < items.length; k++) {
                var itemData = items[k];
                var title = itemData.linkHTML.match(/>(.*?)<\/a>/)[1];

                // Verificar si el título ya ha sido agregado
                if (!addedTitles.has(title)) {
                    // Agregar el enlace
                    var listItem = document.createElement("li");
                    listItem.className = "c-directory-list-item";
                    listItem.innerHTML = itemData.linkHTML;
                    ul.appendChild(listItem);

                    // Agregar el div de detalle si existe
                    if (itemData.detailHTML) {
                        var parser = new DOMParser();
                        var detailElement = parser.parseFromString(itemData.detailHTML, 'text/html').body.firstChild;
                        ul.appendChild(detailElement);
                    }
                    addedTitles.add(title); // Agregar el título al conjunto de títulos agregados
                }
            }

            // Agregar el <ul> a la columna actual
            columnDiv.appendChild(ul);
        }
    }

    var columnElements = document.querySelectorAll('.c-directory-column');
    columnElements.forEach(function(columnElement) {
        columnElement.style.visibility = 'visible';
    });

    moveDetalle();
}

function getLetra(letter) {
    var letrasTilde = {
        "Á": "A",
        "É": "E",
        "Í": "I",
        "Ó": "O",
        "Ú": "U"
    };

    return letrasTilde[letter] || letter;
}

setTimeout(function() {
    agruparDirectorios(3);
    if ($('.c-directory-list-item_title').length === 0) {
        var noResults = '<h6 class="as-empty-message">No se han encontrado resultados</h6>';
        $('.c-directory-wrapper.container').prepend(noResults);
    }
}, 800);

</script>

