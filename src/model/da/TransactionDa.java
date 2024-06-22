package src.model.da;

import lombok.extern.log4j.Log4j;
import src.model.entity.Account;
import src.model.entity.Transaction;
import src.model.entity.enums.TransactionTypes;
import src.model.tools.CRUD;
import src.model.tools.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class TransactionDa implements AutoCloseable, CRUD<Transaction> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public TransactionDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    @Override
    public Transaction save(Transaction transaction) throws Exception {
        transaction.setId(ConnectionProvider.getConnectionProvider().getNextId("transaction_seq"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO TRANSACTION (id, amount, account_src, account_dst, transactionDateTime, transactionType) VALUES (?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, transaction.getId());
        preparedStatement.setInt(2, transaction.getAmount());
        preparedStatement.setInt(3, transaction.getSourceAccount().getAccountNumber());
        preparedStatement.setInt(4, transaction.getDestinationAccount().getAccountNumber());
        preparedStatement.setTimestamp(5, Timestamp.valueOf(transaction.getTransactionDateTime().toLocalDateTime()));
        preparedStatement.setString(6, String.valueOf(transaction.getTransactionType()));
        preparedStatement.execute();
        return transaction;
    }

    @Override
    public Transaction edit(Transaction transaction) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE TRANSACTION SET amount = ?, account_src = ?, account_dst = ?, transactionDateTime = ?, transactionType = ? WHERE id = ?"
        );
        preparedStatement.setInt(1, transaction.getAmount());
        preparedStatement.setInt(2, transaction.getSourceAccount().getAccountNumber());
        preparedStatement.setInt(3, transaction.getDestinationAccount().getAccountNumber());
        preparedStatement.setTimestamp(4, Timestamp.valueOf(transaction.getTransactionDateTime().toLocalDateTime()));
        preparedStatement.setString(5, String.valueOf(transaction.getTransactionType()));
        preparedStatement.setInt(6, transaction.getId());
        preparedStatement.execute();
        return transaction;
    }

    @Override
    public Transaction remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM TRANSACTION WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<Transaction> findAll() throws Exception {
        List<Transaction> transactionList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM TRANSACTION ORDER BY ID");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Transaction transaction = Transaction
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .amount(resultSet.getInt("Amount"))
                    .sourceAccount(Account.builder().accountNumber(resultSet.getInt("Account_src")).build())
                    .destinationAccount(Account.builder().accountNumber(resultSet.getInt("Account_dst")).build())
                    .transactionDateTime(resultSet.getTimestamp("TransactionDateTime"))
                    .transactionType(TransactionTypes.valueOf(resultSet.getString("TransactionType")))
                    .build();
            transactionList.add(transaction);
        }
        return transactionList;
    }

    @Override
    public Transaction findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM TRANSACTION WHERE ID = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Transaction transaction = null;
        if (resultSet.next()) {
            transaction = Transaction
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .amount(resultSet.getInt("Amount"))
                    .sourceAccount(Account.builder().accountNumber(resultSet.getInt("Account_src")).build())
                    .destinationAccount(Account.builder().accountNumber(resultSet.getInt("Account_dst")).build())
                    .transactionDateTime(resultSet.getTimestamp("TransactionDateTime"))
                    .transactionType(TransactionTypes.valueOf(resultSet.getString("TransactionType")))
                    .build();
        }
        return transaction;
    }

    public List<Transaction> findBySourceAccountId(String sourceAccountId) throws Exception {
        List<Transaction> transactionList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM TRANSACTION WHERE account_src = ? ORDER BY ID");
        preparedStatement.setString(1, sourceAccountId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Transaction transaction = Transaction
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .amount(resultSet.getInt("Amount"))
                    .sourceAccount(Account.builder().accountNumber(resultSet.getInt("Account_src")).build())
                    .destinationAccount(Account.builder().accountNumber(resultSet.getInt("Account_dst")).build())
                    .transactionDateTime(resultSet.getTimestamp("TransactionDateTime"))
                    .transactionType(TransactionTypes.valueOf(resultSet.getString("TransactionType")))
                    .build();
            transactionList.add(transaction);
        }
        return transactionList;
    }

    public List<Transaction> findByDestinationAccountId(String destinationAccountId) throws Exception {
        List<Transaction> transactionList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM TRANSACTION WHERE account_dst = ? ORDER BY ID");
        preparedStatement.setString(1, destinationAccountId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Transaction transaction = Transaction
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .amount(resultSet.getInt("Amount"))
                    .sourceAccount(Account.builder().accountNumber(resultSet.getInt("Account_src")).build())
                    .destinationAccount(Account.builder().accountNumber(resultSet.getInt("Account_dst")).build())
                    .transactionDateTime(resultSet.getTimestamp("TransactionDateTime"))
                    .transactionType(TransactionTypes.valueOf(resultSet.getString("TransactionType")))
                    .build();
            transactionList.add(transaction);
        }
        return transactionList;
    }

    public List<Transaction> findByDateTime(Timestamp transactionDateTime) throws Exception {
        List<Transaction> transactionList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM TRANSACTION WHERE transactionDateTime = ? ORDER BY ID");
        preparedStatement.setTimestamp(1, transactionDateTime);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Transaction transaction = Transaction
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .amount(resultSet.getInt("Amount"))
                    .sourceAccount(Account.builder().accountNumber(resultSet.getInt("Account_src")).build())
                    .destinationAccount(Account.builder().accountNumber(resultSet.getInt("Account_dst")).build())
                    .transactionDateTime(resultSet.getTimestamp("TransactionDateTime"))
                    .transactionType(TransactionTypes.valueOf(resultSet.getString("TransactionType")))
                    .build();
            transactionList.add(transaction);
        }
        return transactionList;
    }

    public Transaction findByDateTimeRange(Timestamp start, Timestamp end) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM TRANSACTION WHERE transactionDateTime BETWEEN ? and ? ORDER BY ID");
        preparedStatement.setTimestamp(1, Timestamp.valueOf(start.toLocalDateTime()));
        preparedStatement.setTimestamp(2, Timestamp.valueOf(end.toLocalDateTime()));
        ResultSet resultSet = preparedStatement.executeQuery();
        Transaction transaction = null;
        if (resultSet.next()) {
            transaction = Transaction
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .amount(resultSet.getInt("Amount"))
                    .sourceAccount(Account.builder().accountNumber(resultSet.getInt("Account_src")).build())
                    .destinationAccount(Account.builder().accountNumber(resultSet.getInt("Account_dst")).build())
                    .transactionDateTime(resultSet.getTimestamp("TransactionDateTime"))
                    .transactionType(TransactionTypes.valueOf(resultSet.getString("TransactionType")))
                    .build();
        }
        return transaction;
    }

    public String transactionSum () throws Exception {
        preparedStatement = connection.prepareStatement("SELECT SUM(AMOUNT) FROM TRANSACTION");
        ResultSet resultSet = preparedStatement.executeQuery();
        return String.valueOf(resultSet);
    }

    public List<Transaction> findByDateTimeRangeReport(Timestamp start, Timestamp end) throws Exception {
        List<Transaction> transactionList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM TRANSACTION WHERE transactionDateTime BETWEEN ? AND ? ORDER BY ID");
        preparedStatement.setTimestamp(1, Timestamp.valueOf(start.toLocalDateTime()));
        preparedStatement.setTimestamp(2, Timestamp.valueOf(end.toLocalDateTime()));
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Transaction transaction = Transaction
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .amount(resultSet.getInt("Amount"))
                    .sourceAccount(Account.builder().accountNumber(resultSet.getInt("Account_src")).build())
                    .destinationAccount(Account.builder().accountNumber(resultSet.getInt("Account_dst")).build())
                    .transactionDateTime(resultSet.getTimestamp("TransactionDateTime"))
                    .transactionType(TransactionTypes.valueOf(resultSet.getString("TransactionType")))
                    .build();
            transactionList.add(transaction);
        }
        return transactionList;
    }

    public List<Transaction> findByTransactionType(String transactionType) throws Exception {
        List<Transaction> transactionList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM TRANSACTION WHERE transactionType = ? ORDER BY ID");
        preparedStatement.setString(1, transactionType);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Transaction transaction = Transaction
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .amount(resultSet.getInt("Amount"))
                    .sourceAccount(Account.builder().accountNumber(resultSet.getInt("Account_src")).build())
                    .destinationAccount(Account.builder().accountNumber(resultSet.getInt("Account_dst")).build())
                    .transactionDateTime(resultSet.getTimestamp("TransactionDateTime"))
                    .transactionType(TransactionTypes.valueOf(resultSet.getString("TransactionType")))
                    .build();
            transactionList.add(transaction);
        }
        return transactionList;
    }


    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
