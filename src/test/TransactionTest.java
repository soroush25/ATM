package src.test;

import src.model.bl.TransactionBl;
import src.model.entity.Account;
import src.model.entity.Transaction;
import src.model.entity.enums.TransactionTypes;

import java.time.LocalDate;

public class TransactionTest {
    public static void main(String[] args) throws Exception {

        Account account =
                Account.builder()
                        .accountNumber(1)
                        .balance(100)
                        .customer(null)
                        .bank(null)
                        .accountTypes(null)
                        .build();


        Transaction transaction = Transaction
                .builder()
                .id(1)
                .amount(100)
                .deposit(100)
                .sourceAccount(account)
                .destinationAccount(account)
                .transactionType(TransactionTypes.transfer)
                .transactionDate(LocalDate.now())
                .build();
        System.out.println(TransactionBl.getTransactionBl().save(transaction));
    }
}
