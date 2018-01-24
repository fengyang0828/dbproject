package com.nyu.dbproject.dao;

import com.nyu.dbproject.entity.Favorite;
import com.nyu.dbproject.entity.User;
import com.nyu.dbproject.entity.Artist;
import java.util.*;

/**
 * Favorite DAO interface
 * @author Yang Feng
 */
public interface FavoriteDao {

	public List<Favorite> getFavoriteByuid(User user);
	public void addFavorite(Favorite favorite);
	public void deleteFavorite(Favorite favorite);
	public void updateFavorite(Favorite modelfavorite);

	public Favorite getFavorite(User user, Artist artist);
}

