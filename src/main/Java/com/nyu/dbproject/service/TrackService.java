package com.nyu.dbproject.service;

import com.nyu.dbproject.entity.*;

import java.util.*;


/**
 * Track service interface
 * @author Yang Feng
 */

public interface TrackService {
	public List<Track> RandomPickFour();
	public Track getTrackbyId(String tid);
	public List<Track> getTrackbyTtitle(String ttitle);
	public List<Track> displayLastFiveTrack(User user);
	public List<Track> displayRecentTrack(User user);

	public List<Track> displayTrackByAlbum(String alid);
	public List<Track> displayTrackByArtist(Artist artist);


	public List<Track> searchGenre(String genre);
}

