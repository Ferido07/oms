var this_js_script = $('script[src*=offence-form]');
var next = this_js_script.attr('data-next');
var done = this_js_script.attr('data-done');

if (typeof next === "undefined" )
    next = 'Next';
if (typeof  done === "undefined")
    done = 'Done';

var toListLink = "<a href='../offence'>" + done + "</a>"

var currentTab = 0; // Current tab is set to be the first tab (0)
showTab(currentTab); // Display the current tab

function showTab(n) {
    // This function will display the specified tab of the form...
    var x = document.getElementsByClassName("tab");
    x[n].style.display = "block";
    console.log('n in show tab is ' + n);
    //... and fix the Previous/Next buttons:
    if (n === "0" || n === 0) {
        console.log("in th if clause of n = 0");
        document.getElementById("prevBtn").style.display = "none";
    } else {
        document.getElementById("prevBtn").style.display = "inline";
    }
    if (n === (x.length - 1) || n === (x.length - 1)+"") {
        document.getElementById("nextBtn").innerHTML = toListLink;
        $("#nextBtn").removeClass("btn-primary").addClass("btn-link");
    } else {
        document.getElementById("nextBtn").innerHTML = next;
        $("#nextBtn").removeClass("btn-link").addClass("btn-primary");
    }
    //... and run a function that will display the correct step indicator:
    fixStepIndicator(n);
    currentTab = Number.parseInt(n);
}

function nextPrev(n) {
    // This function will figure out which tab to display
    var x = document.getElementsByClassName("tab");
    // Hide the current tab:
    x[currentTab].style.display = "none";
    // Increase or decrease the current tab by 1:
    currentTab = currentTab + n;
    // Display the correct tab:
    showTab(currentTab);
}

function fixStepIndicator(n) {
    // This function removes the "active" class of all steps...
    var i, x = document.getElementsByClassName("step");
    for (i = 0; i < x.length; i++) {
        x[i].className = x[i].className.replace(" active", "");
    }
    //... and adds the "active" class on the current step:
    x[n].className += " active";
}

/*
 * Code to add navigation with span elements at the bottom of each page
 */
$("span.step").on("click", function () {
    $(".tab").css("display", "none");
    var step = $(this).attr('data-step');
    // console.log(step);
    showTab(step);
});

var dateType = 'gregorian';
function changeDate() {
    var ethiopianDates = $("#ethiopianDate");
    var gregorianDates = $("#gregorianDate");
    var dateSelectorButton = $("#dateSelector");
    //change to ethiopian
    if(dateType === 'gregorian'){
        ethiopianDates.removeClass("d-none");
        gregorianDates.addClass("d-none");
        dateSelectorButton.text("To Gregorian");
        dateType = 'ethiopian';
        //console.log('change date to ...' + dateType);
    }
    //change to gregorian
    else if(dateType === 'ethiopian'){
        gregorianDates.removeClass("d-none");
        ethiopianDates.addClass("d-none");
        dateSelectorButton.text("To Ethiopian");
        dateType = 'gregorian';
        //console.log('change date to ...' + dateType);
    }
}