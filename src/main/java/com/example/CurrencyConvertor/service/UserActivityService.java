package com.example.CurrencyConvertor.service;

import com.example.CurrencyConvertor.model.Conversion;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public interface UserActivityService {

    void saveUserActivity(Conversion conversion);
    void deleteAllByConversionDateBefore(LocalDateTime localDateTime);
}
