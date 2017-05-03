package com.mythsman;

import com.mythsman.dao.UserDao;
import com.mythsman.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("/init-schema.sql")
public class InitDatabaseTests {

    @Autowired
    private UserDao userDAO;

    @Test
    public void initDatabase() {
        for(int i=0;i<10;i++){
            User user=new User(String.format("%d",i), UUID.randomUUID().toString().substring(0,5), UUID.randomUUID().toString().substring(0,10));
            userDAO.addUser(user);
        }
    }
}
