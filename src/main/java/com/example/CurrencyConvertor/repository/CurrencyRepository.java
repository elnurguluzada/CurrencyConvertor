package com.example.CurrencyConvertor.repository;

import com.example.CurrencyConvertor.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency , Integer> {

    public Currency findByShortCurrencyName(String shortCurrencyName);




}
