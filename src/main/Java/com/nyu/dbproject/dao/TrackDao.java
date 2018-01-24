package com.nyu.dbproject.dao;

import com.nyu.dbproject.entity.Playlist;
import com.nyu.dbproject.entity.Track;
import com.nyu.dbproject.entity.Artist;


import java.util.List;
import java.util.Set;

/**
 * Track DAO interface
 * @author Yang Feng
 */
public interface TrackDao {

	public Track getTrackByID(String id);
	public List<Track> getTrackList();
	public Long addTrack(Track track);
	public void deleteTrack(Track track);
	public void updateTrack(Track track);
	public List<Track> getTrackByKeyword(String keyword);
	public List<Track> getTrackByArtistid(Artist artist);
	public List<Track> RandomPickFour();
	public List<Track> getTrackByGenre(String genre);

	public Set<Track> addTrackToPlaylist(Playlist playlist, Track track);
	public Set<Track> deleteTrackFromPlaylist(Playlist playlist, Track track);


}

