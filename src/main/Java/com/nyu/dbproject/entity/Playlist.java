package com.nyu.dbproject.entity;

import java.io.Serializable;
import java.util.*;

/**
 * 
 * @author Yang Feng
 */
public class Playlist implements Serializable
{
	private static final long serialVersionUID = 1L;

	// Playlist ID
	protected Long pid;

	// Playlist name
	protected String ptitle;

	// Playlist genre
	protected int pstatus;

	protected String pdate;

	// The object of the user
	protected User user;

	protected Set<Track> trackSet = new HashSet<Track>();

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getPtitle() {
		return ptitle;
	}

	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}

	public int getPstatus() {
		return pstatus;
	}

	public void setPstatus(int pstatus) {
		this.pstatus = pstatus;
	}

	public String getPdate() {
		return pdate;
	}

	public void setPdate(String pdate) {
		this.pdate = pdate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Track> getTrackSet() {
		return trackSet;
	}

	public void setTrackSet(Set<Track> trackSet) {
		this.trackSet = trackSet;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		} else if (other instanceof Playlist) {
			Playlist otherPlaylist = (Playlist) other;
			if (otherPlaylist.getPid() == this.getPid()) {
				return true;
			}
		}
		return false;
	}

}
