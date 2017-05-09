package com.mythsman;

import com.mythsman.util.JedisAdapter;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest

public class JedisAdapterTests {
    private static final Logger logger = Logger.getLogger(JedisAdapterTests.class);

    @Autowired
    JedisAdapter jedisAdapter;

    @Before
    public void before(){
        jedisAdapter.clear();
    }

    @Test
    public void tests(){

        jedisAdapter.sadd("testKey","testValue");
        Assert.assertEquals(jedisAdapter.sisMember("testKey","testValue"),true);
        Assert.assertEquals(jedisAdapter.scard("testKey"),1);

        jedisAdapter.srem("testKey","testValue");
        Assert.assertEquals(jedisAdapter.sisMember("testKey","testValue"),false);
        Assert.assertEquals(jedisAdapter.scard("testKey"),0);

        jedisAdapter.sadd("testKey","testValue");
        jedisAdapter.clear();
        Assert.assertEquals(jedisAdapter.sisMember("testKey","testValue"),false);

    }

    @After
    public void after(){
        jedisAdapter.clear();
    }

}
