package com.example.CurrencyConvertor.components;


import com.example.CurrencyConvertor.model.Currency;
import com.example.CurrencyConvertor.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.Date;


@Component
public class ScheduledCurrencyUpdates {

    @Autowired
    CurrencyRepository currencyRepository;

    @Value("${api.for.currency.list.updating}")
    private String currencyRateUpdateAPI;

    @Value("${api.for.exchange.rate.updating}")
    private String exchangeRateUpdateAPI;

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



//
//    // Updating currency list every 12 hours
//    @Scheduled(fixedRate = 86100000)
//    private void updateCurrencyList()  {
//
//        ResponseEntity<String> response = restTemplate.exchange(currencyRateUpdateAPI , HttpMethod.GET, getEntity(),String.class);
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//
//        try {
//            NodeList nodeList = getElement(response ,factory ).getElementsByTagName("CcyNtry");
//            for (int i =0; i< nodeList.getLength(); i++) {
//
//                Node node = nodeList.item(i);
//
//                if(node.getNodeType() == Node.ELEMENT_NODE) {
//                    Element elem = (Element) node;
//                    Currency currency = new Currency(
//                            elem.getElementsByTagName("Ccy").item(0).getTextContent(),
//                            elem.getElementsByTagName("CcyNm").item(0).getTextContent(),
//                            elem.getElementsByTagName("CcyNm").item(1).getTextContent());
//
//                   if(!currencyRepository.existsByShortCurrencyName(currency.getShortCurrencyName())){
//                       currencyRepository.save(currency);
//                   }
//                }
//
//
//
//            }
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    // Updating currency exchange rates every 12 hours
//    @Scheduled(fixedRate = 86400000)
//    private void updateExchangeRates(){
//
//
//        ResponseEntity<String> response = restTemplate.exchange(exchangeRateUpdateAPI , HttpMethod.GET, getEntity(),String.class);
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//
//        try {
//            NodeList nodeList = getElement(response,factory).getElementsByTagName("FxRate");
//
//            for (int i =0; i< nodeList.getLength(); i++) {
//
//                Node node = nodeList.item(i);
//
//                if(node.getNodeType() == Node.ELEMENT_NODE) {
//
//                    Element elem = (Element) node;
//                    String shortName = elem.getElementsByTagName("Ccy").item(1).getTextContent();
//                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                    Date date =simpleDateFormat.parse(elem.getElementsByTagName("Dt").item(0).getTextContent());
//                    double newRate = Double.parseDouble(elem.getElementsByTagName("Amt").item(1).getTextContent());
//
//                    Currency currency = currencyRepository.findByShortCurrencyName(shortName);
//
//                   if(currency.getExchangeRate() != newRate){
//                       System.out.println("currency.getExchangeRate() = " + currency.getExchangeRate());
//                       System.out.println("newRate = " + newRate);
//                       currency.setExchangeRate(newRate);
//                       currency.setDate(date);
//                       currencyRepository.save(currency);
//                       System.out.println("Currency exchange rates updated");
//                   }
//
//                }
//            }
//        }  catch (SAXException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        }
//
//
//    }


}
