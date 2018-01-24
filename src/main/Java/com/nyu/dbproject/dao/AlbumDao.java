package com.nyu.dbproject.dao;

import com.nyu.dbproject.entity.Album;
import com.nyu.dbproject.entity.Artist;

import java.util.List;

/**
 * Album DAO interface
 * @author Yang Feng
 */
public interface AlbumDao {

	public Album getAlbumByID(String alid);
	public List<Album> getAlbumList();
	public Long addAlbum(Album album);
	public void deleteAlbum(Album album);
	public void updateAlbum(Album album);
	public List<Album> getAlbumByKeyword(String keyword);
	public List<Album> getAlbumByArtist(Artist artist);
}

