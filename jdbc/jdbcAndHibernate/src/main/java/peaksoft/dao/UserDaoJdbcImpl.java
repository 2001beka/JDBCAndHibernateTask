package peaksoft.dao;

import peaksoft.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static peaksoft.util.Util.connect;
//
//import static peaksoft.util.Util.connect;

public class UserDaoJdbcImpl implements UserDao {


    public UserDaoJdbcImpl() {

    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS  Users (\n"
                + "Id serial PRIMARY KEY,\n"
                + "name varchar(20) NOT NULL,\n"
                + "lastName varchar(30),\n"
                + "age integer \n"
                + ");";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("created table");
        } catch (SQLException e) {
            System.out.println("kata " + e.getMessage());
        }
    }


    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS  Users";
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Table  deleted in given database...");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void saveUser(String name, String lastName, byte age) {
        String SQL = "INSERT INTO Users(name,lastName,age) VALUES(?,?,?)";

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(SQL)) {
                pstmt.setString(1,name);
                pstmt.setString(2,lastName);
            pstmt.setByte(3,age);
            pstmt.executeUpdate();
            System.out.println("save users connected successfully!!!");
            System.out.println(name+" connected");
        } catch (SQLException ex) {
            System.out.println("kata saveUser "+ ex.getMessage());
        }
    }

    public void removeUserById(long id) {
        String SQL = "DELETE  FROM Users where id=? ";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setLong(1,id);
            pstmt.executeUpdate();
            System.out.println("removed users successfully "+id);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   // List<User>list=new ArrayList<>();
    public List<User> getAllUsers() {

            String SQL = "SELECT * FROM Users";
            try (Connection conn = connect();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(SQL)) {
                List<User> usersList = new ArrayList<>();
                while (rs.next()){
                    User user = new User(rs.getString("name"),
                            rs.getString("lastName"), rs.getByte("age"));
                    usersList.add(user);
                  //  System.out.println(usersList);
                }
                return usersList;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
           return null;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM Users";
        try(
            Connection conn=connect();
           Statement stmt = conn.createStatement()){
           stmt.executeUpdate(sql);
            System.out.println("the table cleaned");
        }catch(SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}