var templateToPageMap = {
    "offence-list" : "list-offences",
    "offence-form" : "add-offence",
    "offence-show" : "offence",
    "offence-status" : "offence",
    "driver-list" : "drivers",
    "vehicle-list" : "vehicles",
    "association-list" : "associations",
    "offender-offence-list" : "offence",
    "offence-code-list" : "offence-codes"
};
var template = $("#page-header").attr("data-template");
var page = templateToPageMap[template];
var pageSelector = "li[data-page='" + page + "']";
var pages = $("#pages");
pages.find("a").removeClass("active");
var currentPage = pages.find(pageSelector);
currentPage.children("a").addClass("active");

$.extend($.easing, {
    easeOutSine: function easeOutSine(x, t, b, c, d) {
        return c * Math.sin(t / d * (Math.PI / 2)) + b;
    }
});

$(document).ready(function () {
    /**
     * Dropdown adjustments
     */

    var slideConfig = {
        duration: 270,
        easing: 'easeOutSine'
    };

    // Add dropdown animations when toggled.
    $(':not(.main-sidebar--icons-only) .dropdown').on('show.bs.dropdown', function () {
        $(this).find('.dropdown-menu').first().stop(true, true).slideDown(slideConfig);
    });

    $(':not(.main-sidebar--icons-only) .dropdown').on('hide.bs.dropdown', function () {
        $(this).find('.dropdown-menu').first().stop(true, true).slideUp(slideConfig);
    });

    /**
     * Sidebar toggles
     */
    $('.toggle-sidebar').click(function (e) {
        $('.main-sidebar').toggleClass('open');
    });
});