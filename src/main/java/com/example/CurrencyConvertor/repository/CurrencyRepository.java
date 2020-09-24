package com.example.CurrencyConvertor.repository;

import com.example.CurrencyConvertor.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency , Integer> {

     @Query("SELECT c FROM Currency c WHERE c.exchangeRate > 0  ")
     ArrayList<Currency> findAllCurrencies();
     Currency findByShortCurrencyName(String shortCurrencyName);

}
