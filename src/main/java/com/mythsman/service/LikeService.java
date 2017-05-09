package com.mythsman.service;

import com.mythsman.dao.PostDao;
import com.mythsman.util.JedisAdapter;
import com.mythsman.util.JedisKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by myths on 17-5-4.
 */
@Service
public class LikeService {

    @Autowired
    UserComponent userComponent;

    @Autowired
    JedisAdapter jedisAdapter;

    @Autowired
    PostDao postDao;

    public String toggleLike(int postId){
        String key= JedisKeys.getLikeKey(postId);
        String uid=String.valueOf(userComponent.getUser().getId());
        if(jedisAdapter.sisMember(key,uid)){
            jedisAdapter.srem(key,uid);
            postDao.updateLikes(postId,(int)jedisAdapter.scard(key));
            return "rem";
        }else {
            jedisAdapter.sadd(key, uid);
            postDao.updateLikes(postId,(int)jedisAdapter.scard(key));
            return "add";
        }
    }

    public boolean isLike(int postId, int uid){
        String key= JedisKeys.getLikeKey(postId);
        String uids=String.valueOf(uid);
        if(jedisAdapter.sisMember(key,uids)){
            return true;
        }else{
            return false;
        }
    }

}
