package com.mythsman.model;

import org.springframework.stereotype.Component;

/**
 * Created by myths on 5/4/17.
 */
@Component
public class UserComponent {
    private static ThreadLocal<User> users=new ThreadLocal<User>();

    public User getUser(){
        return users.get();
    }

    public void setUser(User user){
        users.set(user);
    }

    public void clear(){
        users.remove();
    }
}
