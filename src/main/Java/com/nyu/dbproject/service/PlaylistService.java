package com.nyu.dbproject.service;

import com.nyu.dbproject.entity.Playlist;
import com.nyu.dbproject.entity.Track;
import com.nyu.dbproject.entity.User;

import java.util.List;
import java.util.Set;


/**
 * Playlist service interface
 * @author Yang Feng
 */

public interface PlaylistService {
	public List<Playlist> FollowingLatestPlaylist(User following);
	public Playlist getPlaylistbyId(Long pid);
	public List<Playlist> getPlaylistbyTitle(String ptitle);
	public List<Playlist> displayMyPlaylist(User user);
	public List<Playlist> displayFollowingPlaylist(User following);
	public void addTracktoPlaylist(Long pid, String tid);
	public void MakeplaylistPublic(Long pid);
	public void MakeplaylistPrivate(Long pid);
	public void updateplaylist(Playlist playlist);
	public void deleteplaylist(Playlist playlist);


	public Set<Track> addTrackToPlaylist(Playlist playlist, Track track);
	public Set<Track> deleteTrackFromPlaylist(Playlist playlist, Track track);

}

