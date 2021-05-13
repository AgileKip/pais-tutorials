package org.agilekip.tutorials.travel.delegates;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SayHiDelegate implements JavaDelegate {



    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
         System.out.println("=================================================");
         System.out.println("=============== HI WORLD!!!======================");
         System.out.println("=================================================");

    }

}