package com.nyu.dbproject.entity;

import java.io.Serializable;

/**
 * @author Yang Feng
 */

public class PlaylistTrack implements Serializable
{

    private static final long serialVersionUID = 1L;

    // Playlist ID
    private Long pid;

    // Track ID
    private Track track;

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
