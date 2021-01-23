# CleanProgramming
The projects show different implementation of writing clean program

## Problem statement:
Expose API which will take account type and principal amount as input and return interest.

<b>Input:</b> accountType, principalAmount.

<b>Output:</b> Interest amount. 

## Option 1: Using switch statement and enum account type
Below option is the natural option for most of us. It's easy to understand and pretty straight forward

<code>
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
</code>

<b>Problems with this option1:</b> When a new AccountType is added to enum, Developer need to find all the places where the AccountType enum is used.
Above cases is the simplest example to show how one can use switch to fulfill desired result. The switch demands default case even it will never be triggered.   

### Can we do better?

==================================================================================================================

## Option 2: Avoid using switch statement and move business logic in enum.
