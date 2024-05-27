package src.test;

import src.model.bl.TransactionBl;
import src.model.entity.Transaction;
import src.model.entity.enums.TransactionTypes;

import java.time.LocalDateTime;

public class TransactionTest {
    public static void main(String[] args) throws Exception {
        Transaction transaction = Transaction
                .builder()
                .id(1)
                .amount(100D)
                .deposit("100")
                .transactionType(TransactionTypes.transfer)
                .destinationAccount("115")
                .sourceAccount("120")
                .transactionDateTime(LocalDateTime.now())
                .build();
        System.out.println(TransactionBl.getTransactionBl().save(transaction));
        System.out.println(TransactionBl.getTransactionBl().findByDateTimeRange(2023, 2024));
    }
}
