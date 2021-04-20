package peaksoft.service;

import org.hibernate.Session;
import peaksoft.dao.UserDao;
import peaksoft.dao.UserDaoHibernateImpl;
import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
  //  UserDaoJdbcImpl userDaoJdbc=new UserDaoJdbcImpl();
  UserDao userDaoHibernate=new UserDaoHibernateImpl();

    public void createUsersTable() {
//        userDaoJdbc.createUsersTable();
        userDaoHibernate.createUsersTable();
    }

    public void dropUsersTable() {
//        userDaoJdbc.dropUsersTable();
        userDaoHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
//        userDaoJdbc.saveUser(name,lastName,age);
        userDaoHibernate.saveUser(name,lastName,age);
    }

    public void removeUserById(long id){
//       userDaoJdbc.removeUserById(id);
        userDaoHibernate.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {

//        return  userDaoJdbc.getAllUsers();
       return userDaoHibernate.getAllUsers() ; //!
    }

    public void cleanUsersTable() {
//        userDaoJdbc.cleanUsersTable();
        userDaoHibernate.cleanUsersTable();
    }
}
