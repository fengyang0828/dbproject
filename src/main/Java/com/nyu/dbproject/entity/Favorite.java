package com.nyu.dbproject.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Yang Feng
 */

public class Favorite implements Serializable
{

    private static final long serialVersionUID = 1L;

    // User ID
    private User user;

    // Artist ID
    private Artist artist;

    // Timestamp
    private String timestamp;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
