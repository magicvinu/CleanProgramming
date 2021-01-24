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

<b>Problems with this option1:</b> When a new AccountType is added to enum, Developer need to find all the places where the AccountType enum is used. There is no way to enforce the new enum type ss added in all the places.
Above cases is the simplest example to show how one can use switch to fulfill desired result. The switch demands default case even though it is never triggered.   

### Can we do better?

=======================================================================

## Option 2: Avoid using switch statement and move business logic in enum.
<code>

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
    
</code>

Introducing a new abstract method calculateInterestAmount in enum will enforce the method must be implemented for each newly introduced enum type.  

<code>

    @GetMapping("/calculateInterestAmount/option2")
    public BigDecimal calculateInterestAmount(@RequestHeader String accountType, @RequestHeader BigDecimal principalAmount){
            return AccountType.valueOf(accountType).calculateInterestAmount(accountType, principalAmount);
    }

</code>
Option2 implementation can be used if the business logic is simple and doesn't need a dependency to be injected in enum to call business logic.
Both Option1 and Option2 controller exposed to implementation detail and violates Single Responsibility Principle. All the business logic implementation is packed in a single class which makes it hard to test. 
It will be easy to test different business logic If each of the account type handled by a separate class. 