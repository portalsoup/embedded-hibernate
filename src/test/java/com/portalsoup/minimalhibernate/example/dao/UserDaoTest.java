package com.portalsoup.minimalhibernate.example.dao;

import com.portalsoup.minimalhibernate.dao.DBBaseTest;
import com.portalsoup.minimalhibernate.example.entity.User;
import org.junit.Test;

public class UserDaoTest extends DBBaseTest {

    @Test
    public void saveAndReadUsersTest() {
        UserDao userDao = new UserDao();

        User user1 = new User("Jim");
        User user2 = new User("James");
    }
}