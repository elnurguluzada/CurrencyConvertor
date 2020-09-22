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


        String url = "http://lb.lt/webservices/FxRates/FxRates.asmx/getCurrencyList";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> response = restTemplate.exchange(url , HttpMethod.GET, entity,String.class);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;

        try {

            builder = factory.newDocumentBuilder();
            Document xmlDocument = builder.parse(new ByteArrayInputStream(response.getBody().getBytes()));
            xmlDocument.getDocumentElement().normalize();
            Element docElement = xmlDocument.getDocumentElement();
            NodeList nodeList = docElement.getElementsByTagName("CcyNtry");


            for (int i =0; i< nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    Currency currency = new Currency(
                            elem.getElementsByTagName("Ccy").item(0).getTextContent(),
                            elem.getElementsByTagName("CcyNm").item(0).getTextContent(),
                            elem.getElementsByTagName("CcyNm").item(1).getTextContent());
                }



            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @GetMapping("/addCurrencyExchangeRates")
    public void addExchangeRate(){


        String url = "http://lb.lt/webservices/FxRates/FxRates.asmx/getCurrentFxRates?tp=lt";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> response = restTemplate.exchange(url , HttpMethod.GET, entity,String.class);


        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;

        try {
        builder = factory.newDocumentBuilder();
        Document xmlDocument = builder.parse(new ByteArrayInputStream(response.getBody().getBytes()));
        xmlDocument.getDocumentElement().normalize();
        Element docElement = xmlDocument.getDocumentElement();
        NodeList nodeList = docElement.getElementsByTagName("FxRate");


            for (int i =0; i< nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    Currency currency = null;
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                        currency = new Currency(

                                simpleDateFormat.parse(elem.getElementsByTagName("Dt").item(0).getTextContent()),
                                elem.getElementsByTagName("Ccy").item(1).getTextContent(),
                                Float.parseFloat(elem.getElementsByTagName("Amt").item(1).getTextContent()));

//                    System.out.println("Shor currency name = " + currency.getShortCurrencyName()  + " , rate = " + currency.getExchangeRate() + " , date = " + currency.getDate());
                }




            }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

}
