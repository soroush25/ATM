package src.model.da;

import lombok.extern.log4j.Log4j;
import src.model.entity.Customer;
import src.model.entity.enums.City;
import src.model.entity.enums.Gender;
import src.model.tools.CRUD;
import src.model.tools.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class CustomerDa implements AutoCloseable, CRUD<Customer> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public CustomerDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    @Override
    public Customer save(Customer customer) throws Exception {
        customer.setId(ConnectionProvider.getConnectionProvider().getNextId("customer_seq"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO CUSTOMER (id, fname, lname, nid, gender, birth_date, phone, email, address, city, username, password) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, customer.getId());
        preparedStatement.setString(2, customer.getFirstName());
        preparedStatement.setString(3, customer.getLastName());
        preparedStatement.setString(4, customer.getNationalId());
        preparedStatement.setString(5, customer.getGender().name());
        preparedStatement.setDate(6, Date.valueOf(customer.getBirthDate()));
        preparedStatement.setString(7, customer.getPhone());
        preparedStatement.setString(8, customer.getEmail());
        preparedStatement.setString(9, customer.getAddress());
        preparedStatement.setString(10, customer.getCity().name());
        preparedStatement.setString(11, customer.getUsername());
        preparedStatement.setString(12, customer.getPassword());
        preparedStatement.execute();
        return customer;
    }

    @Override
    public Customer edit(Customer customer) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE CUSTOMER SET fname = ?, lname = ?, nid = ?, gender = ?, birth_date = ?, phone = ?, email = ?, address = ?, city = ?, username = ?, password = ? WHERE id = ?"
        );
        preparedStatement.setString(1, customer.getFirstName());
        preparedStatement.setString(2, customer.getLastName());
        preparedStatement.setString(3, customer.getNationalId());
        preparedStatement.setString(4, customer.getGender().name());
        preparedStatement.setDate(5, Date.valueOf(customer.getBirthDate()));
        preparedStatement.setString(6, customer.getPhone());
        preparedStatement.setString(7, customer.getEmail());
        preparedStatement.setString(8, customer.getAddress());
        preparedStatement.setString(9, customer.getCity().name());
        preparedStatement.setString(10, customer.getUsername());
        preparedStatement.setString(11, customer.getPassword());
        preparedStatement.setInt(12, customer.getId());
        preparedStatement.execute();
        return customer;
    }

    @Override
    public Customer remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM CUSTOMER WHERE ID = ?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<Customer> findAll() throws Exception {
        List<Customer> customerList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM CUSTOMER ORDER BY ID");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Customer customer = Customer
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .firstName(resultSet.getString("FNAME"))
                    .lastName(resultSet.getString("LNAME"))
                    .nationalId(resultSet.getString("NID"))
                    .gender(Gender.valueOf(resultSet.getString("GENDER")))
                    .birthDate(resultSet.getDate("BIRTH_DATE").toLocalDate())
                    .phone(resultSet.getString("PHONE"))
                    .email(resultSet.getString("EMAIL"))
                    .address(resultSet.getString("ADDRESS"))
                    .city(City.valueOf(resultSet.getString("CITY")))
                    .username(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .build();
            customerList.add(customer);
        }
        return customerList;
    }

    @Override
    public Customer findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM CUSTOMER WHERE ID = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Customer customer = null;
        if (resultSet.next()) {
            customer = Customer
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .firstName(resultSet.getString("FNAME"))
                    .lastName(resultSet.getString("LNAME"))
                    .nationalId(resultSet.getString("NID"))
                    .gender(Gender.valueOf(resultSet.getString("GENDER")))
                    .birthDate(resultSet.getDate("BIRTH_DATE").toLocalDate())
                    .phone(resultSet.getString("PHONE"))
                    .email(resultSet.getString("EMAIL"))
                    .address(resultSet.getString("ADDRESS"))
                    .city(City.valueOf(resultSet.getString("CITY")))
                    .username(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .build();
        }
        return customer;
    }

    public List<Customer> findByFamily(String family) throws Exception {
        List<Customer> customerList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM CUSTOMER WHERE lname LIKE? ORDER BY ID");
        preparedStatement.setString(1, family + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Customer customer = Customer
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .firstName(resultSet.getString("FNAME"))
                    .lastName(resultSet.getString("LNAME"))
                    .nationalId(resultSet.getString("NID"))
                    .gender(Gender.valueOf(resultSet.getString("GENDER")))
                    .birthDate(resultSet.getDate("BIRTH_DATE").toLocalDate())
                    .phone(resultSet.getString("PHONE"))
                    .email(resultSet.getString("EMAIL"))
                    .address(resultSet.getString("ADDRESS"))
                    .city(City.valueOf(resultSet.getString("CITY")))
                    .username(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .build();
            customerList.add(customer);
        }
        return customerList;
    }

    public Customer findByNationalId(String nationalId) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM CUSTOMER WHERE nid = ? ORDER BY ID");
        preparedStatement.setString(1, nationalId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Customer customer = null;
        if (resultSet.next()) {
            customer = Customer
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .firstName(resultSet.getString("FNAME"))
                    .lastName(resultSet.getString("LNAME"))
                    .nationalId(resultSet.getString("NID"))
                    .gender(Gender.valueOf(resultSet.getString("GENDER")))
                    .birthDate(resultSet.getDate("BIRTH_DATE").toLocalDate())
                    .phone(resultSet.getString("PHONE"))
                    .email(resultSet.getString("EMAIL"))
                    .address(resultSet.getString("ADDRESS"))
                    .city(City.valueOf(resultSet.getString("CITY")))
                    .username(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .build();
        }
        return customer;
    }

    public Customer findByPhone(String phone) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM CUSTOMER WHERE phone = ? ORDER BY ID");
        preparedStatement.setString(1, phone);
        ResultSet resultSet = preparedStatement.executeQuery();
        Customer customer = null;
        if (resultSet.next()) {
            customer = Customer
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .firstName(resultSet.getString("FNAME"))
                    .lastName(resultSet.getString("LNAME"))
                    .nationalId(resultSet.getString("NID"))
                    .gender(Gender.valueOf(resultSet.getString("GENDER")))
                    .birthDate(resultSet.getDate("BIRTH_DATE").toLocalDate())
                    .phone(resultSet.getString("PHONE"))
                    .email(resultSet.getString("EMAIL"))
                    .address(resultSet.getString("ADDRESS"))
                    .city(City.valueOf(resultSet.getString("CITY")))
                    .username(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .build();
        }
        return customer;
    }

    public Customer findByUsername(String username) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM CUSTOMER WHERE username = ? ORDER BY ID");
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        Customer customer = null;
        if (resultSet.next()) {
            customer = Customer
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .firstName(resultSet.getString("FNAME"))
                    .lastName(resultSet.getString("LNAME"))
                    .nationalId(resultSet.getString("NID"))
                    .gender(Gender.valueOf(resultSet.getString("GENDER")))
                    .birthDate(resultSet.getDate("BIRTH_DATE").toLocalDate())
                    .phone(resultSet.getString("PHONE"))
                    .email(resultSet.getString("EMAIL"))
                    .address(resultSet.getString("ADDRESS"))
                    .city(City.valueOf(resultSet.getString("CITY")))
                    .username(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .build();
        }
        return customer;
    }

    public Customer findByUsernameAndPassword(String username, String password) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM CUSTOMER WHERE username = ? AND password = ? ORDER BY ID");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        Customer customer = null;
        if (resultSet.next()) {
            customer = Customer
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .firstName(resultSet.getString("FNAME"))
                    .lastName(resultSet.getString("LNAME"))
                    .nationalId(resultSet.getString("NID"))
                    .gender(Gender.valueOf(resultSet.getString("GENDER")))
                    .birthDate(resultSet.getDate("BIRTH_DATE").toLocalDate())
                    .phone(resultSet.getString("PHONE"))
                    .email(resultSet.getString("EMAIL"))
                    .address(resultSet.getString("ADDRESS"))
                    .city(City.valueOf(resultSet.getString("CITY")))
                    .username(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .build();
        }
        return customer;
    }


    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
