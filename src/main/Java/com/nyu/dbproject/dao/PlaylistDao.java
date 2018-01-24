package com.nyu.dbproject.dao;

import com.nyu.dbproject.entity.Playlist;
import com.nyu.dbproject.entity.User;


import java.util.List;

/**
 * Playlist DAO interface
 * @author Yang Feng
 */
public interface PlaylistDao {

	public Playlist getPlaylistByID(Long id);
	public List<Playlist> getPlaylistList();
	public Long addPlaylist(Playlist playlist);
	public void deletePlaylist(Playlist playlist);
	public void updatePlaylist(Playlist playlist);
	public List<Playlist> getPlaylistByKeyword(String keyword);
	public List<Playlist> getPlaylistByUserid(User user);
	public List<Playlist>  getPlaylistByfollowid(User user);
	public Playlist  getLatestPlaylistByfollowid(User user);

}

