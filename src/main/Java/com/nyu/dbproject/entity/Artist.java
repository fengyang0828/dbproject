package com.nyu.dbproject.entity;

import java.io.Serializable;

import java.util.*;

/**
 * @author Yang Feng
 */

public class Artist implements Serializable
{

    private static final long serialVersionUID = 1L;

    // Artist ID
    private String aid;

    // Artist name
    private String aname;

    // Artist description
    private String adesc;

    private Set<Album> albumSet;


    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getAdesc() {
        return adesc;
    }

    public void setAdesc(String adesc) {
        this.adesc = adesc;
    }

    public Set<Album> getAlbumSet() {
        return albumSet;
    }

    public void setAlbumSet(Set<Album> albumSet) {
        this.albumSet = albumSet;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
