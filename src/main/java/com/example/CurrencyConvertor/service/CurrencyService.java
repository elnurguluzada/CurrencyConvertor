package com.example.CurrencyConvertor.service;

import com.example.CurrencyConvertor.model.Conversion;
import com.example.CurrencyConvertor.model.Currency;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface CurrencyService  {


    ArrayList<Currency> findAllCurrencies();
    Currency findByShortCurrencyName(String shortCurrencyName);
    Double convert(Conversion conversion);
    boolean existsByShortCurrencyName(String shortCurrencyName);
    void save(Currency currency);





}
