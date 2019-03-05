var this_js_script = $('script[src*=offence-form]');
var next = this_js_script.attr('data-next');
var submit = this_js_script.attr('data-submit');
var fullCode = this_js_script.attr('data-full-code');
var description = this_js_script.attr('data-description');
var lang = this_js_script.attr('data-lang');

if (typeof next === "undefined" )
    next = 'Next';
if (typeof submit === "undefined" )
    submit = 'Submit';
if (typeof fullCode === "undefined" )
    fullCode = 'Full Code';
if(typeof description === "undefined")
    description = 'Description';
if(typeof lang === "undefined")
    lang = 'en';


/*
 * Page navigation code
 */
var currentTab = 0; // Current tab is set to be the first tab (0)
showTab(currentTab); // Display the current tab

var validator = $("#regForm").validate({
    errorClass: "invalid-feedback is-invalid"
});

var calendarType = 'gregorian';
var ethiopianCalendar = $.calendars.instance('ethiopian', lang);
var gregorianCalendar = $.calendars.instance('gregorian', lang);
var dateInput = $("#date");
dateInput.attr('placeholder', gregorianCalendar.local.dateFormat);
dateInput.calendarsPicker({calendar: gregorianCalendar});
onDateLoad();

function onDateSubmit() {
    if(calendarType === 'ethiopian'){
        var ethiopianDate = ethiopianCalendar.parseDate(ethiopianCalendar.local.dateFormat, dateInput.val());
        if (ethiopianDate !== null) {
            var gregorianDate = gregorianCalendar.fromJD(ethiopianDate.toJD());
            var isoFormattedDate = gregorianCalendar.formatDate(gregorianCalendar.ISO_8601, gregorianDate);
            //console.log('iso formatted date is ' + isoFormattedDate);
            dateInput.val(isoFormattedDate);
        }
    }
    else if(calendarType === 'gregorian'){
        var gregorianDate = gregorianCalendar.parseDate(gregorianCalendar.local.dateFormat, dateInput.val());
        if(gregorianDate !== null){
            var isoFormattedDate = gregorianCalendar.formatDate(gregorianCalendar.ISO_8601, gregorianDate);
            //console.log('iso formatted date is ' + isoFormattedDate);
            dateInput.val(isoFormattedDate);
        }
    }
}
function onDateLoad(){
    var gregorianDate = gregorianCalendar.parseDate(gregorianCalendar.ISO_8601, dateInput.val());
    if(gregorianDate !== null){
        var isoFormattedGregorianDate = gregorianCalendar.formatDate(gregorianCalendar.local.dateFormat, gregorianDate);
        dateInput.val(isoFormattedGregorianDate);
    }
}

function changeCalendar() {
    var dateSelectorButton = $("#dateSelector");

    //change to ethiopian
    if(calendarType === 'gregorian'){
        calendarType = 'ethiopian';
        dateSelectorButton.text("To Gregorian");
        //console.log('change date to ...' + calendarType);
        dateInput.attr('placeholder', ethiopianCalendar.local.dateFormat);
        dateInput.calendarsPicker('option',{calendar: ethiopianCalendar});

        //if date input is not null convert the selected date
        var gregorianDate = gregorianCalendar.parseDate(gregorianCalendar.local.dateFormat, dateInput.val());
        if (gregorianDate !== null) {
            var ethiopianDate = ethiopianCalendar.fromJD(gregorianDate.toJD());
            var formattedDate = ethiopianCalendar.formatDate(ethiopianCalendar.local.dateFormat, ethiopianDate);
            //console.log('ethiopian formatted date is ' + formattedDate);
            dateInput.val(formattedDate);
        }
    }
    //change to gregorian
    else if(calendarType === 'ethiopian'){
        calendarType = 'gregorian';
        dateSelectorButton.text("To Ethiopian");
        //console.log('change date to ...' + calendarType);
        dateInput.attr('placeholder', gregorianCalendar.local.dateFormat);
        dateInput.calendarsPicker('option',{calendar: gregorianCalendar});

        //if date input is not null convert the selected date
        var ethiopianDate = ethiopianCalendar.parseDate(ethiopianCalendar.local.dateFormat, dateInput.val());
        if (ethiopianDate !== null) {
            var gregorianDate = gregorianCalendar.fromJD(ethiopianDate.toJD());
            var formattedDate = gregorianCalendar.formatDate(gregorianCalendar.local.dateFormat, gregorianDate);
            //console.log('gregorian formatted date is ' + formattedDate);
            dateInput.val(formattedDate);
        }
    }
}

