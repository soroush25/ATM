package src.test;

import src.model.entity.Account;
import src.model.entity.Customer;
import src.model.entity.enums.AccountType;
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
                .balance(200)
                .customer(customer)
                .accountTypes(AccountType.Saving)
                .accountNumber(1)
                .build();
        //System.out.println(AccountBl.getAccountBl().save(account));
        //System.out.println(AccountBl.getAccountBl().edit(account));
        //System.out.println(AccountBl.getAccountBl().remove(1));
        //System.out.println(AccountBl.getAccountBl().findAll());
        //System.out.println(AccountBl.getAccountBl().findById(1));
        //System.out.println(AccountBl.getAccountBl().findByAccountType("Saving"));
        //System.out.println(AccountBl.getAccountBl().findByBankName("Meli");
        //System.out.println(AccountBl.getAccountBl().findByCustomerId("1"));
    }
}