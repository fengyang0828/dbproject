package com.nyu.dbproject.entity;

import java.io.Serializable;
import java.util.*;
/**
 * @author Yang Feng
 */

public class Album implements Serializable
{

    private static final long serialVersionUID = 1L;

    // Album ID
    private String alid;

    // Album name
    private String altitle;

    // Album aldate
    private String aldate;

    // Many to Many Track
    private Set<Track> trackset;

    private Artist artist;

    public String getAldate() {
        return aldate;
    }

    public void setAldate(String aldate) {
        this.aldate = aldate;
    }

    public String getAlid() {
        return alid;
    }

    public void setAlid(String alid) {
        this.alid = alid;
    }

    public String getAltitle() {
        return altitle;
    }

    public void setAltitle(String altitle) {
        this.altitle = altitle;
    }

    public Set<Track> getTrackset() {
        return trackset;
    }

    public void setTrackset(Set<Track> trackset) {
        this.trackset = trackset;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
