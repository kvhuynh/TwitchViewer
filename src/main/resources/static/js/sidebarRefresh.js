jQuery(document).ready(function() {
    setInterval(function() {
        jQuery("#favorites").load('sidebar.tag');
    }, 1000);
});