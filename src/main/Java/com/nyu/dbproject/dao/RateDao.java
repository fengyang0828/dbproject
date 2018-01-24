package com.nyu.dbproject.dao;

import com.nyu.dbproject.entity.Rate;
import com.nyu.dbproject.entity.Track;
import com.nyu.dbproject.entity.User;

import java.util.List;

/**
 * Rate DAO interface
 * @author Yang Feng
 */
public interface RateDao {

	public Rate getRateByuidtid(User user, Track track);
	public void addRate(Rate rate);
	public void deleteRate(Rate rate);
	public void updateRate(Rate modelrate);
}

