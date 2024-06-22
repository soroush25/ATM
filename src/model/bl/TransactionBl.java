package src.model.bl;

import lombok.Getter;
import src.controller.exceptions.NotFoundException;
import src.model.da.AccountDa;
import src.model.da.TransactionDa;
import src.model.entity.Transaction;
import src.model.tools.CRUD;

import java.sql.Timestamp;
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
            List<Transaction> transactionList = transactionDa.findAll();
            if (!transactionList.isEmpty()) {
                for (Transaction transaction : transactionList) {
                    transaction.setSourceAccount(AccountBl.getAccountBl().findByAccountNumber(transaction.getSourceAccount().getAccountNumber()));
                    transaction.setDestinationAccount(AccountBl.getAccountBl().findByAccountNumber(transaction.getDestinationAccount().getAccountNumber()));
                }
                return transactionList;
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
                transaction.setSourceAccount(AccountBl.getAccountBl().findByAccountNumber(transaction.getSourceAccount().getAccountNumber()));
                transaction.setDestinationAccount(AccountBl.getAccountBl().findByAccountNumber(transaction.getDestinationAccount().getAccountNumber()));
                return transaction;
            } else {
                throw new NotFoundException();
            }
        }
    }

    public List<Transaction> findBySourceAccountId(String sourceAccountId) throws Exception {
        try (TransactionDa transactionDa = new TransactionDa()) {
            List<Transaction> transactionList = transactionDa.findBySourceAccountId(sourceAccountId);
            if (!transactionList.isEmpty()) {
                for (Transaction transaction : transactionList) {
                    transaction.setSourceAccount(AccountBl.getAccountBl().findByAccountNumber(transaction.getSourceAccount().getAccountNumber()));
                    transaction.setDestinationAccount(AccountBl.getAccountBl().findByAccountNumber(transaction.getDestinationAccount().getAccountNumber()));
                }
                return transactionList;
            } else {
                throw new NotFoundException();
            }
        }
    }

    public List<Transaction> findByDestinationAccountId(String destinationAccountId) throws Exception {
        try (TransactionDa transactionDa = new TransactionDa()) {
            List<Transaction> transactionList = transactionDa.findBySourceAccountId(destinationAccountId);
            if (!transactionList.isEmpty()) {
                for (Transaction transaction : transactionList) {
                    transaction.setSourceAccount(AccountBl.getAccountBl().findByAccountNumber(transaction.getSourceAccount().getAccountNumber()));
                    transaction.setDestinationAccount(AccountBl.getAccountBl().findByAccountNumber(transaction.getDestinationAccount().getAccountNumber()));
                }
                return transactionList;
            } else {
                throw new NotFoundException();
            }
        }
    }

    public List<Transaction> findByDateTime(Timestamp transactionDateTime) throws Exception {
        try (TransactionDa transactionDa = new TransactionDa()) {
            List<Transaction> transactionList = transactionDa.findByDateTime(transactionDateTime);
            if (!transactionList.isEmpty()) {
                for (Transaction transaction : transactionList) {
                    transaction.setSourceAccount(AccountBl.getAccountBl().findByAccountNumber(transaction.getSourceAccount().getAccountNumber()));
                    transaction.setDestinationAccount(AccountBl.getAccountBl().findByAccountNumber(transaction.getDestinationAccount().getAccountNumber()));
                }
                return transactionList;
            } else {
                throw new NotFoundException();
            }
        }
    }

    public Transaction findByDateTimeRange(Timestamp start, Timestamp end) throws Exception {
        try (TransactionDa transactionDa = new TransactionDa()) {
            Transaction transaction = transactionDa.findByDateTimeRange(start, end);
            if (transaction != null) {
                transaction.setSourceAccount(AccountBl.getAccountBl().findByAccountNumber(transaction.getSourceAccount().getAccountNumber()));
                transaction.setDestinationAccount(AccountBl.getAccountBl().findByAccountNumber(transaction.getDestinationAccount().getAccountNumber()));
                return transaction;
            } else {
                throw new NotFoundException();
            }
        }
    }

    public String transactionSum () throws Exception {
        try (AccountDa accountDa = new AccountDa()) {
            String sum = accountDa.balanceSum();
            return sum;
        }
    }

    public List<Transaction> findByDateTimeRangeReport(Timestamp start, Timestamp end) throws Exception {
        try (TransactionDa transactionDa = new TransactionDa()) {
            List<Transaction> transactionList = transactionDa.findByDateTimeRangeReport(start, end);
            if (!transactionList.isEmpty()) {
                for (Transaction transaction : transactionList) {
                    transaction.setSourceAccount(AccountBl.getAccountBl().findByAccountNumber(transaction.getSourceAccount().getAccountNumber()));
                    transaction.setDestinationAccount(AccountBl.getAccountBl().findByAccountNumber(transaction.getDestinationAccount().getAccountNumber()));
                }
                return transactionList;
            } else {
                throw new NotFoundException();
            }
        }
    }

    public List<Transaction> findByTransactionType(String transactionType) throws Exception {
        try (TransactionDa transactionDa = new TransactionDa()) {
            List<Transaction> transactionList = transactionDa.findByTransactionType(transactionType);
            if (!transactionList.isEmpty()) {
                for (Transaction transaction : transactionList) {
                    transaction.setSourceAccount(AccountBl.getAccountBl().findByAccountNumber(transaction.getSourceAccount().getAccountNumber()));
                    transaction.setDestinationAccount(AccountBl.getAccountBl().findByAccountNumber(transaction.getDestinationAccount().getAccountNumber()));
                }
                return transactionList;
            } else {
                throw new NotFoundException();
            }
        }
    }
}
