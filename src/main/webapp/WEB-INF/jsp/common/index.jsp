<!DOCTYPE html>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html lang="en">

<!-- Mirrored from colorlib.com/preview/theme/cryptos/ by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 23 Sep 2020 16:32:14 GMT -->
<!-- Added by HTTrack --><meta http-equiv="content-type" content="text/html;charset=UTF-8" /><!-- /Added by HTTrack -->
<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


    <title>Cryptos - Cryptocurrency &amp; Mining Template</title>

    <link rel="icon" href="../assets/img/core-img/favicon.ico">

    <link rel="stylesheet" href="../assets/css/style.css">


    <style>
        section {
            height: 100vh;
        }

    </style>
</head>
<body>

<div id="preloader">
    <i class="circle-preloader"></i>
</div>

<header class="header-area">
</header>





<section class="currency-calculator-area section-padding-100 bg-img bg-overlay" style="background-image: url(../assets/img/currencies.jpeg);">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="section-heading text-center white mx-auto">
                    <h3 class="mb-4">Currency Convertor</h3>
                </div>
            </div>
        </div>

        <div class="flex-row">
            <div class="col-12">
                <div class="currency-calculator mb-15 clearfix">

                    <form  class="d-flex align-items-center justify-content-center">
                        <div class="calculator-first-part d-flex align-items-center dropdown">
                            <input id="amout" type="number" name="inputNumber" placeholder="0">
                            <select id="fromCurrency">
                                <option >From...</option>
                            </select>
                        </div>



                        <div class="equal-sign">=</div>

                        <div class="calculator-first-part d-flex align-items-center">
                            <input disabled id="conversionResult" type="number" name="inputNumber" placeholder="0">
                            <select id="toCurrency">
                                <option>To...</option>
                            </select>
                        </div>


                    </form>
                    <div>
                        <button class="btn cryptos-btn" type="button" id="convertButton">CONVERT</button>
                    </div>
                </div>

            </div>
        </div>

    </div>
</section>





<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
<script src="../assets/js/jquery/jquery-2.2.4.min.js" type="1491f53a9dd1d6926d797821-text/javascript"></script>
<script src="../assets/js/bootstrap/popper.min.js" type="1491f53a9dd1d6926d797821-text/javascript"></script>
<script src="../assets/js/bootstrap/bootstrap.min.js" type="1491f53a9dd1d6926d797821-text/javascript"></script>
<script src="../assets/js/plugins/plugins.js" type="1491f53a9dd1d6926d797821-text/javascript"></script>
<script src="../assets/js/active.js" type="1491f53a9dd1d6926d797821-text/javascript"></script>
<script src="../assets/js/front-processes.js"></script>

<script async src="https://www.googletagmanager.com/gtag/js?id=UA-23581568-13" type="1491f53a9dd1d6926d797821-text/javascript"></script>
<script type="1491f53a9dd1d6926d797821-text/javascript">
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-23581568-13');
</script>
<script src="../assets/ajax.cloudflare.com/cdn-cgi/scripts/7089c43e/cloudflare-static/rocket-loader.min.js" data-cf-settings="1491f53a9dd1d6926d797821-|49" defer=""></script><script defer src="../assets/static.cloudflareinsights.com/beacon.min.js" data-cf-beacon='{"rayId":"5d75a91c6a8bffdc","version":"2020.9.1","si":10}'></script>
<script>
    getAllCurrencies();
</script>
</body>

<!-- Mirrored from colorlib.com/preview/theme/cryptos/ by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 23 Sep 2020 16:32:30 GMT -->
</html>