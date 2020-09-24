package com.example.CurrencyConvertor.service.serviceImpl;

import com.example.CurrencyConvertor.model.Conversion;
import com.example.CurrencyConvertor.model.Currency;
import com.example.CurrencyConvertor.repository.CurrencyRepository;
import com.example.CurrencyConvertor.service.CurrencyService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;


@Service
public class CurrencyServiceImpl implements CurrencyService    {


    CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository){
        this.currencyRepository = currencyRepository;
    }


    @Override
    public ArrayList<Currency> findAllCurrencies() {

        ArrayList<Currency> currencyArrayList = currencyRepository.findAllCurrencies();
        currencyArrayList.sort(Comparator.comparing(Currency::getShortCurrencyName));
        return currencyArrayList ;
    }

    @Override
    public Currency findByShortCurrencyName(String shortCurrencyName) {
        return currencyRepository.findByShortCurrencyName(shortCurrencyName);
    }


    public Double convert(Conversion conversion){

       Currency fromCurrency = currencyRepository.findByShortCurrencyName(conversion.getFromCurrency());
       Currency toCurrency = currencyRepository.findByShortCurrencyName(conversion.getToCurrency());


           Double toRate = toCurrency.getExchangeRate();
           System.out.println("toRate = " + toRate);
           Double fromRate = fromCurrency.getExchangeRate();

           Double result = toRate * conversion.getValue() / fromRate;
           if(result < 0) {
               return null;
           }


           return result;
    }



}
