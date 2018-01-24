package com.nyu.dbproject.dao.impl;

import com.nyu.dbproject.dao.FavoriteDao;
import com.nyu.dbproject.entity.Artist;
import com.nyu.dbproject.entity.Favorite;
import com.nyu.dbproject.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;


/**
 * Favorite DAO Implement
 * @author  Yang Feng
 */
@Repository("favoriteDao")
public class FavoriteDaoImpl implements FavoriteDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }


	@Override
	public List<Favorite> getFavoriteByuid(User user) {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("getArtistByuid");
		query.setParameter("user", user);
		List<Favorite> favoritelist = query.list();
		return favoritelist;
	}

	@Override
	public void addFavorite(Favorite favorite) {
		Session session = sessionFactory.getCurrentSession();
		session.save(favorite);
	}

	@Override
	public void deleteFavorite(Favorite favorite) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(favorite);
	}

	@Override
	public void updateFavorite(Favorite modelfavorite) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(modelfavorite);
	}

	@Override
	public Favorite getFavorite(User user, Artist artist){
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("getFavorite");
		query.setParameter("user", user);
		query.setParameter("artist", artist);
		Favorite favorite = (Favorite) query.uniqueResult();
		return favorite;
	}

}
