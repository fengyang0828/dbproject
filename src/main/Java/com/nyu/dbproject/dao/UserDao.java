package com.nyu.dbproject.dao;

import java.util.List;

import com.nyu.dbproject.entity.User;

/**
 * User DAO interface
 * @author Yang Feng
 */
public interface UserDao {

	public User getUserByID(Long id);
	public User resetUserpassword(Long id);
	public List<User> getUserList();
	public User login(User user);
	public Long addUser(User user);
	public void deleteUser(User user);
	public void updateUser(User modeluser);
	public List<User> getUserByuname(String name);

}

