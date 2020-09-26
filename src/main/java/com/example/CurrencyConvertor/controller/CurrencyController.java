package com.example.CurrencyConvertor.controller;



import com.example.CurrencyConvertor.model.Conversion;
import com.example.CurrencyConvertor.model.Currency;
import com.example.CurrencyConvertor.service.CurrencyService;
import com.example.CurrencyConvertor.service.UserActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController
public class CurrencyController {




    private final CurrencyService currencyService;
    private final UserActivityService userActivityService;

    @Autowired
    public CurrencyController(CurrencyService currencyService, UserActivityService userActivityService) {
        this.currencyService = currencyService;
        this.userActivityService = userActivityService;
    }


    @GetMapping("/")
    public ModelAndView getIndexPage(){


        ModelAndView modelAndView = new ModelAndView();


        Currency currency = new Currency();
        modelAndView.addObject("currency" , currency);

         modelAndView.setViewName("/common/index");

        return modelAndView;
    }



    @PostMapping(value = "/conversion", produces = {"application/json"})
    public ResponseEntity<Double> convertCurrency(@RequestBody Conversion conversion ){




            Optional<Double> result = Optional.ofNullable(currencyService.convert(conversion));
            conversion.setConversionResult(result.get().doubleValue());

            if(result.isPresent()){
                userActivityService.saveUserActivity(conversion);
                return new ResponseEntity<>(result.get() , HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }



    @GetMapping(value = "/currencyList" , produces = { "application/json" })
    public ResponseEntity<List<Currency>> getCurrencyList() {
        return new ResponseEntity<>(currencyService.findAllCurrencies(), HttpStatus.OK);
    }

}
