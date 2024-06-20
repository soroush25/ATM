package src.test;

import src.model.entity.Account;
import src.model.entity.Transaction;
import src.model.entity.enums.TransactionTypes;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TransactionTest {
    public static void main(String[] args) throws Exception {

        Account account =
                Account.builder()
                        .accountNumber(1)
                        .balance(100)
                        .customer(null)
                        .accountType(null)
                        .build();

        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        Transaction transaction = Transaction
                .builder()
                .id(1)
                .amount(100)
                .sourceAccount(account)
                .destinationAccount(account)
                .transactionType(TransactionTypes.Transfer)
                .transactionDateTime(now)
                .build();
        //System.out.println(TransactionBl.getTransactionBl().save(transaction));
        //System.out.println(TransactionBl.getTransactionBl().findByDateTime(now));
        //System.out.println(TransactionBl.getTransactionBl().findBySourceAccountId("1"));

    }
}
