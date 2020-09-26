package com.example.CurrencyConvertor.service.serviceImpl;

import com.example.CurrencyConvertor.model.Conversion;
import com.example.CurrencyConvertor.repository.UserActivityRepository;
import com.example.CurrencyConvertor.service.UserActivityService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class UserActivityServiceImpl implements UserActivityService {

    UserActivityRepository userActivityRepository;

    public UserActivityServiceImpl(UserActivityRepository userActivityRepository){
        this.userActivityRepository = userActivityRepository;
    }


    @Override
    public void saveUserActivity(Conversion conversion) {

        LocalDateTime now = LocalDateTime.now();
        conversion.setConversionDate(now);
        userActivityRepository.save(conversion);
    }

    @Override
    public void deleteAllByConversionDateBefore(LocalDateTime localDateTime) {

        userActivityRepository.deleteAllByConversionDateBefore(localDateTime);
    }


}
