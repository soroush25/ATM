package src.model.da;

import lombok.extern.log4j.Log4j;
import src.model.entity.Transaction;
import src.model.entity.enums.City;
import src.model.entity.enums.Gender;
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
        preparedStatement = connection.prepareStatement(
                "INSERT INTO TRANSACTION (id, amount, deposit, account, transactionDateAndTime, transactionType) VALUES (?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, transaction.getId());
        preparedStatement.setDouble(2, transaction.getAmount());
        preparedStatement.setDouble(3, transaction.getDeposit());
        preparedStatement.setInt(4, String.valueOf(transaction.getAccount()));
        preparedStatement.setDate(5, Date.valueOf(transaction.getTransactionDateAndTime()));
        preparedStatement.setString(6, transaction.getTransactionType());

        preparedStatement.execute();
        return transaction;
    }

    @Override
    public Transaction edit(Transaction transaction) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE TRANSACTION SET fname = ?, lname = ?, nid = ?, gender = ?, birth_date = ?, city = ?, phone = ?, email = ?, address = ? WHERE id = ?"
        );
        preparedStatement.setString(1, transaction.getFirstName());
        preparedStatement.setString(2, transaction.getLastName());
        preparedStatement.setString(3, transaction.getNationalID());
        preparedStatement.setString(4, transaction.getGender().name());
        preparedStatement.setDate(5, Date.valueOf(transaction.getBirthDate()));
        preparedStatement.setString(6, transaction.getCity().name());
        preparedStatement.setString(7, transaction.getPhone());
        preparedStatement.setString(8, transaction.getEmail());
        preparedStatement.setString(9, transaction.getAddress());
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
                    .firstName(resultSet.getString("NAME"))
                    .lastName(resultSet.getString("FAMILY"))
                    .nationalID(resultSet.getString("NID"))
                    .gender(Gender.valueOf(resultSet.getString("GENDER")))
                    .birthDate(resultSet.getDate("BIRTH_DATE").toLocalDate())
                    .city(City.valueOf(resultSet.getString("CITY")))
                    .phone(resultSet.getString("PHONE"))
                    .email(resultSet.getString("EMAIL"))
                    .address(resultSet.getString("ADDRESS"))
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
                    .firstName(resultSet.getString("NAME"))
                    .lastName(resultSet.getString("FAMILY"))
                    .nationalID(resultSet.getString("NID"))
                    .gender(Gender.valueOf(resultSet.getString("GENDER")))
                    .birthDate(resultSet.getDate("BIRTH_DATE").toLocalDate())
                    .city(City.valueOf(resultSet.getString("CITY")))
                    .phone(resultSet.getString("PHONE"))
                    .email(resultSet.getString("EMAIL"))
                    .address(resultSet.getString("ADDRESS"))
                    .build();
        }
        return transaction;
    }

    public List<Transaction> findByFamily(String family) throws Exception {
        List<Transaction> transactionList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("SELECT * FROM TRANSACTION WHERE lname LIKE? ORDER BY ID");
        preparedStatement.setString(1, family + "%");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Transaction transaction = Transaction
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .firstName(resultSet.getString("NAME"))
                    .lastName(resultSet.getString("FAMILY"))
                    .nationalID(resultSet.getString("NID"))
                    .gender(Gender.valueOf(resultSet.getString("GENDER")))
                    .birthDate(resultSet.getDate("BIRTH_DATE").toLocalDate())
                    .city(City.valueOf(resultSet.getString("CITY")))
                    .phone(resultSet.getString("PHONE"))
                    .email(resultSet.getString("EMAIL"))
                    .address(resultSet.getString("ADDRESS"))
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
