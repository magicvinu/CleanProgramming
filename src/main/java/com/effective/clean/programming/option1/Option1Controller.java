package com.effective.clean.programming.option1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.BigInteger;


@RestController()
public class Option1Controller {

    private final BigDecimal SAVING_ACCOUNT_INTEREST_RATE = BigDecimal.valueOf(1.2);
    private final BigDecimal CURRENT_ACCOUNT_INTEREST_RATE = BigDecimal.valueOf(0.0);
    private final BigDecimal MORTGAGE_ACCOUNT_INTEREST_RATE = BigDecimal.valueOf(2.4);
    private final BigDecimal SENIOR_CITIZEN_SAVING_ACCOUNT_INTEREST_RATE = BigDecimal.valueOf(1.8);
    private final BigDecimal CHILDREN_SAVING_ACCOUNT_INTEREST_RATE = BigDecimal.valueOf(1.6);


    private enum AccountType{
        SAVING_ACCOUNT,CURRENT_ACCOUNT, MORTGAGE_ACCOUNT, SENIOR_CITIZEN_SAVING_ACCOUNT,CHILDREN_SAVING_ACCOUNT
    }
    @GetMapping("/calculateInterestAmount/option1")
    public BigDecimal calculateInterestAmount(@RequestHeader String accountType
            , @RequestHeader BigDecimal principalAmount){

        switch (AccountType.valueOf(accountType)){
            case SAVING_ACCOUNT: return principalAmount.multiply(SAVING_ACCOUNT_INTEREST_RATE).divide(BigDecimal.valueOf(100));
            case CURRENT_ACCOUNT: return principalAmount.multiply(CURRENT_ACCOUNT_INTEREST_RATE).divide(BigDecimal.valueOf(100));
            case MORTGAGE_ACCOUNT: return principalAmount.multiply(MORTGAGE_ACCOUNT_INTEREST_RATE).divide(BigDecimal.valueOf(100));
            case SENIOR_CITIZEN_SAVING_ACCOUNT: return principalAmount.multiply(SENIOR_CITIZEN_SAVING_ACCOUNT_INTEREST_RATE).divide(BigDecimal.valueOf(100));
            case CHILDREN_SAVING_ACCOUNT: return principalAmount.multiply(CHILDREN_SAVING_ACCOUNT_INTEREST_RATE).divide(BigDecimal.valueOf(100));
            default: throw new IllegalArgumentException(accountType + " accountType is not supported");

        }
    }

}
