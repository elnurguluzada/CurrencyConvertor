package com.example.CurrencyConvertor.controller;



import com.example.CurrencyConvertor.model.Conversion;
import com.example.CurrencyConvertor.model.Currency;
import com.example.CurrencyConvertor.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController
public class CurrencyController {




    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController( CurrencyService currencyService) {
        this.currencyService = currencyService;


    }






    @GetMapping("/addCurrencyExchangeRates")
    public ModelAndView addExchangeRate(){


        ModelAndView modelAndView = new ModelAndView();


        Currency currency = new Currency();
        modelAndView.addObject("currency" , currency);

         modelAndView.setViewName("/common/index");

        return modelAndView;
    }



    @PostMapping(value = "/currencyConvertion" , produces = {"application/json"})
    public ResponseEntity<Double> convertCurrency(@RequestBody Conversion conversion){

           Optional<Double> result = Optional.ofNullable(currencyService.convert(conversion));
            if(result.isPresent()){
                return new ResponseEntity<>(result.get() , HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }



    @ModelAttribute("currencyList")
    public ArrayList<Currency> getCurrencyList() {

        ArrayList<Currency> currencyList =  new ArrayList<>();

        currencyList= currencyService.findAllCurrencies();

        return currencyList;
    }

}
