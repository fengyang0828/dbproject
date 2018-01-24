package com.nyu.dbproject.service;


import com.nyu.dbproject.entity.*;

import java.util.List;


/**
 * User service interface
 * @author Yang Feng
 */

public interface UserService {
	public void register(User user);
	public User login(User modeluser);
	public void updateUser(User modeluser);
	public User getUserbyId(Long id);

	public void Follow(Follow follow);
	public void Unfollow(User user, User otheruser);
	public boolean checkFollow(User user, User following);
	public List<User> displayallFollowing(User user);
	public List<User> displayallFollower(User user);

	public void Rate(Rate rate);
	public Rate getTrackRate(User user, Track track);

	public void createPlaylist(Playlist playlist);
	public void deletePlaylist(Playlist playlist);

	public void FavoriteArtist(Favorite favorite);
	public void unFavoriteArtist(User user, Artist artist);
	public boolean checkFavorite(User user, Artist artist);

	public List<Album> displayAlbumByArtist(String aid);

	public List<Track> searchTrack(String keyword);
	public List<Artist> searchArtist(String keyword);
	public List<Album> searchAlbum(String keyword);
	public List<User> searchUser(String keyword);
	public List<Playlist> searchPlaylist(String keyword);


	public Album getAlbumByID (String alid);

	public void Play(Play play);

	public Artist displayArtist(String aid);
	public List<Artist> getRelatedArtist(Artist artist);

}

