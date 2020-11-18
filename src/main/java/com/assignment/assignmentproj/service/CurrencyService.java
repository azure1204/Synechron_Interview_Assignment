package com.assignment.assignmentproj.service;

import com.assignment.assignmentproj.model.CurrencyRate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface CurrencyService {
    public List<CurrencyRate> getcurrencyInfo();
    public CurrencyRate getTodaysCurrencyRate();
    public int getCurrentMonth();
}
