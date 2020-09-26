package com.example.CurrencyConvertor.repository;

import com.example.CurrencyConvertor.model.Conversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public interface UserActivityRepository extends JpaRepository<Conversion , Integer> {



    @Transactional
    void deleteAllByConversionDateBefore(LocalDateTime localDateTime);
}
