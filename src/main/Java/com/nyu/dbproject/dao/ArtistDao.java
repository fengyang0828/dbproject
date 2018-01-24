package com.nyu.dbproject.dao;

import java.util.List;
import java.util.Map;

import com.nyu.dbproject.entity.Artist;

/**
 * Artist DAO interface
 * @author Yang Feng
 */
public interface ArtistDao {

	public Artist getArtistByID(String aid);
	public List<Artist> getArtistList();
	public String addArtist(Artist Artist);
	public void deleteArtist(Artist Artist);
	public List<Artist> getArtistByKeyword(String keyword);

	public List<Artist> getRelatedArtist(Artist artist);
}

