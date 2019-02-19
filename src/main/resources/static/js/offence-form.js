var this_js_script = $('script[src*=offence-form]');
var next = this_js_script.attr('data-next');
var submit = this_js_script.attr('data-submit');
var fullCode = this_js_script.attr('data-full-code');
var description = this_js_script.attr('data-description');

if (typeof next === "undefined" )
    next = 'Next';
if (typeof submit === "undefined" )
    submit = 'Submit';
if (typeof fullCode === "undefined" )
    fullCode = 'Full Code';
if(typeof description === "undefined")
    description = 'Description';


/*
 * Page navigation code
 */
var currentTab = 0; // Current tab is set to be the first tab (0)
showTab(currentTab); // Display the current tab

var validator = $("#regForm").validate({
    errorClass: "invalid-feedback is-invalid"
});

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
        "/oms/vehicle?plateNo=" + request.term,
        function (data) {
            response(data);
        });
}

function populateVehicleInput(vehicle){
    if(vehicle !== null) {
        $("#vehicle-id").val(vehicle["id"]);
        $("#vehicle-type").val(vehicle["type"]).attr("readonly", "readonly");
        $("#vehicle-plate-no").val(vehicle["plateNo"]);
        $("#vo-libreNo").val(vehicle["vehicleOwnershipModels"][0]["libreNo"]).attr("readonly", "readonly");
        $("#vo-fName").val(vehicle["vehicleOwnershipModels"][0]["personModelOwners"][0]["firstName"]).attr("readonly", "readonly");
        $("#vo-mName").val(vehicle["vehicleOwnershipModels"][0]["personModelOwners"][0]["middleName"]).attr("readonly", "readonly");
        $("#vo-lName").val(vehicle["vehicleOwnershipModels"][0]["personModelOwners"][0]["lastName"]).attr("readonly", "readonly");
        //$("#vehicle-association-id").val(vehicle["associationModel"]["id"]);
        $("#vehicle-association-name").val(vehicle["associationModel"]["name"]).attr("readonly", "readonly");
        $("#vehicle-side-no").val(vehicle["sideNo"]).attr("readonly", "readonly");
    }
    else{
        $("#vehicle-id").val("");
        $("#vehicle-type").val("").removeAttr("readonly");
        $("#vo-libreNo").val("").removeAttr("readonly");
        $("#vo-fName").val("").removeAttr("readonly");
        $("#vo-mName").val("").removeAttr("readonly");
        $("#vo-lName").val("").removeAttr("readonly");
        //$("#vehicle-association-id").val(");
        $("#vehicle-association-name").val("").removeAttr("readonly");
        $("#vehicle-side-no").val("").removeAttr("readonly");
    }
}

function getVehicleByPlateNo(plateNo) {
    $.getJSON(
        "/oms/vehicle/plate/" + plateNo,
        function (data) {
            populateVehicleInput(data);
        }
    )
}

function vehiclePlateNoInputChanged(event, ui){
    var plateNo = $("#vehicle-plate-no").val();
    if(ui.item === null)
        getVehicleByPlateNo(plateNo);
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
    var licenseNo = $("#driver-license-no").val();
    if(ui.item === null)
        getDriverByLicenseNo(licenseNo);
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
    //alert("getting data from url : " + url);
    $.getJSON(
        url,
        function (data) {
            response(data);
        }
    );
}

function populateOffenceCodeData(event,data){
    var offenceCodeInputColumnTd = $(event.target).parent();

    var offenceCodeHiddenColumnTd = offenceCodeInputColumnTd.prev();
    offenceCodeHiddenColumnTd.children("input:nth-child(1)").val(data["id"]);
    offenceCodeHiddenColumnTd.children("input:nth-child(2)").val(data["sectionHeaderLabel"]);
    offenceCodeHiddenColumnTd.children("input:nth-child(3)").val(data["level"]);
    offenceCodeHiddenColumnTd.children("input:nth-child(4)").val(data["penaltyAmount"]);
    offenceCodeHiddenColumnTd.children("input:nth-child(5)").val(data["numberLabel"]);
    offenceCodeHiddenColumnTd.children("input:nth-child(6)").val(data["offenderType"]);

    offenceCodeInputColumnTd.next().children("input:nth-child(1)").val(data["description"]);
}

function getOffenceCode(event, ui) {
    var fullyQualifiedOffenceCode = ui.item.value;
    var offenceCodeParts =fullyQualifiedOffenceCode.split(' ', 4);
    var sectionHeaderLabel = offenceCodeParts[0];
    var level = offenceCodeParts[1];
    var penaltyAmount = offenceCodeParts[2];
    var numberLabel = offenceCodeParts[3];

    var url = "/oms/api/offence-code/get?sectionHeaderLabel=" + sectionHeaderLabel +
        "&level=" + level + "&penaltyAmount=" + penaltyAmount +
        "&numberLabel=" + numberLabel;
    //alert("getting data from url : " + url);
    $.getJSON(
        url,
        function (data) {
            //set the id and also good for client side checks to see all offence codes
            //have same offenderType and sectionHeaderLabel
            populateOffenceCodeData(event, data);
        }
    )
}

var offenceCodesBody = $("#offence-codes-body");
var offenceCodeInputs = offenceCodesBody.find("tr td:nth-child(2)").children("input");

var offenceCodeAutoComplete = {
    source :  getOffenceCodesList,
    minLength :6,
    select : getOffenceCode
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
            "\t\t<input class=\"form-control\" placeholder=\"" + description + "\" readonly=\"readonly\" id=\"offenceCodeModels" + offenceCodes + ".description\" name=\"offenceCodeModels[" + offenceCodes + "].description\" value=\"\">\n" +
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

    $("#driver-license-no").mask("000000");

    // offenceCodeInputs.mask("0.0.0 0 A");//, {
    //     // translation: {
    //     //     A: {pattern: /[0-9]/, recursive:true}
    //     //   //  E: {pattern: /[0-9]?/, optional: true}
    //     // }
    // //})
});

function vehiclePlateNoKeyUp(){
    var vehiclePlateNo = $("#vehicle-plate-no");
    vehiclePlateNo.val(vehiclePlateNo.val().toUpperCase());
}

function proofDocumentOnChange(event){
    switch(event.target.id){
        case "isDriversLicenseTaken" :
            var driversLicenseTaken = $("#driversLicenseTaken");
            $('#isDriversLicenseTaken').is(':checked')? driversLicenseTaken.removeAttr("disabled") : driversLicenseTaken.attr("disabled", "disabled");;
            break;
        case "isVehiclePlateTaken" :
            var vehiclePlateTaken = $("#vehiclePlateTaken");
            $('#isVehiclePlateTaken').is(':checked')? vehiclePlateTaken.removeAttr("disabled") : vehiclePlateTaken.attr("disabled", "disabled");;
            break;
        case "isLibreTaken" :
            var libreTaken = $("#libreTaken");
            $('#isLibreTaken').is(':checked')? libreTaken.removeAttr("disabled") : libreTaken.attr("disabled", "disabled");;
            break;
        case "isVehicleBoloTaken" :
            var vehicleBoloTaken = $("#vehicleBoloTaken");
            $('#isVehicleBoloTaken').is(':checked')? vehicleBoloTaken.removeAttr("disabled") : vehicleBoloTaken.attr("disabled", "disabled");;
            break;
    }
}