package com.example.CurrencyConvertor.controller;



import com.example.CurrencyConvertor.model.Currency;
import com.example.CurrencyConvertor.repository.CurrencyRepository;
import com.example.CurrencyConvertor.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

@RestController
public class CurrencyController {




    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyController( CurrencyRepository currencyRepository) {

        this.currencyRepository = currencyRepository;
    }


    @GetMapping("/getCurrencies")
    public void addCurrencies(){

    }



    @GetMapping("/addCurrencyExchangeRates")
    public void addExchangeRate(){

    }

}
