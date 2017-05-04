package com.mythsman.model;

import java.util.Date;

/**
 * Created by myths on 5/4/17.
 */
public class LoginTicket {
    int id;
    int uid;
    String ticket;
    Date expire;

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    int valid;

    public int getId() {
        return id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

}
