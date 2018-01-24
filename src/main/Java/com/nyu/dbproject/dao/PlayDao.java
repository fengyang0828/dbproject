package com.nyu.dbproject.dao;

import com.nyu.dbproject.entity.Play;
import com.nyu.dbproject.entity.User;
import com.nyu.dbproject.entity.Track;



import java.util.List;

/**
 * Play DAO interface
 * @author Yang Feng
 */
public interface PlayDao {

	public List<Play> getPlayByuid(User user);
	public List<Play> getPlayBytid(Track track);
	public void addPlay(Play play);
	public void deletePlay(Play play);
	public void updatePlay(Play modelplay);
	public List<Play> getRecent(User user, int count);

}

