package com.mythsman.model;

import java.util.Date;

/**
 * Created by myths on 5/2/17.
 */
enum Gender{
    USELESS,
    MALE,
    FEMAIE,
    UNKNOWN
};
public class User {
    int uid;
    Gender gender;             //1-male 2-female 3-unknown
    String name;
    String salt;
    String password;
    String avatarUrl;
    String website;
    String biography;
    String email;
    String phoneNumber;
    Date date;
    int followers;
    int following;
    int posts;
    public User(String name,String salt,String password){
        this.name=name;
        this.salt=salt;
        this.password=password;
    }
}