package com.nyu.dbproject.service.impl;

import com.nyu.dbproject.dao.*;

import com.nyu.dbproject.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nyu.dbproject.service.UserService;

import java.util.*;

/**
 * User service Implement
 * @author  Yang Feng
 */

@Transactional(readOnly=false)
@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;

	@Autowired
	FollowDao followDao;

	@Autowired
	RateDao rateDao;

	@Autowired
	PlaylistDao playlistDao;

	@Autowired
	TrackDao trackDao;

	@Autowired
	FavoriteDao favoriteDao;

	@Autowired
	ArtistDao artistDao;

	@Autowired
	AlbumDao albumDao;

	@Autowired
	PlayDao playDao;


	@Override
	public User login(User modeluser) {
		User user = userDao.login(modeluser);
		return user;
	}

	@Override
	public void register(User user) {
		userDao.addUser(user);
	}

	@Override
	public void updateUser(User modeluser) {
		userDao.updateUser(modeluser);
	}

	@Override
	public User getUserbyId(Long id) {
		return userDao.getUserByID(id);
	}

	@Override
	public void Follow(Follow follow)
	{
		followDao.addFollow(follow);
	}


	@Override
	public boolean checkFollow(User user, User following){
		Follow f = followDao.checkfollow(user,following);
		if(f == null) return false;
		else return true;
	}

	@Override
	public void Unfollow(User user, User otheruser)
	{
		Follow f = followDao.checkfollow(user, otheruser);
		followDao.deleteFollow(f);
	}

	@Override
	public void Rate(Rate rate){
    	rateDao.addRate(rate);
	}

	@Override
	public Rate getTrackRate(User user, Track track){
		Rate rate = rateDao.getRateByuidtid(user, track);
		return rate;
	}

	@Override
	public void createPlaylist(Playlist playlist){
		playlistDao.addPlaylist(playlist);
	}

	@Override
	public void deletePlaylist(Playlist playlist){
		playlistDao.deletePlaylist(playlist);
	}

	@Override
	public List<User> displayallFollowing(User user)
	{
		List<Follow> followList= followDao.getFollowByuid(user);
		List<User> following = new ArrayList<>();
		for(Follow f : followList)
		{
			following.add(f.getFollowing());
		}
		return following;

	}

	@Override
	public List<User> displayallFollower(User user)
	{
		List<Follow> followList= followDao.getFollowByfollowid(user);
		List<User> follower = new ArrayList<>();
		for(Follow f : followList)
		{
			follower.add(f.getUser());
		}
		return follower;
	}

	@Override
	public void FavoriteArtist(Favorite favorite){
		favoriteDao.addFavorite(favorite);
	}

	@Override
	public void unFavoriteArtist(User user, Artist artist)
	{
		Favorite favorite = favoriteDao.getFavorite(user,artist);
		favoriteDao.deleteFavorite(favorite);
	}
	@Override
	public boolean checkFavorite(User user, Artist artist){
		Favorite favorite = favoriteDao.getFavorite(user, artist);
		if (favorite == null) return false;
		else return true;
	}

	@Override
	public Artist displayArtist(String aid){
		Artist artist = artistDao.getArtistByID(aid);
		return artist;
	}

	@Override
	public List<Album> displayAlbumByArtist(String aid)
	{
		Artist artist = artistDao.getArtistByID(aid);
		List<Album> album = albumDao.getAlbumByArtist(artist);
		return album;
	}

	@Override
	public List<Artist> getRelatedArtist(Artist artist){
		List<Artist> artistList = artistDao.getRelatedArtist(artist);
		return artistList;
	}


	@Override
	public List<Track> searchTrack(String keyword)
	{
		List<Track> list = trackDao.getTrackByKeyword(keyword);
		return list;
	}

	@Override
	public List<Artist> searchArtist(String keyword)
	{
		List<Artist> list = artistDao.getArtistByKeyword(keyword);
		return list;
	}

	@Override
	public List<Album> searchAlbum(String keyword)
	{
		List<Album> list = albumDao.getAlbumByKeyword(keyword);
		return list;
	}

	@Override
	public List<User> searchUser(String keyword)
	{
		List<User> list = userDao.getUserByuname(keyword);
		return list;
	}

	@Override
	public List<Playlist> searchPlaylist(String keyword)
	{
		List<Playlist> list = playlistDao.getPlaylistByKeyword(keyword);
		return list;
	}

	@Override
	public Album getAlbumByID (String alid)
	{
		Album album = albumDao.getAlbumByID(alid);
		return album;
	}

	@Override
	public void Play(Play play){
		playDao.addPlay(play);
	}
}
