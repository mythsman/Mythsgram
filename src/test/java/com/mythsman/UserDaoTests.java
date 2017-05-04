package com.mythsman;

import com.mythsman.dao.UserDao;
import com.mythsman.model.User;
import com.mythsman.service.UserService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("/initDatabase.sql")
@Sql("/userDao.sql")
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
}
