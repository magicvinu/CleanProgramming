package com.effective.clean.programming.option2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


@RestController()
public class Option2Controller {

    private static final BigDecimal SAVING_ACCOUNT_INTEREST_RATE = BigDecimal.valueOf(1.2);
    private static final BigDecimal CURRENT_ACCOUNT_INTEREST_RATE = BigDecimal.valueOf(0.0);
    private static final BigDecimal MORTGAGE_ACCOUNT_INTEREST_RATE = BigDecimal.valueOf(2.4);
    private static final BigDecimal SENIOR_CITIZEN_SAVING_ACCOUNT_INTEREST_RATE = BigDecimal.valueOf(1.8);
    private static final BigDecimal CHILDREN_SAVING_ACCOUNT_INTEREST_RATE = BigDecimal.valueOf(1.6);


    private enum AccountType{
        SAVING_ACCOUNT{
            @Override
            BigDecimal calculateInterestAmount(String accountType, BigDecimal principalAmount) {
                return principalAmount.multiply(SAVING_ACCOUNT_INTEREST_RATE).divide(BigDecimal.valueOf(100));
            }
        },
        CURRENT_ACCOUNT{
            @Override
            BigDecimal calculateInterestAmount(String accountType, BigDecimal principalAmount) {
                return principalAmount.multiply(CURRENT_ACCOUNT_INTEREST_RATE).divide(BigDecimal.valueOf(100));
            }
        },
        MORTGAGE_ACCOUNT{
            @Override
            BigDecimal calculateInterestAmount(String accountType, BigDecimal principalAmount) {
                return principalAmount.multiply(MORTGAGE_ACCOUNT_INTEREST_RATE).divide(BigDecimal.valueOf(100));
            }
        },
        SENIOR_CITIZEN_SAVING_ACCOUNT{
            @Override
            BigDecimal calculateInterestAmount(String accountType, BigDecimal principalAmount) {
                return principalAmount.multiply(SENIOR_CITIZEN_SAVING_ACCOUNT_INTEREST_RATE).divide(BigDecimal.valueOf(100));
            }
        },
        CHILDREN_SAVING_ACCOUNT{
            @Override
            BigDecimal calculateInterestAmount(String accountType, BigDecimal principalAmount) {
                return principalAmount.multiply(CHILDREN_SAVING_ACCOUNT_INTEREST_RATE).divide(BigDecimal.valueOf(100));
            }
        };

        abstract BigDecimal calculateInterestAmount(String accountType, BigDecimal principalAmount);
    }
    @GetMapping("/calculateInterestAmount/option2")
    public BigDecimal calculateInterestAmount(@RequestHeader String accountType, @RequestHeader BigDecimal principalAmount){
            return AccountType.valueOf(accountType).calculateInterestAmount(accountType, principalAmount);
    }

}
