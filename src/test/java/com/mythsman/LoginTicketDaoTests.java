package com.mythsman;

import com.mythsman.dao.LoginTicketDao;
import com.mythsman.model.LoginTicket;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("/loginTicket.sql")
public class LoginTicketDaoTests {
    private static final Logger logger = Logger.getLogger(LoginTicketDaoTests.class);

    @Autowired
    private LoginTicketDao loginTicketDao;

    @Test
    public void addTicketTests(){
        LoginTicket loginTicket=new LoginTicket();
        String ticket="ticket";
        loginTicket.setUid(1);
        loginTicket.setTicket(ticket);
        loginTicket.setValid(1);
        loginTicket.setExpire(new Date());
        loginTicketDao.addTicket(loginTicket);
        Assert.assertNotNull(loginTicketDao.selectByTicket(ticket));
        loginTicketDao.updateValid(ticket,0);
        loginTicket=loginTicketDao.selectByTicket(ticket);
        Assert.assertEquals(loginTicket.getValid(),0);
    }

}
