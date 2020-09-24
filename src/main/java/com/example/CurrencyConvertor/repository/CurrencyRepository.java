package com.example.CurrencyConvertor.repository;

import com.example.CurrencyConvertor.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency , Integer> {

     @Query("SELECT c FROM Currency c WHERE c.exchangeRate > 0  ")
     ArrayList<Currency> findAllCurrencies();
     Currency findByShortCurrencyName(String shortCurrencyName);
     boolean existsByShortCurrencyName(String shortCurrencyName);

}
