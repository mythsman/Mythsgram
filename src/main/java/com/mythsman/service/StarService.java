package com.mythsman.service;

import com.mythsman.dao.PostDao;
import com.mythsman.util.JedisAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by myths on 17-5-4.
 */
@Service
public class StarService {

    @Autowired
    UserComponent userComponent;

    @Autowired
    JedisAdapter jedisAdapter;

    @Autowired
    PostDao postDao;

    public String toggleStar(int postId){
        String key=String.format("STAR:%d",postId);
        String uid=String.valueOf(userComponent.getUser().getId());
        if(jedisAdapter.isMember(key,uid)){
            jedisAdapter.srem(key,uid);
            postDao.updateLikes(postId,(int)jedisAdapter.scard(key));
            return "rem";
        }else {
            jedisAdapter.sadd(key, uid);
            postDao.updateLikes(postId,(int)jedisAdapter.scard(key));
            return "add";
        }
    }


}
