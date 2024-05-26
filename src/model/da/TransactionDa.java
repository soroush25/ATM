package src.model.da;

import lombok.extern.log4j.Log4j;
import src.model.entity.Account;
import src.model.entity.Transaction;
import src.model.entity.enums.TransactionTypes;
import src.model.tools.CRUD;
import src.model.tools.ConnectionProvider;

import java.sql.*;
import java.time.LocalDateTime;
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
        preparedStatement = connection.prepareStatement(
                "INSERT INTO TRANSACTION (id, amount, deposit, account_id, transactionDateAndTime, transactionType) VALUES (?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, transaction.getId());
        preparedStatement.setDouble(2, transaction.getAmount());
        preparedStatement.setDouble(3, Double.parseDouble(transaction.getDeposit()));
        preparedStatement.setInt(4, transaction.getAccount().getAccountNumber());
        preparedStatement.setDate(5, Date.valueOf(String.valueOf(transaction.getTransactionDateTime())));
        preparedStatement.setString(6, String.valueOf(transaction.getTransactionType()));

        preparedStatement.execute();
        return transaction;
    }

    @Override
    public Transaction edit(Transaction transaction) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE TRANSACTION SET amount = ?, deposit = ?, account_id = ?, transactionDateAndTime = ?, transactionType = ? WHERE id = ?"
        );
        preparedStatement.setDouble(1, transaction.getAmount());
        preparedStatement.setDouble(2, Double.parseDouble(transaction.getDeposit()));
        preparedStatement.setInt(3, transaction.getAccount().getAccountNumber());
        preparedStatement.setDate(4, Date.valueOf(String.valueOf(transaction.getTransactionDateTime())));
        preparedStatement.setString(5, String.valueOf(transaction.getTransactionType()));
        preparedStatement.setInt(6, transaction.getId());
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
                    .amount(resultSet.getDouble("Amount"))
                    .deposit(resultSet.getString("Deposit"))
                    .account((Account)resultSet.getObject("Account_ID"))
                    .transactionDateTime(LocalDateTime.parse(resultSet.getString("TransactionDateAndTime")))
                    .transactionType(TransactionTypes.valueOf(resultSet.getString("TransactionType")))
                    .build();
            transactionList.add(transaction);
        }
        return transactionList;
    }

    @Override
    public Transaction findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM TRANSACTION WHERE ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Transaction transaction = null;
        if (resultSet.next()) {
            transaction = Transaction
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .amount(resultSet.getDouble("Amount"))
                    .deposit(resultSet.getString("Deposit"))
                    .account((Account)resultSet.getObject("Account_ID"))
                    .transactionDateTime(LocalDateTime.parse(resultSet.getString("TransactionDateAndTime")))
                    .transactionType(TransactionTypes.valueOf(resultSet.getString("TransactionType")))
                    .build();
        }
        return transaction;
    }

    public List<Transaction> findByAccountId(int accountId) throws Exception {
        List<Transaction> transactionList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM TRANSACTION WHERE Transaction.account_id LIKE? ORDER BY ID");
        preparedStatement.setString(1, account + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Transaction transaction = Transaction
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .amount(resultSet.getDouble("Amount"))
                    .deposit(resultSet.getString("Deposit"))
                    .account((Account)resultSet.getObject("Account_ID"))
                    .transactionDateTime(LocalDateTime.parse(resultSet.getString("TransactionDateAndTime")))
                    .transactionType(TransactionTypes.valueOf(resultSet.getString("TransactionType")))
                    .build();
            transactionList.add(transaction);
        }
        return transactionList;
    }

//    todo : findBySourceAccountId
//    todo : findByDestinationAccountId
//    todo : findByDateTime
//    todo : findByDateTimeRange(startDateTime, endDateTime)
//    todo : findByDateTimeRange(startDateTime, endDateTime) Report

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
