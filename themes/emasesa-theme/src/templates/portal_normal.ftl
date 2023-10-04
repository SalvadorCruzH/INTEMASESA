<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${html_title}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	<@liferay_util["include"] page=top_head_include />
	<link href="${css_folder}/fontawesome/css/fontawesome.css" rel="stylesheet">
	<link href="${css_folder}/fontawesome/css/brands.css" rel="stylesheet">
	<link href="${css_folder}/fontawesome/css/solid.css" rel="stylesheet">
	<link href="${css_folder}/fontawesome/css/regular.css" rel="stylesheet">
	<link href="${css_folder}/select2/select2.min.css" rel="stylesheet" />

	<link rel="stylesheet" href="${javascript_folder}/slick/slick.min.css">
	<link rel="stylesheet" href="${javascript_folder}/slick/accessible-slick-theme.min.css">

	<script src="${javascript_folder}/Pagination.js" data-senna-track="permanent"></script>

	<#-- duet date picker -->
	<script type="module" src="${javascript_folder}/duet/duet.esm.js"></script>
	<script nomodule src="${javascript_folder}/duet/duet.js"></script>
	<link rel="stylesheet" href="${css_folder}/duet/duet-emasesa.css" />

</head>

<body class="${css_class}">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<div class="d-flex flex-column min-vh-100">
	<@liferay.control_menu />

	<div class="d-flex flex-column flex-fill position-relative i-mainWrapper" id="wrapper">
        <#include "${full_templates_path}/header.ftl" />
        <#include "${full_templates_path}/content.ftl" />
        <#include "${full_templates_path}/footer.ftl" />
	</div>
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

<!-- inject:js -->

	<@liferay.js file_name="${javascript_folder}/slick/slick.js"/>

<!-- endinject -->

</body>

</html>