package com.mythsman.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by myths on 5/8/17.
 */
@Service
public class JedisAdapter implements InitializingBean {
    private JedisPool pool;

    @Override
    public void afterPropertiesSet() throws Exception {
        pool = new JedisPool();
    }

    public void sadd(String key,String value){
        Jedis jedis=null;
        try{
            jedis=pool.getResource();
            jedis.sadd(key,value);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    public void srem(String key,String value){
        Jedis jedis=null;
        try{
            jedis=pool.getResource();
            jedis.srem(key,value);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    public boolean isMember(String key,String value){
        Jedis jedis=null;
        try{
            jedis=pool.getResource();
            return jedis.sismember(key,value);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis!=null)
                jedis.close();
        }
        return false;
    }

    public long scard(String key){
        Jedis jedis=null;
        try{
            jedis=pool.getResource();
            return jedis.scard(key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis!=null)
                jedis.close();
        }
        return 0;
    }
}
