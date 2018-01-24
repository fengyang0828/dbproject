package com.nyu.dbproject.service.impl;

import com.nyu.dbproject.dao.PlayDao;
import com.nyu.dbproject.dao.TrackDao;
import com.nyu.dbproject.dao.PlaylistDao;
import com.nyu.dbproject.dao.FollowDao;

import com.nyu.dbproject.entity.*;
import com.nyu.dbproject.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Track service Implement
 * @author  Yang Feng
 */

@Transactional(readOnly=false)
@Service("playlistService")
public class PlaylistServiceImpl implements PlaylistService{

	@Autowired
	TrackDao trackDao;

	@Autowired
	PlayDao playDao;

	@Autowired
	PlaylistDao playlistDao;

	@Autowired
	FollowDao followDao;

	@Override
	public List<Playlist> FollowingLatestPlaylist(User user){
		List<Follow> followlist = followDao.RandomPickFollow(user);
		List<Playlist> playlist = new ArrayList<>();
		for(Follow f : followlist)
		{
			User following = f.getFollowing();
			Playlist subplaylist = playlistDao.getLatestPlaylistByfollowid(following);
			if(subplaylist == null)
				continue;
			else
				playlist.add(subplaylist);
		}
		return playlist;
	}

	@Override
	public Playlist getPlaylistbyId(Long pid){
		Playlist playlist = playlistDao.getPlaylistByID(pid);
		return playlist;
	}

	@Override
	public List<Playlist> getPlaylistbyTitle(String ptitle){
		List<Playlist> playlist = playlistDao.getPlaylistByKeyword(ptitle);
		return playlist;
	}
	@Override
	public List<Playlist> displayMyPlaylist(User user){
		List<Playlist> playlist = playlistDao.getPlaylistByUserid(user);
		return playlist;
	}
	@Override
	public List<Playlist> displayFollowingPlaylist(User following){
		List<Playlist> playlist = playlistDao.getPlaylistByfollowid(following);
		return playlist;

	}

	@Override
	public void addTracktoPlaylist(Long pid, String tid){
		Playlist p = playlistDao.getPlaylistByID(pid);
		Track t = trackDao.getTrackByID(tid);
		p.getTrackSet().add(t);
		playlistDao.updatePlaylist(p);
	}

	@Override
	public void MakeplaylistPublic(Long pid){
		Playlist p = playlistDao.getPlaylistByID(pid);
		p.setPstatus(0);
		playlistDao.updatePlaylist(p);
	}

	@Override
	public void MakeplaylistPrivate(Long pid){
		Playlist p = playlistDao.getPlaylistByID(pid);
		p.setPstatus(1);
		playlistDao.updatePlaylist(p);
	}

	@Override
	public void updateplaylist(Playlist playlist){
		playlistDao.updatePlaylist(playlist);
	}

	@Override
	public void deleteplaylist(Playlist playlist){
		playlistDao.deletePlaylist(playlist);
	}

	@Override
	public Set<Track> addTrackToPlaylist(Playlist playlist, Track track)
	{
		Set<Track> tracks = trackDao.addTrackToPlaylist(playlist,track);
		return tracks;
	}

	@Override
	public Set<Track> deleteTrackFromPlaylist(Playlist playlist, Track track){
		Set<Track> tracks = trackDao.deleteTrackFromPlaylist(playlist,track);
		return tracks;
	}
}
