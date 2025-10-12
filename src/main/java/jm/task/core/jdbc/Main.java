package jm.task.core.jdbc;

import jm.task.core.jdbc.model.*;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.sql.*;
import java.util.*;

public class Main {


    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        User firstUser = new User("Curtis", "Jackson", (byte) 50);
//        User secondUser = new User("Marshall", "Mathers", (byte) 52);
//        User thirdUser = new User("Fergie", "Duhamel", (byte) 50);
//        User fourthUser = new User("Ashley", "Frangipanie", (byte) 30);
//
//        userService.saveUser(firstUser.getName(), firstUser.getLastName(), firstUser.getAge());
//        userService.saveUser(secondUser.getName(), secondUser.getLastName(), secondUser.getAge());
//        userService.saveUser(thirdUser.getName(), thirdUser.getLastName(), thirdUser.getAge());
//        userService.saveUser(fourthUser.getName(), fourthUser.getLastName(), fourthUser.getAge());
//
//        System.out.println(userService.getAllUsers());
//
//        userService.cleanUsersTable();
//        System.out.println(userService.getAllUsers());
//        userService.dropUsersTable();

        //Get Session
        Session session = Util.HibernateUtil.getSessionFactory().getCurrentSession();
        //start transaction
        session.beginTransaction();
        //Save the Model object
        session.save(firstUser);
        //Commit transaction
        session.getTransaction().commit();
        System.out.println("User ID="+firstUser.getId());

        //terminate session factory, otherwise program won't end
        Util.HibernateUtil.getSessionFactory().close();

    }
}
