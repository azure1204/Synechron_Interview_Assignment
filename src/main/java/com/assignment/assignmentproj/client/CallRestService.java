package com.assignment.assignmentproj.client;

import com.assignment.assignmentproj.model.CurrencyRate;
import com.assignment.assignmentproj.repository.CurrencyRepository;
import com.assignment.assignmentproj.utils.DateUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;
/* Component class which grabs data from third party API(https://ratesapi.io/) and pushes to H2 DB*/
@Component
@ComponentScan({"com.assignment.assignmentproj.repository.CurrencyRepository"})
public class CallRestService implements CommandLineRunner {
    private CurrencyRepository repository;

    @Autowired
    CallRestService(CurrencyRepository repository){
        this.repository = repository;
    }
    /* calls third party REst API https://ratesapi.io/ service to  grab the present and past exchange rates data
    * @return List<Entity> which further gets pushed to  H2 DB*/
    public List<ResponseEntity<CurrencyRate>> callRestService() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        List<ResponseEntity<CurrencyRate>> responses= new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        for(String date : DateUtility.getLastSixMonths()) {
            ResponseEntity<CurrencyRate> response = restTemplate.exchange("https://api.ratesapi.io/api/"+date+"?symbols=USD,GBP,HKD", HttpMethod.GET,entity,CurrencyRate.class);
            responses.add(response);
        }
        return responses;
    }
    @Override
    public void run(String... args) throws Exception {
        List<ResponseEntity<CurrencyRate>> currencyRates =  this.callRestService();
        for(ResponseEntity<CurrencyRate> currencyRateResponseEntity:currencyRates){
           //Persisting to H2 DB Store.
            repository.save(currencyRateResponseEntity.getBody());
        }
    }
}
