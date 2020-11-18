package com.assignment.assignmentproj.service;

import com.assignment.assignmentproj.model.CurrencyRate;
import com.assignment.assignmentproj.repository.CurrencyRepository;
import com.assignment.assignmentproj.utils.DateUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyServiceImpl implements  CurrencyService{
    //injecting repository
    private CurrencyRepository repository;
    @Autowired
    CurrencyServiceImpl(CurrencyRepository repository){
        this.repository = repository;
    }

    @Override
    public CurrencyRate getTodaysCurrencyRate() {
        return this.getcurrencyInfo().get(0);
    }

    @Override
    public int getCurrentMonth() {
        return DateUtility.getCurrentMonth();
    }

    @Override
    public List<CurrencyRate> getcurrencyInfo() {
        return (List<CurrencyRate>) this.repository.findAll();
    }

}
