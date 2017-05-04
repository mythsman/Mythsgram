package com.mythsman.model;


import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by myths on 5/2/17.
 */
public class User {
    public static String MALE = "male";
    public static String FEMALE = "female";
    public static String UNKNOWN = "unknown";

    int id;
    String gender;             //male female unknown
    String name;
    String salt;
    String password;

    public int getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getSalt() {
        return salt;
    }

    public String getPassword() {
        return password;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getWebsite() {
        return website;
    }

    public String getBiography() {
        return biography;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Date getDate() {
        return date;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    public int getPosts() {
        return posts;
    }

    String avatar;
    String website;
    String biography;
    String email;
    String phone;
    Date date;
    int followers;
    int following;
    int posts;

    public User(int id, String gender, String name, String salt, String password, String avatar, String website, String biography, String email, String phone, Timestamp timestamp, int followers, int following, int posts) {
        this.id = id;
        this.gender = gender;
        this.name = name;
        this.salt = salt;
        this.password = password;
        this.avatar = avatar;
        this.website = website;
        this.biography = biography;
        this.email = email;
        this.phone = phone;
        this.date =new Date(timestamp.getTime());
        this.followers = followers;
        this.following = following;
        this.posts = posts;
    }
}