package com.nyu.dbproject.dao.impl;

import com.nyu.dbproject.dao.RateDao;
import com.nyu.dbproject.entity.Rate;
import com.nyu.dbproject.entity.Track;
import com.nyu.dbproject.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Rate DAO Implement
 * @author  Yang Feng
 */
@Repository("rateDao")
public class RateDaoImpl implements RateDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }


	@Override
	public Rate getRateByuidtid(User user, Track track) {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("getRateByuidtid");
		query.setParameter("user", user);
		query.setParameter("track", track);
		Rate rate = (Rate)query.uniqueResult();
		return rate;
	}

	@Override
	public void addRate(Rate rate) {
		Session session = sessionFactory.getCurrentSession();
		session.save(rate);
	}

	@Override
	public void deleteRate(Rate rate) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(rate);
	}

	@Override
	public void updateRate(Rate modelrate) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(modelrate);
	}

}
