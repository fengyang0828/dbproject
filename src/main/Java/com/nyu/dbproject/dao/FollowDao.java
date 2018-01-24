package com.nyu.dbproject.dao;

import com.nyu.dbproject.entity.Follow;
import com.nyu.dbproject.entity.User;

import java.util.List;

/**
 * Follow DAO interface
 * @author Yang Feng
 */
public interface FollowDao {

	public List<Follow> getFollowByuid(User user);
	public List<Follow> getFollowByfollowid(User following);
	public Follow checkfollow(User user,User following);
	public void addFollow(Follow follow);
	public void deleteFollow(Follow follow);
	public void updateFollow(Follow modelfollow);

	public List<Follow> RandomPickFollow(User user);
}

