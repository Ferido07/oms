var dateType = 'gregorian';
function changeDate() {
    var ethiopianDates = $(".ethiopianDate");
    var gregorianDates = $(".gregorianDate");
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