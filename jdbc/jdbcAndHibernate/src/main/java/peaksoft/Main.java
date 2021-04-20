package peaksoft;

import antlr.ANTLRHashString;
import org.hibernate.Session;
import peaksoft.model.User;
import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;
import peaksoft.util.Util;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws Throwable {
        // реализуйте алгоритм здесь

 //       UserServiceImpl userService=new UserServiceImpl();
//        userService.createUsersTable();
//        userService.saveUser("Nurlanov","Erlan",(byte)19);
//        userService.saveUser("Toktosunov","Syimyk",(byte)19);
//        userService.saveUser("Turarbekov","Beku",(byte)18);
//        userService.removeUserById(1);
//          userService.getAllUsers();
//          userService.cleanUsersTable();
//        userService.dropUsersTable();

UserServiceImpl userService=new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Bakyt","Jumabekov",(byte)19);
        userService.saveUser("Bek","Jan",(byte)19);
        userService.saveUser("Nurbek","Alymov",(byte)19);
//        userService.getAllUsers();
//        userService.removeUserById(3);
//        userService.cleanUsersTable();
//        userService.dropUsersTable();
        Util.getSessionFactory().close();

    }
}
