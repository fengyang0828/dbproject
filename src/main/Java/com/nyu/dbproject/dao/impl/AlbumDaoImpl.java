package com.nyu.dbproject.dao.impl;

import com.nyu.dbproject.entity.Album;
import com.nyu.dbproject.dao.AlbumDao;
import com.nyu.dbproject.entity.Artist;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Album DAO Implement
 * @author  Yang Feng
 */
@Repository("albumDao")
public class AlbumDaoImpl implements AlbumDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
	

	@Override
	public Album getAlbumByID(String alid) {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("getAlbumByID");
		query.setString("alid", alid);
		Album Album = (Album) query.uniqueResult();
		return Album;
	}

	@Override
	public List<Album> getAlbumList() {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("getAlbumList");
		List<Album> albumlist = query.list();
		return albumlist;
	}

	@Override
	public Long addAlbum(Album album) {
		Session session = sessionFactory.getCurrentSession();
		Long alid=(Long) session.save(album);
		return alid;
	}

	@Override
	public void deleteAlbum(Album album) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(album);
	}

	@Override
	public void updateAlbum(Album album) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(album);
	}

	@Override
	public List<Album>  getAlbumByKeyword(String keyword) {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("getAlbumByKeyword");
		query.setString("keyword", "%" + keyword + "%");
		List<Album> albumlist = query.list();
		return albumlist;
	}
	@Override
	public List<Album> getAlbumByArtist(Artist artist){
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("getAlbumByArtist");
		query.setParameter("artist", artist);
		List<Album> albumlist = query.list();
		return albumlist;
	}
}
