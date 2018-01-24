package com.nyu.dbproject.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyu.dbproject.common.MD5;
import com.nyu.dbproject.dao.UserDao;
import com.nyu.dbproject.entity.User;


/**
 * User DAO Implement
 * @author  Yang Feng
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

	MD5 getmd5 = new MD5();

	@Override
	public User login(User modeluser) {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("user_login");
		query.setString("username", modeluser.getUsername());
		query.setString("password", modeluser.getUpassword());
		User user = (User) query.uniqueResult();
		if (user!= null)
		{
			return user;
		}
		return null;
	}

	@Override
	public User getUserByID(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("getUserByID");
		query.setLong("id", id);
		User user = (User) query.uniqueResult();
		return user;
	}

	@Override
	public User resetUserpassword(Long id) {
		User user = this.getUserByID(id);
		this.getSessionFactory().getCurrentSession().flush();
		user.setUpassword(getmd5.convertMD5("123456"));
		this.updateUser(user);
		return user;
	}

	@Override
	public List<User> getUserList() {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("getUserList");
		List<User> list = query.list();
		List<User> userlist = new ArrayList<User>();
		//遍历整个user表，只得到userlist
		for (User user:list)
		{
			userlist.add(user);
		}
		return userlist;
	}

	@Override
	public Long addUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		Long userid=(Long) session.save(user);
		return userid;
	}

	@Override
	public void deleteUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(user);
	}

	@Override
	public void updateUser(User modeluser) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(modeluser);
	}

	@Override
	public List<User> getUserByuname(String name) {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("getUserByname");
		query.setString("name", "%" + name + "%");
		List<User> userlist =query.list();
		return userlist;
	}
}
