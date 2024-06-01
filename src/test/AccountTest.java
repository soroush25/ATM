package src.test;

import src.model.bl.AccountBl;
import src.model.entity.Account;
import src.model.entity.Customer;
import src.model.entity.enums.BankAccountTypes;
import src.model.entity.enums.Banks;
import src.model.entity.enums.City;

public class AccountTest {
    public static void main(String[] args) throws Exception {

        Customer customer =
                Customer.builder()
                        .id(1)
                        .firstName(null)
                        .lastName(null)
                        .nationalId(null)
                        .birthDate(null)
                        .gender(null)
                        .username(null)
                        .password(null)
                        .address(null)
                        .phone(null)
                        .email(null)
                        .city(City.Tehran)
                        .build();

        Account account = Account
                .builder()
                .accountNumber(1)
                .balance(1)
                .customer(customer)
                .bank(Banks.Meli)
                .accountTypes(BankAccountTypes.Saving)
                .build();
        System.out.println(AccountBl.getAccountBl().save(account));
    }
}