package com.assignment.assignmentproj.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Map;
/*Entity class used to and fro persisting to data store as well as fetching data from Db store. */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class CurrencyRate {

    private  String base;
    @ElementCollection
    private Map<String,Double> rates;
    @Id
    private  String  date;
    public CurrencyRate(){

    }
    public String getBase() {
        return base;
    }
    public void setBase(String base) {
        this.base = base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String,Double> rates) {
        this.rates = rates;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CurrencyRate{" +
                "base='" + base + '\'' +
                ", rates=" + rates +
                ", date='" + date + '\'' +
                '}';
    }
}
