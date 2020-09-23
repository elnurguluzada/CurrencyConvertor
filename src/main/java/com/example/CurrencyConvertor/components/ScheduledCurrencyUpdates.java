package com.example.CurrencyConvertor.components;


import com.example.CurrencyConvertor.model.Currency;
import com.example.CurrencyConvertor.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;


@Component
public class ScheduledCurrencyUpdates {

    @Autowired
    CurrencyRepository currencyRepository;

    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders headers = new HttpHeaders();


    private Element getElement(ResponseEntity<String> response, DocumentBuilderFactory factory) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilder builder;
        builder = factory.newDocumentBuilder();
        Document xmlDocument = builder.parse(new ByteArrayInputStream(response.getBody().getBytes()));
        xmlDocument.getDocumentElement().normalize();
        Element docElement = xmlDocument.getDocumentElement();
        return docElement;
    }



    private HttpEntity<String> getEntity(){

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        return entity;
    }




    @Scheduled(fixedRate = 300000)
    private void updateCurrencyList()  {

        String url = "http://lb.lt/webservices/FxRates/FxRates.asmx/getCurrencyList";

        ResponseEntity<String> response = restTemplate.exchange(url , HttpMethod.GET, getEntity(),String.class);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {

            NodeList nodeList = getElement(response ,factory ).getElementsByTagName("CcyNtry");


            for (int i =0; i< nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    Currency currency = new Currency(
                            elem.getElementsByTagName("Ccy").item(0).getTextContent(),
                            elem.getElementsByTagName("CcyNm").item(0).getTextContent(),
                            elem.getElementsByTagName("CcyNm").item(1).getTextContent());

                   if(!currencyRepository.existsByShortCurrencyName(currency.getShortCurrencyName())){
                       currencyRepository.save(currency);
                   }
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


    @Scheduled(fixedRate = 86400000)
    private void updateExchangeRates(){

        String url = "http://lb.lt/webservices/FxRates/FxRates.asmx/getCurrentFxRates?tp=lt";



        ResponseEntity<String> response = restTemplate.exchange(url , HttpMethod.GET, getEntity(),String.class);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();



        try {

            NodeList nodeList = getElement(response,factory).getElementsByTagName("FxRate");

            for (int i =0; i< nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                if(node.getNodeType() == Node.ELEMENT_NODE) {

                    Element elem = (Element) node;

                    Currency currency = currencyRepository.findByShortCurrencyName( elem.getElementsByTagName("Ccy").item(1).getTextContent());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    currency.setDate( simpleDateFormat.parse(elem.getElementsByTagName("Dt").item(0).getTextContent()));
                    currency.setExchangeRate(Float.parseFloat(elem.getElementsByTagName("Amt").item(1).getTextContent()));
                    currencyRepository.save(currency);
                    System.out.println("Currency exchange rates updated");


                }
            }
        }  catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }


    }


}
