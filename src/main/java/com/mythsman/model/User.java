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

    String avatar;
    String website;
    String biography;
    String email;
    String phone;
    Date date;
    int followers;
    int following;
    int posts;

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public void setPosts(int posts) {
        this.posts = posts;
    }

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