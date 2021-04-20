package peaksoft.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.PreparedStatement;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try{
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
       Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS users(\n"
               + "id serial PRIMARY KEY,\n"
               + "name varchar(20) NOT NULL,\n"
               + "last_name varchar(30),\n"
               + "age integer \n"
               + ");" );
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("created users table successful");
    }catch (ExceptionInInitializerError exceptionInInitializerError){
            System.out.println(exceptionInInitializerError+" exception");
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery("DROP TABLE IF EXISTS users");
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("Successfully deleted users table");
        }catch (ExceptionInInitializerError exceptionInInitializerError){
            System.out.println("exception "+exceptionInInitializerError);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            User user = new User(name,lastName,age);
            session.save(user);
            session.getTransaction().commit();
            session.close();
            System.out.println("Successfully saved user "+user);
        }catch (ExceptionInInitializerError exceptionInInitializerError){
            System.out.println("exception "+exceptionInInitializerError);
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            User u= (User) session.get(User.class, id);
            session.delete(u);
            session.getTransaction().commit();
            session.close();
            System.out.println("Successfully deleted " +u);
        }catch (ExceptionInInitializerError exceptionInInitializerError){
            System.out.println("exception "+exceptionInInitializerError);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            List<User> userL = session.createSQLQuery("SELECT * FROM users").list();
            session.getTransaction().commit();
            session.close();
            System.out.println("Found " + userL.size() + " users");
            return userL;

        }catch (ExceptionInInitializerError exceptionInInitializerError){
            System.out.println("exception "+exceptionInInitializerError);
        }
        return null;  //404
    }


    @Override
    public void cleanUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery("TRUNCATE table users");
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("Successfully cleaned");
        }catch (ExceptionInInitializerError exceptionInInitializerError){
            System.out.println("exception "+exceptionInInitializerError);
        }
    }
}
