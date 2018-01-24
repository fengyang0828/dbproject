package com.nyu.dbproject.dao.impl;

import com.nyu.dbproject.dao.FollowDao;
import com.nyu.dbproject.entity.Follow;
import com.nyu.dbproject.entity.Track;
import com.nyu.dbproject.entity.User;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Follow DAO Implement
 * @author  Yang Feng
 */
@Repository("followDao")
public class FollowDaoImpl implements FollowDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }


	@Override
	public List<Follow> getFollowByuid(User user) {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("getFollowByuid");
		query.setParameter("user", user);
		List<Follow> followlist = query.list();
		return followlist;
	}

	@Override
	public List<Follow> getFollowByfollowid(User following) {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("getFollowByfollowid");
		query.setParameter("following", following);
		List<Follow> followerlist = query.list();
		return followerlist;
	}

	@Override
	public Follow checkfollow(User user, User following) {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("checkfollow");
		query.setParameter("user", user);
		query.setParameter("following", following);
		Follow follower = (Follow) query.uniqueResult();
		return follower;
	}


	@Override
	public void addFollow(Follow follow) {
		Session session = sessionFactory.getCurrentSession();
		session.save(follow);
	}

	@Override
	public void deleteFollow(Follow follow) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(follow);
	}

	@Override
	public void updateFollow(Follow modelfollow) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(modelfollow);
	}

	@Override
	public List<Follow> RandomPickFollow(User user){
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery("SELECT * FROM follow WHERE uid = ? ORDER BY RAND()");
		query.setParameter(0,user.getUid());
		query.addEntity(Follow.class);
		query.setMaxResults(5);
		List<Follow> followlist= query.list();
		return followlist;
	}

}
