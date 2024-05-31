package src.test;

import src.model.bl.AccountBl;
import src.model.bl.AdminBl;
import src.model.entity.Account;
import src.model.entity.Admin;
import src.model.entity.enums.BankAccountTypes;
import src.model.entity.enums.Banks;
import src.model.entity.enums.Gender;

import java.time.LocalDate;

public class AccountTest {
    public static void main(String[] args) throws Exception {


    Account account = Account
            .builder()
            .accountNumber(1232)
            .balance(234)
            .customer(null)
            .bank(Banks.Meli)
            .accountTypes(BankAccountTypes.SavingAccount)
            .build();
        System.out.println(AccountBl.getAccountBl().save(account));
}
}