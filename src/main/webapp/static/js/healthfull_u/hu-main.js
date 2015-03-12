/* 
 * Email Popup
 */
$(document).ready(function() {
    $('#my_popup').popup({
        backgroundactive: true,
        blur: false,
        onopen: function() {
            this.draggable();
        }
    });
});

$(function() {
    $('#compose-button').on('click', function(e) {
        $('#compose-form').show();
        $("html, body").animate({ scrollTop: $(document).height() });
    });

	$('#compose-cancel').on('click', function(e) {
        $('#compose-form').hide();
    });

});