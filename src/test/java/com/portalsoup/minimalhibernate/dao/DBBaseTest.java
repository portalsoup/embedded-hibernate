package com.portalsoup.minimalhibernate.dao;

import com.portalsoup.minimalhibernate.example.dao.UserDao;
import com.portalsoup.minimalhibernate.example.entity.User;
import com.portalsoup.minimalhibernate.util.TestHibernateUtil;
import org.junit.Before;

public class DBBaseTest {

    @Before
    public void init() {
        TestHibernateUtil.resetDatabase();
        new UserDao().getUsers().stream().map(User::getName).forEach(System.out::println);
    }
}
