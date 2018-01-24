package com.nyu.dbproject.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Yang Feng
 */

public class Play implements Serializable
{

    private static final long serialVersionUID = 1L;

    // User ID
    private User user;

    // Track ID
    private Track track;

    // Timestamp
    private String timestamp;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
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
