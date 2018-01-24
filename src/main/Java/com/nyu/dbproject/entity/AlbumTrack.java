package com.nyu.dbproject.entity;

import java.io.Serializable;

/**
 * @author Yang Feng
 */

public class AlbumTrack implements Serializable
{
    private static final long serialVersionUID = 1L;

    // Album ID
    private Album album;

    // Track ID
    private Track track;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
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
