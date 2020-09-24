function getAllCurrencies() {

    var fromCurrency = document.getElementById("fromCurrency");
    var dropdownTo = document.getElementById("toCurrency");

    var xmlRequest = new XMLHttpRequest();

    xmlRequest.onreadystatechange = function () {
        if(this.readyState === 4 && this.status === 200) {
            var jsonCurrencies = JSON.parse(this.responseText);
            fillCurrencies(fromCurrency, jsonCurrencies);
            fillCurrencies(dropdownTo, jsonCurrencies);
           // fillButtonInfo();
        }
    };

    xmlRequest.open("GET", "/currencyList", true);
    xmlRequest.send();
}

function fillCurrencies(dropdown, jsonParsed) {
    for (var i = 0; i < jsonParsed.length; i++) {
        var option = document.createElement("option");
        var text = document.createTextNode(jsonParsed[i].shortCurrencyName );
        option.appendChild(text);
        dropdown.appendChild(option);
    }
}


$("#convertButton").click(function() {

    var from = $('#fromCurrency').find(":selected").text();

    var to = $('#toCurrency').find(":selected").text();

    var value = $('#amout').val();

    // Make the http request using Ajax
    $.ajax({
        type: "POST",
        url: "/conversion",
        data: JSON.stringify({
            "fromCurrency": from,
            "toCurrency": to,
            "value": value
        }),
        contentType: "application/json;",
        success: function(data) {
            console.log(data);
            $("#conversionResult").attr("placeholder", data);
        },
        error: function() {

            toastr.error('There was a problem converting!');
        }
    });



});