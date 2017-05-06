package com.mythsman;

import com.mythsman.dao.UserDao;
import com.mythsman.model.User;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("/user.sql")
public class UserDaoTests {
    private static final Logger logger = Logger.getLogger(UserDaoTests.class);
    @Autowired
    private UserDao userDAO;

    @Test
    public void addUserTests() {
        int res=userDAO.addUser("myths", "eread", "32434324");
        logger.info(res);
    }

    @Test
    public void selectByIdTests() {
        int id = 1;
        User user = userDAO.selectById(id);
        logger.info(user.getId()+" "+user.getName());
    }

    @Test
    public void selectByNameTests() {
        String name="myths1";
        User user = userDAO.selectByName(name);
        logger.info(user.getId()+" "+user.getName());
    }

    @Test
    public void updateProfileTests(){
        String website="www.baidu.com";
        String email="root@mythsman.com";
        String phone="110";
        String gender="male";
        String biography="who am i";

        User user=userDAO.selectById(1);
        user.setWebsite(website);
        user.setEmail(email);
        user.setPhone(phone);
        user.setGender(gender);
        user.setBiography(biography);

        userDAO.updateProfile(user);

        Assert.assertEquals(userDAO.selectById(1).getWebsite(),website);

    }
}
