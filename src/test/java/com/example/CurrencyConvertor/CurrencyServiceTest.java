package com.example.CurrencyConvertor;

import com.example.CurrencyConvertor.model.Conversion;
import com.example.CurrencyConvertor.model.Currency;
import com.example.CurrencyConvertor.repository.CurrencyRepository;
import com.example.CurrencyConvertor.service.CurrencyService;

import com.example.CurrencyConvertor.service.serviceImpl.CurrencyServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class CurrencyServiceTest {


    @Mock
    private CurrencyRepository currencyRepository;
    private CurrencyServiceImpl currencyServiceImpl;

    @Before
    public void build(){
        currencyServiceImpl= new CurrencyServiceImpl(currencyRepository);
    }


    @Test
    public void getCurrenciesTestEmpty(){
        Mockito.when(currencyRepository.findAll()).thenReturn(Arrays.asList());
        List<Currency> currencies = currencyServiceImpl.findAllCurrencies();
        Assertions.assertTrue(currencies.isEmpty());
    }




    @Test
    public void getAllCurrenciesTestAgainstNullPointerException(){
        Mockito.when(currencyRepository.findAll()).thenReturn(null);
    }



    @Test
    public void convertWillReturnEmptyIfCurrencyDoesntExist(){

        Currency euro = new Currency("EUR", "Euras" , "Euro" ,  1d, null);
        Currency usd = new Currency("USD", "JAV doleris" , "US dollar", 1.174,null);

        Mockito.when(currencyRepository.findByShortCurrencyName("EUR")).thenReturn(euro);
        Mockito.when(currencyRepository.findByShortCurrencyName("USD")).thenReturn(usd);
        Conversion conversion = new Conversion("EUR" , "USD" , -10);

        Optional<Double> result = Optional.ofNullable(currencyServiceImpl.convert(conversion));


        Assert.assertNotNull(result);
        Assert.assertFalse(result.isPresent());


    }


    @Test
    public void convertShouldGiveResult(){

        Currency euro = new Currency("EUR", "Euras" , "Euro" ,  1d, null);
        Currency usd = new Currency("USD", "JAV doleris" , "US dollar", 1.15795,null);

        Mockito.when(currencyRepository.findByShortCurrencyName("EUR")).thenReturn(euro);
        Mockito.when(currencyRepository.findByShortCurrencyName("USD")).thenReturn(usd);
        Conversion conversion = new Conversion(euro.getShortCurrencyName() , usd.getShortCurrencyName() , 10);
      //  Optional<Double> result = Optional.ofNullable(currencyServiceImpl.convert(conversion));


        Optional<Double> result = Optional.ofNullable(this.currencyServiceImpl.convert(conversion));


        Assert.assertTrue(result.isPresent());
        double desiredValue = Math.round(8.635951466 * 100.0) / 100.0;
        double obtainedValue = Math.round(result.get()* 100.0) / 100.0;
        Assert.assertTrue(desiredValue == obtainedValue);


    }
}
