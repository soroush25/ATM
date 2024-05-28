package src.model.bl;

import lombok.Getter;
import src.controller.exceptions.NotFoundException;
import src.model.da.TransactionDa;
import src.model.entity.Transaction;
import src.model.tools.CRUD;

import java.util.List;

public class TransactionBl implements CRUD<Transaction> {
    @Getter
    private static TransactionBl transactionBl = new TransactionBl();

    private TransactionBl() {
    }

    @Override
    public Transaction save(Transaction transaction) throws Exception {
        try (TransactionDa transactionDa = new TransactionDa()) {
            transactionDa.save(transaction);
            return transaction;
        }
    }

    @Override
    public Transaction edit(Transaction transaction) throws Exception {
        try (TransactionDa transactionDa = new TransactionDa()) {
            if (transactionDa.findById(transaction.getId()) != null) {
                transactionDa.edit(transaction);
                return transaction;
            } else {
                throw new NotFoundException();
            }
        }
    }

    @Override
    public Transaction remove(int id) throws Exception {
        try (TransactionDa transactionDa = new TransactionDa()) {
            Transaction transaction = transactionDa.findById(id);
            if (transaction != null) {
                transactionDa.remove(id);
                return transaction;
            } else {
                throw new NotFoundException();
            }
        }
    }

    @Override
    public List<Transaction> findAll() throws Exception {
        try (TransactionDa transactionDa = new TransactionDa()) {
            List<Transaction> perosnList = transactionDa.findAll();
            if (!perosnList.isEmpty()) {
                return perosnList;
            } else {
                throw new NotFoundException();
            }
        }
    }

    @Override
    public Transaction findById(int id) throws Exception {
        try (TransactionDa transactionDa = new TransactionDa()) {
            Transaction transaction = transactionDa.findById(id);
            if (transaction != null) {
                return transaction;
            } else {
                throw new NotFoundException();
            }
        }
    }

    public Transaction findBySourceAccountId(String sourceAccountId) throws Exception {
        try (TransactionDa transactionDa = new TransactionDa()) {
            Transaction transaction = transactionDa.findBySourceAccountId(sourceAccountId);
            if (transaction != null) {
                transaction.setSourceAccount(AccountBl.getAccountBl().findById(transaction.getSourceAccount().getAccountNumber()));
                transaction.setDestinationAccount(AccountBl.getAccountBl().findById(transaction.getDestinationAccount().getAccountNumber()));
                return transaction;
            } else {
                throw new NotFoundException();
            }
        }
    }

    public Transaction findByDestinationAccountId(String destinationAccountId) throws Exception {
        try (TransactionDa transactionDa = new TransactionDa()) {
            Transaction transaction = transactionDa.findBySourceAccountId(destinationAccountId);
            if (transaction != null) {
                transaction.setSourceAccount(AccountBl.getAccountBl().findById(transaction.getSourceAccount().getAccountNumber()));
                transaction.setDestinationAccount(AccountBl.getAccountBl().findById(transaction.getDestinationAccount().getAccountNumber()));
                return transaction;
            } else {
                throw new NotFoundException();
            }
        }
    }

    public List<Transaction> findByDateTime(String transactionDateTime) throws Exception {
        try (TransactionDa transactionDa = new TransactionDa()) {
            List<Transaction> transactionList = transactionDa.findByDateTime(transactionDateTime);
            if (!transactionList.isEmpty()) {
                return transactionList;
            } else {
                throw new NotFoundException();
            }
        }
    }

    public Transaction findByDateTimeRange(int start, int end) throws Exception {
        try (TransactionDa transactionDa = new TransactionDa()) {
            Transaction transaction = transactionDa.findByDateTimeRange(start, end);
            if (transaction != null) {
                transaction.setSourceAccount(AccountBl.getAccountBl().findById(transaction.getSourceAccount().getAccountNumber()));
                transaction.setDestinationAccount(AccountBl.getAccountBl().findById(transaction.getDestinationAccount().getAccountNumber()));
                return transaction;
            } else {
                throw new NotFoundException();
            }
        }
    }

    public List<Transaction> findByDateTimeRangeReport(int start, int end) throws Exception {
        try (TransactionDa transactionDa = new TransactionDa()) {
            List<Transaction> transactionList = transactionDa.findByDateTimeRangeReport(start, end);
            if (!transactionList.isEmpty()) {
                for (Transaction transaction : transactionList) {
                    transaction.setSourceAccount(AccountBl.getAccountBl().findById(transaction.getSourceAccount().getAccountNumber()));
                    transaction.setDestinationAccount(AccountBl.getAccountBl().findById(transaction.getDestinationAccount().getAccountNumber()));
                }
                return transactionList;
            } else {
                throw new NotFoundException();
            }
        }
    }
}
