<%@ include file="init.jsp" %>

<style>
/* Overriding header margin in form portlets */
.portlet-layout .portlet-header {
    margin-bottom: 0;
}
</style>

<jsp:include page="${formPage}" flush="true" />

<script>
var button = $('button.ema-ajaxsearch-form__filterbutton');
var filters = $('div.ema-ajaxsearch-form__filters');
button.on('click', function() {
    filters.fadeToggle();
    if (filters.attr('aria-hidden') === 'false') {
        button.find('i.fa-chevron-down').toggleClass('fa-chevron-down fa-chevron-up');
        filters.attr('aria-hidden', 'true');
    } else {
        button.find('i.fa-chevron-up').toggleClass('fa-chevron-up fa-chevron-down');
        filters.attr('aria-hidden', 'false');
    }
});

</script>