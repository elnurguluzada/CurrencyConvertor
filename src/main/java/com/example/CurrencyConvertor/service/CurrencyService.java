package com.example.CurrencyConvertor.service;

import com.example.CurrencyConvertor.model.Currency;
import com.example.CurrencyConvertor.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

@Service
public interface CurrencyService  {


    Currency findByShortCurrencyName(String shortCurName);
    Currency findByCurrencyID(Integer id);
    Currency findByCurrencyNameEN(String currencyNameEN);
    Currency findByCurrencyNameLT(String currencyNameLT);






}