function showTab(n) {
    // This function will display the specified tab of the form...
    var x = document.getElementsByClassName("tab");
    x[n].style.display = "block";
    //... and fix the Previous/Next buttons:
    if (n === 0) {
        document.getElementById("prevBtn").style.display = "none";
    } else {
        document.getElementById("prevBtn").style.display = "inline";
    }
    if (n === (x.length - 1)) {
        document.getElementById("nextBtn").innerHTML = submit;
    } else {
        document.getElementById("nextBtn").innerHTML = next;
    }
    //... and run a function that will display the correct step indicator:
    fixStepIndicator(n)
}

function nextPrev(n) {
    // This function will figure out which tab to display
    var x = document.getElementsByClassName("tab");
    // Exit the function if any field in the current tab is invalid:
    if (n === 1 && !validateForm()) return false;
    // Hide the current tab:
    x[currentTab].style.display = "none";
    // Increase or decrease the current tab by 1:
    currentTab = currentTab + n;
    // if you have reached the end of the form...
    if (currentTab >= x.length) {
        // ... the form gets submitted:
        onDateSubmit();
        document.getElementById("regForm").submit();
        return false;
    }
    // Otherwise, display the correct tab:
    showTab(currentTab);
}

function validateForm() {
    // This function deals with validation of the form fields
    var tabs, inputs, valid = true;
    tabs = $(".tab");
    var tab = $(tabs.get(currentTab));
    inputs = tab.find("input");
    inputs.each(function (index, value) {
        valid = validator.element(value) && valid;
    });
    // If the valid status is true, mark the step as finished and valid:
    if (valid) {
        document.getElementsByClassName("step")[currentTab].className += " finish";
    }
    return valid; // return the valid status
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
 * End of page navigation code
 */

/*
 *  Autocomplete and other dynamic behaviours of offence-form
 */

function getVehiclesByPlateNo(request, response) {
    $.getJSON(
        "/oms/api/vehicles?plateNo=" + request.term,
        function (data) {
            response(data);
        });
}

function populateVehicleInput(vehicle){
    if(vehicle !== null) {
        $("#vehicle-id").val(vehicle["id"]);
        $("#vehicle-type").val(vehicle["type"]).attr("readonly", "readonly");
        $("#vo-libreNo").val(vehicle["vehicleOwnershipModels"][0]["libreNo"]).attr("readonly", "readonly");
        $("#vo-fName").val(vehicle["vehicleOwnershipModels"][0]["personModelOwners"][0]["firstName"]).attr("readonly", "readonly");
        $("#vo-mName").val(vehicle["vehicleOwnershipModels"][0]["personModelOwners"][0]["middleName"]).attr("readonly", "readonly");
        $("#vo-lName").val(vehicle["vehicleOwnershipModels"][0]["personModelOwners"][0]["lastName"]).attr("readonly", "readonly");
        if(vehicle["associationModel"] !== null) {
            $("#vehicle-association-id").val(vehicle["associationModel"]["id"]);
            $("#vehicle-association-name").val(vehicle["associationModel"]["name"]).attr("readonly", "readonly");
        }
        $("#vehicle-side-no").val(vehicle["sideNo"]).attr("readonly", "readonly");
    }
    else{
        $("#vehicle-id").val("");
        $("#vehicle-type").val("").removeAttr("readonly");
        $("#vo-libreNo").val("").removeAttr("readonly");
        $("#vo-fName").val("").removeAttr("readonly");
        $("#vo-mName").val("").removeAttr("readonly");
        $("#vo-lName").val("").removeAttr("readonly");
        $("#vehicle-association-id").val("");
        $("#vehicle-association-name").val("").removeAttr("readonly");
        $("#vehicle-side-no").val("").removeAttr("readonly");
    }
}

function getVehicleByPlateNo(plateNo) {
    $.getJSON(
        "/oms/api/vehicles/plate/" + plateNo,
        function (data) {
            populateVehicleInput(data);
        }
    )
}

function vehiclePlateNoInputChanged(event, ui){
    if(ui.item === null) {
        var plateNo = $("#vehicle-plate-no").val();
        getVehicleByPlateNo(plateNo);
    }
}

$("#vehicle-plate-no").autocomplete({
    source :  getVehiclesByPlateNo,
    minLength :3,
    select : function (event, ui) {
        getVehicleByPlateNo(ui.item.value);
    },
    change : vehiclePlateNoInputChanged
});

function getDriversByLicenseNo(request, response) {
    $.getJSON(
        "/oms/api/drivers?licenseNo=" + request.term,
        function (data) {
            response(data);
        });
}

function populateDriverInput(driver){
    console.log('driver in populateDriverInput ' + driver);
    if(driver !== null) {
        $("#driver-id").val(driver["id"]);
        $("#driver-fn").val(driver["firstName"]).attr("readonly", "readonly");
        $("#driver-mn").val(driver["middleName"]).attr("readonly", "readonly");
        $("#driver-ln").val(driver["lastName"]).attr("readonly", "readonly");
        $("#driver-license-type").val(driver["licenseType"]).attr("readonly", "readonly");
    }
    else{
        $("#driver-id").val("");
        $("#driver-fn").val("").removeAttr("readonly");
        $("#driver-mn").val("").removeAttr("readonly");
        $("#driver-ln").val("").removeAttr("readonly");
        $("#driver-license-type").removeAttr("readonly");
    }
}

function getDriverByLicenseNo(licenseNo){
    console.log("getting driver with license no " + licenseNo );
    $.getJSON(
        "/oms/api/drivers/" + licenseNo,
        function (data) {
            populateDriverInput(data);
        }
    )
}

function driverLicenseNoInputChanged(event, ui){
    console.log('ui.item in driverLicenseNoInputChanged ' + ui.item);
    if(ui.item === null) {
        var licenseNo = $("#driver-license-no").val();
        getDriverByLicenseNo(licenseNo);
    }
}

$("#driver-license-no").autocomplete({
    source : getDriversByLicenseNo,
    minLength : 3,
    select : function(event,ui){
        getDriverByLicenseNo(ui.item.value);
    },
    change : driverLicenseNoInputChanged
});

function getOffenceCodesList(request,response){
    var offenceCode = request.term;
    var offenceCodeParts = offenceCode.split(' ', 4);
    var sectionHeaderLabel = offenceCodeParts[0];
    var level = offenceCodeParts[1];
    var penaltyAmount = (offenceCodeParts[2] === undefined)? '' : offenceCodeParts[2];

    var url = "/oms/api/offence-code?sectionHeaderLabel=" + sectionHeaderLabel +
        "&level=" + level + "&penaltyAmount=" + penaltyAmount;
    $.getJSON(
        url,
        function (data) {
            response(data);
        }
    );
}

function populateOffenceCodeData(event,offenceCode){
    console.log('populating offence code');
    var offenceCodeInputColumnTd = $(event.target).parent();
    var offenceCodeHiddenColumnTd = offenceCodeInputColumnTd.prev();
    var offenceCodeDescriptionColumnTd = offenceCodeInputColumnTd.next();

    var id = offenceCodeHiddenColumnTd.children("input:nth-child(1)");
    var sectionHeaderLabel = offenceCodeHiddenColumnTd.children("input:nth-child(2)");
    var level = offenceCodeHiddenColumnTd.children("input:nth-child(3)");
    var penaltyAmount = offenceCodeHiddenColumnTd.children("input:nth-child(4)");
    var numberLabel = offenceCodeHiddenColumnTd.children("input:nth-child(5)");
    var offenderType = offenceCodeHiddenColumnTd.children("input:nth-child(6)");
    var description = offenceCodeDescriptionColumnTd.children("input:nth-child(1)");

    if(offenceCode !== null) {
        //populate hidden columns
        id.val(offenceCode["id"]);
        sectionHeaderLabel.val(offenceCode["sectionHeaderLabel"]);
        level.val(offenceCode["level"]);
        penaltyAmount.val(offenceCode["penaltyAmount"]);
        numberLabel.val(offenceCode["numberLabel"]);
        offenderType.val(offenceCode["offenderType"]);

        //populate description
        description.val(offenceCode["description"]);
    }
    else{
        //populate hidden columns
        id.val("");
        sectionHeaderLabel.val("");
        level.val("");
        penaltyAmount.val("");
        numberLabel.val("");
        offenderType.val("");

        //populate description
        description.val("");
    }
}

function getOffenceCode(event, fullyQualifiedOffenceCode) {

    var offenceCodeParts = fullyQualifiedOffenceCode.split(' ', 4);
    //check if is really fully qualified
    if(offenceCodeParts.length === 4) {
        var sectionHeaderLabel = offenceCodeParts[0];
        var level = offenceCodeParts[1];
        var penaltyAmount = offenceCodeParts[2];
        var numberLabel = offenceCodeParts[3];

        var url = "/oms/api/offence-code/get?sectionHeaderLabel=" + sectionHeaderLabel +
            "&level=" + level + "&penaltyAmount=" + penaltyAmount +
            "&numberLabel=" + numberLabel;
        $.getJSON(
            url,
            function (data) {
                populateOffenceCodeData(event, data);
            }
        )
    }
    else{
        populateOffenceCodeData(event, null);
    }
}

function offenceCodeInputChanged(event, ui){
    if (ui.item === null) {
        var offenceCode = $(event.target).val();
        getOffenceCode(event,offenceCode);
    }
}

var offenceCodesBody = $("#offence-codes-body");
var offenceCodeInputs = offenceCodesBody.find("tr td:nth-child(2)").children("input");

var offenceCodeAutoComplete = {
    source :  getOffenceCodesList,
    minLength :6,
    select : function (event, ui) {
        getOffenceCode(event, ui.item.value);
    },
    change : offenceCodeInputChanged
};

offenceCodeInputs.autocomplete(offenceCodeAutoComplete);

/*
 * Loads the offence code name like 9.3.1 1 50 1 into the input for full code after each visit to the server
 * using data in the hidden fields of the first column in offence code table
 * runs every time after the page loads and is called in document ready function at the bottom
 * needed when a user enters bad data and the server rejects it so the filled must be repopulated
*/
function loadQualifiedName() {
    offenceCodeInputs.each(function () {
        var offenceCodeIdInput = $(this).parent().prev().children("input:nth-child(1)");
        if(offenceCodeIdInput.val() !== null && offenceCodeIdInput.val() !== undefined && offenceCodeIdInput.val() !== ''){
            var offenceCodeSectionHeaderLabelInput = offenceCodeIdInput.next();
            var offenceCodeLevelInput = offenceCodeSectionHeaderLabelInput.next();
            var offenceCodePenaltyAmount = offenceCodeLevelInput.next();
            var offenceCodeNumberLabel = offenceCodePenaltyAmount.next();

            var fullQualifiedOffenceCodeName =  offenceCodeSectionHeaderLabelInput.val() + ' ' +
                offenceCodeLevelInput.val() + ' ' +
                offenceCodePenaltyAmount.val() + ' ' +
                offenceCodeNumberLabel.val();
            $(this).val(fullQualifiedOffenceCodeName);
        }
        else{
            $(this).val('');
            var offenceCodeDescriptionInput = $(this).parent().next().children("input:nth-child(1)");
            offenceCodeDescriptionInput.val('');
        }
    });
}

//variable to hold number of offence codes currently on the page
var offenceCodes = offenceCodesBody.find("tr").length;
function addOffenceCode(){
    var newOffenceCode =
        $("<tr class='d-flex'>\n" +
            "\t<td class='d-none'>\n" +
            "\t\t<input type=\"hidden\" id=\"offenceCodeModels" + offenceCodes + ".id\" name=\"offenceCodeModels[" + offenceCodes + "].id\" value=\"\">\n" +
            "\t\t<input type=\"hidden\" id=\"offenceCodeModels" + offenceCodes + ".sectionHeaderLabel\" name=\"offenceCodeModels[" + offenceCodes + "].sectionHeaderLabel\" value=\"\">\n" +
            "\t\t<input type=\"hidden\" id=\"offenceCodeModels" + offenceCodes + ".level\" name=\"offenceCodeModels[" + offenceCodes + "].level\" value=\"\">\n" +
            "\t\t<input type=\"hidden\" id=\"offenceCodeModels" + offenceCodes + ".penaltyAmount\" name=\"offenceCodeModels[" + offenceCodes + "].penaltyAmount\" value=\"\">\n" +
            "\t\t<input type=\"hidden\" id=\"offenceCodeModels" + offenceCodes + ".numberLabel\" name=\"offenceCodeModels[" + offenceCodes + "].numberLabel\" value=\"\">\n" +
            "\t\t<input type=\"hidden\" id=\"offenceCodeModels" + offenceCodes + ".offenderType\" name=\"offenceCodeModels[" + offenceCodes + "].offenderType\" value=\"\">\n" +
            "\t</td>\n" +
            "\t<td class='col-sm-4 col-md-3'>\n" +
            "\t\t<input class=\"form-control ui-autocomplete-input\" placeholder=\"" + fullCode + "\">\n" +
            "\t</td>\n" +
            "\t<td class='col-sm-7 col-md-8'>\n" +
            "\t\t<input class=\"form-control\" placeholder=\"" + description + "\" readonly=\"readonly\" id=\"offenceCodeModels" + offenceCodes + ".description\" name=\"offenceCodeModels[" + offenceCodes + "].description\" value='' required>\n" +
            "\t</td>\n" +
            "\t<td class='col-sm-1 col-md-1'>\n" +
            "\t\t<button class=\"btn btn-outline-light\" type=\"button\" onclick=\"removeOffenceCode(event)\"><span class=\"fas fa-minus text-danger\"></span></button>\n" +
            "\t</td>\n" +
            "</tr>");

    var newOffenceCodeInput = newOffenceCode.children("td:nth-child(2)").children("input");
    newOffenceCodeInput.autocomplete(offenceCodeAutoComplete);
    newOffenceCode.appendTo("#offence-codes-body");

    offenceCodes++;

    //Remove button in previous to last row
    var previousToLastRow = $("#offence-codes-body").children("tr:nth-child(" + (offenceCodes - 1) + ")");
    var buttonInPreviousToLastRow = previousToLastRow.children("td:nth-child(4)").children();
    buttonInPreviousToLastRow.remove();
}

function removeOffenceCode(event) {
    $(event.target).parent().parent().remove();
    offenceCodes--;
    console.log("offence Codes = " + offenceCodes);
    console.log(event.target);
    if(offenceCodes > 1){
        var lastRow = $("#offence-codes-body").children("tr:nth-child(" + offenceCodes + ")");
        var lastRowButtonColumn = lastRow.children("td:nth-child(4)");
        $("<button class=\"btn btn-outline-light\" type=\"button\" onclick=\"removeOffenceCode(event)\"><span class=\"fas fa-minus text-danger\"></span></button>")
            .appendTo(lastRowButtonColumn);
    }
}

$(document).ready(function(){
    loadQualifiedName();

    $("#vehicle-plate-no").mask("1-A22222-BB", {
        translation : {
            1: {pattern: /[1-5]/},
            A: {pattern: /[aA-zZ]/, optional: true},
            2: {pattern: /[0-9]/},
            B: {pattern: /[aA-zZ]/}
        },
        placeholder : "1-A12345-AA"
    });
    $("#vehiclePlateTaken").mask("1-A22222-BB", {
        translation : {
            1: {pattern: /[1-5]/},
            A: {pattern: /[aA-zZ]/, optional: true},
            2: {pattern: /[0-9]/},
            B: {pattern: /[aA-zZ]/}
        },
        placeholder : "1-A12345-AA"
    });

    $("#driver-license-no").mask("000000");
    $("#driversLicenseTaken").mask("000000");

    // offenceCodeInputs.mask("0.0.0 0 A");//, {
    //     // translation: {
    //     //     A: {pattern: /[0-9]/, recursive:true}
    //     //   //  E: {pattern: /[0-9]?/, optional: true}
    //     // }
    // //})
});

function vehiclePlateNoKeyUp(event){
    switch(event.target.id){
        case "vehicle-plate-no" :
            var vehiclePlateNo = $("#vehicle-plate-no");
            vehiclePlateNo.val(vehiclePlateNo.val().toUpperCase());
            break;
        case "vehiclePlateTaken" :
            var vehiclePlateTaken = $("#vehiclePlateTaken");
            vehiclePlateTaken.val(vehiclePlateTaken.val().toUpperCase());
            break;
    }
}

function proofDocumentOnChange(event){
    switch(event.target.id){
        case "isDriversLicenseTaken" :
            var driversLicenseTaken = $("#driversLicenseTaken");
            $('#isDriversLicenseTaken').is(':checked')? driversLicenseTaken.removeAttr("disabled").attr("required", "required") : driversLicenseTaken.attr("disabled", "disabled").removeAttr("required").val('');
            break;
        case "isVehiclePlateTaken" :
            var vehiclePlateTaken = $("#vehiclePlateTaken");
            $('#isVehiclePlateTaken').is(':checked')? vehiclePlateTaken.removeAttr("disabled").attr("required", "required") : vehiclePlateTaken.attr("disabled", "disabled").removeAttr("required").val('');
            break;
        case "isLibreTaken" :
            var libreTaken = $("#libreTaken");
            $('#isLibreTaken').is(':checked')? libreTaken.removeAttr("disabled").attr("required", "required") : libreTaken.attr("disabled", "disabled").removeAttr("required").val('');
            break;
        case "isVehicleBoloTaken" :
            var vehicleBoloTaken = $("#vehicleBoloTaken");
            $('#isVehicleBoloTaken').is(':checked')? vehicleBoloTaken.removeAttr("disabled").attr("required", "required") : vehicleBoloTaken.attr("disabled", "disabled").removeAttr("required").val('');
            break;
    }
}


function getAssociationsByName(request, response) {
    $.getJSON(
        "/oms/api/associations?name=" + request.term,
        function (data) {
            response(data);
        });
}

function populateAssociationInput(association){
    if(association !== null) {
        $("#vehicle-association-id").val(association["id"]);
    }
    else{
        $("#vehicle-association-id").val("");
    }
}

function getAssociationByName(name){
    $.getJSON(
        "/oms/api/associations/name/" + name,
        function (data) {
            populateAssociationInput(data);
        }
    )
}

function associationNameInputChanged(event, ui){
    if(ui.item === null) {
        var name = $("#vehicle-association-name").val();
        getAssociationByName(name);
    }
}

$("#vehicle-association-name").autocomplete({
    source : getAssociationsByName,
    minLength : 3,
    select : function(event,ui){
        getAssociationByName(ui.item.value);
    },
    change : associationNameInputChanged
});