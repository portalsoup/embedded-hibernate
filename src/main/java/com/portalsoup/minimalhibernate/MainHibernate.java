package com.portalsoup.minimalhibernate;

import com.portalsoup.minimalhibernate.dao.UserDao;
import com.portalsoup.minimalhibernate.entity.User;

import java.util.List;

public class MainHibernate {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        User user = new User("John");

        userDao.saveUser(user);

        List<User> allUsers = userDao.getUsers();
        allUsers.stream().map(User::getName).forEach(System.out::println);

        List<User> allUsersNamedJohn = userDao.getUsersNamed("John");
        allUsersNamedJohn.stream().map(User::getName).forEach(System.out::println);
    }
}
