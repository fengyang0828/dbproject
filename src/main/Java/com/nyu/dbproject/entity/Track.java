package com.nyu.dbproject.entity;

import java.io.Serializable;
import java.util.*;

/**
 * 
 * @author Yang Feng
 */
public class Track implements Serializable
{
	private static final long serialVersionUID = 1L;

	// Track ID
	protected String tid;

	// Track name
	protected String ttitle;

	// The duration of Track (ms)
	protected long tduration;

	// Track genre
	protected String tgenre;

	// The object of the user
	protected Artist artist;

	// The set of album
	protected Set<Album> albumSet;

	// The set of Playlist
	protected Set<Playlist> playlistsSet = new HashSet<Playlist>();

//	// Rate for the Track
//	protected Set<Rate> rate;

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTtitle() {
		return ttitle;
	}

	public void setTtitle(String ttitle) {
		this.ttitle = ttitle;
	}

	public long getTduration() {
		return tduration;
	}

	public void setTduration(long tduration) {
		this.tduration = tduration;
	}

	public String getTgenre() {
		return tgenre;
	}

	public void setTgenre(String tgenre) {
		this.tgenre = tgenre;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public void setAlbumSet(Set<Album> albumSet) {
		this.albumSet = albumSet;
	}

	public Set<Album> getAlbumSet() {
		return albumSet;
	}

	public String getTid() {
		return tid;
	}

	public Set<Playlist> getPlaylistsSet() {
		return playlistsSet;
	}

	public void setPlaylistsSet(Set<Playlist> playlistsSet) {
		this.playlistsSet = playlistsSet;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		} else if (other instanceof Track) {
			Track otherTrack = (Track) other;
			if (otherTrack.getTid() == this.getTid()) {
				return true;
			}
		}
		return false;
	}

	public void removechild(Playlist playlist)
	{
		for(Playlist p :this.playlistsSet)
		{
			if(p.equals(playlist))
			{
				playlistsSet.remove(playlist);
				break;
			}
		}
	}

	public void addTrackToPlaylist(Playlist playlist)
	{
		this.playlistsSet.add(playlist);
	}
}
