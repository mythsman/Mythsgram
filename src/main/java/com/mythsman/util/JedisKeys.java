package com.mythsman.util;

/**
 * Created by myths on 5/9/17.
 */
public class JedisKeys {
    public static String getLikeKey(int postId){
        return String.format("LIKE:%d",postId);
    }

    public static String getFollowKey(int userId){
        return String.format("FOLLOW:%d",userId);
    }

    public static String getFansKey(int userId){
        return String.format("FANS:%d",userId);
    }
}
