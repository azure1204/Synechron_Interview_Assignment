package com.assignment.assignmentproj.controllers;

import com.assignment.assignmentproj.model.CurrencyRate;
import com.assignment.assignmentproj.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
/* Controller to handle all incoming requests*/
@Controller
public class CurrencyController {
    //injecting service
    private CurrencyService service;
    @Autowired
    CurrencyController(CurrencyService service){
        this.service = service;
    }

    @GetMapping(value = "/")
    public String getTodayCurrencyRateInfo(Model model){
      CurrencyRate currencyRate = this.service.getTodaysCurrencyRate();
    /*checking for null*/
      if(currencyRate!=null){
          //adding objectto display in view Layer in thymleaf template.
          model.addAttribute("currencyRate",currencyRate);
          System.out.println(currencyRate);
          return "firstPage";
      }
      return "error";
    }
    @GetMapping(value = "/getCurrencyInfo")
    public String getCurrencyRateInfo(Model model){
       List<CurrencyRate> currencyRateList = this.service.getcurrencyInfo();
       if(!currencyRateList.isEmpty()){
           model.addAttribute("currencyRateList",currencyRateList);
           model.addAttribute("currentMonth",this.service.getCurrentMonth());
           return  "allCurrencyRateInfo";
       }
       return  "error";

    }

}
