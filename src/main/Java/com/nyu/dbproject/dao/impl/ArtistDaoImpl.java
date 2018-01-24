package com.nyu.dbproject.dao.impl;

import com.nyu.dbproject.dao.ArtistDao;
import com.nyu.dbproject.entity.Artist;
import com.nyu.dbproject.entity.Favorite;
import com.nyu.dbproject.entity.Follow;
import com.nyu.dbproject.entity.Track;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Artist DAO Implement
 * @author  Yang Feng
 */
@Repository("artistDao")
public class ArtistDaoImpl implements ArtistDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

	@Override
	public Artist getArtistByID(String aid) {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("getArtistByID");
		query.setString("aid", aid);
		Artist Artist = (Artist) query.uniqueResult();
		return Artist;
	}

	@Override
	public List<Artist> getArtistList() {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("getArtistList");
		List<Artist> artistlist = query.list();
		return artistlist;
	}

	@Override
	public String addArtist(Artist Artist) {
		Session session = sessionFactory.getCurrentSession();
		String artistid=(String) session.save(Artist);
		return artistid;
	}

	@Override
	public void deleteArtist(Artist Artist) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(Artist);
	}

	@Override
	public List<Artist> getArtistByKeyword(String keyword) {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("getArtistByKeyword");
		query.setString("keyword", "%" + keyword + "%");
		List<Artist> artistlist = query.list();
		return artistlist;
	}

	@Override
	public List<Artist> getRelatedArtist(Artist artist){
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery("SELECT f1.aid FROM Favoirte f1, Favoirte f2 WHERE f2.aid = ? and f1.uid = f2.uid and f1.aid <> f2.aid GROUP BY f1.aid, f2.aid HAVING COUNT(f1.uid) >= 2");
		query.setParameter(0,artist.getAid());
		List<String> aidlist = query.list();
		List<Artist> artistlist = new ArrayList<>();
		for(String s: aidlist){
			Artist a = getArtistByID(s);
			artistlist.add(a);
		}
		return artistlist;
		}

}
