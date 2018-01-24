package com.nyu.dbproject.dao.impl;

import com.nyu.dbproject.dao.PlaylistDao;
import com.nyu.dbproject.entity.Playlist;
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
 * Playlist DAO Implement
 * @author  Yang Feng
 */
@Repository("playlistDao")
public class PlaylistDaoImpl implements PlaylistDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
	

	@Override
	public Playlist getPlaylistByID(Long pid) {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("getPlaylistByID");
		query.setLong("pid", pid);
		Playlist Playlist = (Playlist) query.uniqueResult();
		return Playlist;
	}

	@Override
	public List<Playlist> getPlaylistList() {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("getPlaylistList");
		List<Playlist> albumlist = query.list();
		return albumlist;
	}

	@Override
	public Long addPlaylist(Playlist playlist) {
		Session session = sessionFactory.getCurrentSession();
		Long pid=(Long) session.save(playlist);
		return pid;
	}

	@Override
	public void deletePlaylist(Playlist playlist) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(playlist);
	}

	@Override
	public void updatePlaylist(Playlist playlist) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(playlist);
	}

	@Override
	public List<Playlist>  getPlaylistByKeyword(String keyword) {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("getPlaylistByTitle");
		query.setString("keyword", "%" + keyword + "%");
		List<Playlist> albumlist = query.list();
		return albumlist;
	}

	@Override
	public List<Playlist>  getPlaylistByUserid(User user) {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("getPlaylistByuid");
		query.setParameter("user", user);
		List<Playlist> playlist = query.list();
		return playlist;
	}

	@Override
	public List<Playlist>  getPlaylistByfollowid(User user) {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("getPlaylistByfollowid");
		query.setParameter("user", user);
		List<Playlist> playlist = query.list();
		return playlist;
	}

	@Override
	public Playlist  getLatestPlaylistByfollowid(User user){
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("getPlaylistByfollowid");
		query.setParameter("user", user);
		query.setMaxResults(1);
		Playlist playlist = (Playlist)query.uniqueResult();
		return playlist;
	}
}
