package src.model.da;

import lombok.extern.log4j.Log4j;
import src.model.entity.Admin;
import src.model.entity.enums.Gender;
import src.model.tools.CRUD;
import src.model.tools.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class AdminDa implements AutoCloseable, CRUD<Admin> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public AdminDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    @Override
    public Admin save(Admin admin) throws Exception {
        admin.setId(ConnectionProvider.getConnectionProvider().getNextId("admin_seq"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO ADMIN (id, fname, lname, nid, gender, birth_date, phone, email, address, username, password) VALUES (?,?,?,?,?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, admin.getId());
        preparedStatement.setString(2, admin.getFirstName());
        preparedStatement.setString(3, admin.getLastName());
        preparedStatement.setString(4, admin.getNationalId());
        preparedStatement.setString(5, admin.getGender().name());
        preparedStatement.setDate(6, Date.valueOf(admin.getBirthDate()));
        preparedStatement.setString(7, admin.getPhone());
        preparedStatement.setString(8, admin.getEmail());
        preparedStatement.setString(9, admin.getAddress());
        preparedStatement.setString(10, admin.getUsername());
        preparedStatement.setString(11, admin.getPassword());
        preparedStatement.execute();
        return admin;
    }

    @Override
    public Admin edit(Admin admin) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE ADMIN SET fname = ?, lname = ?, nid = ?, gender = ?, birth_date = ?,  phone = ?, email = ?, address = ?, username = ?, password =? WHERE id = ?"
        );
        preparedStatement.setString(1, admin.getFirstName());
        preparedStatement.setString(2, admin.getLastName());
        preparedStatement.setString(3, admin.getNationalId());
        preparedStatement.setString(4, admin.getGender().name());
        preparedStatement.setDate(5, Date.valueOf(admin.getBirthDate()));
        preparedStatement.setString(6, admin.getPhone());
        preparedStatement.setString(7, admin.getEmail());
        preparedStatement.setString(8, admin.getAddress());
        preparedStatement.setString(9, admin.getUsername());
        preparedStatement.setString(10, admin.getPassword());
        preparedStatement.setInt(11, admin.getId());
        preparedStatement.execute();
        return admin;
    }

    @Override
    public Admin remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM ADMIN WHERE ID = ?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<Admin> findAll() throws Exception {
        List<Admin> adminList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM ADMIN ORDER BY id");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Admin admin = Admin
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
                    .username(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .build();
            adminList.add(admin);
        }
        return adminList;
    }

    @Override
    public Admin findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM ADMIN WHERE ID = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Admin admin = null;
        if (resultSet.next()) {
            admin = Admin
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
                    .username(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .build();
        }
        return admin;
    }

    public List<Admin> findByFamily(String family) throws Exception {
        List<Admin> adminList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM ADMIN WHERE lname LIKE? ORDER BY ID");
        preparedStatement.setString(1, family + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Admin admin = Admin
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
                    .username(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .build();
            adminList.add(admin);
        }
        return adminList;
    }

    public Admin findByNationalId(String nationalId) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM ADMIN WHERE nid = ? ORDER BY ID");
        preparedStatement.setString(1, nationalId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Admin admin = null;
        if (resultSet.next()) {
            admin = Admin
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
                    .username(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .build();
        }
        return admin;
    }

    public Admin findByUsername(String username) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM ADMIN WHERE username = ? ORDER BY ID");
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        Admin admin = null;
        if (resultSet.next()) {
            admin = Admin
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
                    .username(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .build();
        }
        return admin;
    }
    public Admin findByUsernameAndPassword(String username,String password) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM ADMIN WHERE username = ? and password = ? ORDER BY ID");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        Admin admin = null;
        if (resultSet.next()) {
            admin = Admin
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
                    .username(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .build();
        }
        return admin;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
