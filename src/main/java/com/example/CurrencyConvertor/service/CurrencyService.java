package com.example.CurrencyConvertor.service;

import com.example.CurrencyConvertor.model.Conversion;
import com.example.CurrencyConvertor.model.Currency;
import com.example.CurrencyConvertor.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface CurrencyService  {


    ArrayList<Currency> findAllCurrencies();
    Currency findByShortCurrencyName(String shortCurrencyName);
    Double convert(Conversion conversion);






}
