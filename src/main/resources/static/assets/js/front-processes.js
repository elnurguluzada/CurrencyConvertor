function getAllCurrencies() {

    var fromCurrencies = document.getElementById("fromCurrency");
    var toCurrencies = document.getElementById("toCurrency");

    var xmlRequest = new XMLHttpRequest();

    xmlRequest.onreadystatechange = function () {
        if(this.readyState === 4 && this.status === 200) {
            var jsonCurrencies = JSON.parse(this.responseText);
            fillCurrencies(fromCurrencies, jsonCurrencies);
            fillCurrencies(toCurrencies, jsonCurrencies);
            fillButtonInfo();
        }
    };

    $.ajax({
        type: "GET",
        url: "/currencyList",
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
        error: function(data) {
            alert('There was a problem converting!');
        }
    });


    xmlRequest.open("GET", "/currencyList", true);
    xmlRequest.send();
}

function fillCurrencies(dropdown, jsonParsed) {
    for (var i = 0; i < jsonParsed.length; i++) {
        var option = document.createElement("option");
        option.setAttribute("class", "dropdown-item");
        option.setAttribute("id" , "optionID")
        var text = document.createTextNode(jsonParsed[i].shortCurrencyName );

        option.appendChild(text);
        option.setAttribute("value", jsonParsed[i].shortCurrencyName);
        dropdown.appendChild(option);
    }
}

function filterFunction(input, dropdown) {
    if(input == null) {
        return;
    }
    var input, filter, ul, li, a, i;
    input = document.getElementById(input);
    filter = input.value.toUpperCase();
    div = document.getElementById(dropdown);
    a = div.getElementsByTagName("a");
    for (i = 0; i < a.length; i++) {
        if (a[i].innerHTML.toUpperCase().indexOf(filter) > -1) {
            a[i].style.display = "";
        } else {
            a[i].style.display = "none";
        }
    }
}

function fillButtonInfo() {

    var listAs = document.querySelectorAll(".dropdown-menu a");

    for(var i = 0; i < listAs.length; i++) {
        var a = listAs[i];
        a.addEventListener("click", function(evt) {
            var a = evt.target;
            var button = a.parentElement.previousElementSibling;
            button.innerHTML = a.getAttribute("value");
        })
    }
}



$("#convertButton").click(function() {


    // Get from
    var from = $('#fromCurrency').find(":selected").text();

    // Get to
    var to = $('#toCurrency').find(":selected").text();


    // Get value
    var value = $('#amout').val();






    // Make the http request using Ajax
    $.ajax({
        type: "POST",
        url: "/currencyConversion",
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
        error: function(data) {
            alert('There was a problem converting!');
        }
    });



});