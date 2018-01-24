package com.nyu.dbproject.dao.impl;

import com.nyu.dbproject.dao.PlayDao;
import com.nyu.dbproject.entity.User;
import com.nyu.dbproject.entity.Play;
import com.nyu.dbproject.entity.Track;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
 * Play DAO Implement
 * @author  Yang Feng
 */
@Repository("playDao")
public class PlayDaoImpl implements PlayDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }


	@Override
	public List<Play> getPlayByuid(User user) {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("getPlayByuid");
		query.setParameter("user", user);
		query.setMaxResults(50);
		List<Play> playlist = query.list();
		return playlist;
	}

	@Override
	public List<Play> getPlayBytid(Track track) {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("getPlayBytid");
		query.setParameter("track", track);
		List<Play> playlist = query.list();
		return playlist;
	}

	@Override
	public void addPlay(Play play) {
		Session session = sessionFactory.getCurrentSession();
		session.save(play);
	}

	@Override
	public void deletePlay(Play play) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(play);
	}

	@Override
	public void updatePlay(Play modelplay) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(modelplay);
	}

	@Override
	public List<Play> getRecent(User user, int count) {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("getPlayByuid");
		query.setParameter("user", user);
		query.setMaxResults(count);
		List<Play> playlist = query.list();
		return playlist;
	}
}
