package com.nyu.dbproject.dao.impl;

import com.nyu.dbproject.dao.TrackDao;
import com.nyu.dbproject.entity.Playlist;
import com.nyu.dbproject.entity.Track;
import com.nyu.dbproject.entity.Artist;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


/**
 * Track DAO Implement
 * @author  Yang Feng
 */
@Repository("trackDao")
public class TrackDaoImpl implements TrackDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
	

	@Override
	public Track getTrackByID(String tid) {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("getTrackById");
		query.setString("tid", tid);
		Track Track = (Track) query.uniqueResult();
		return Track;
	}

	@Override
	public List<Track> getTrackList() {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("getTrackList");
		List<Track> tracklist = query.list();
		return tracklist;
	}

	@Override
	public Long addTrack(Track track) {
		Session session = sessionFactory.getCurrentSession();
		Long tid=(Long) session.save(track);
		return tid;
	}

	@Override
	public void deleteTrack(Track track) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(track);
	}

	@Override
	public void updateTrack(Track track) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(track);
	}

	@Override
	public List<Track>  getTrackByKeyword(String keyword) {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("getTrackByTitle");
		query.setString("keyword", "%"+keyword + "%");
		List<Track> tracklist = query.list();
		System.out.println(keyword);
		return tracklist;
	}

	@Override
	public List<Track>  getTrackByArtistid(Artist artist) {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("getTrackByArtistId");
		query.setParameter("artist", artist);
		List<Track> tracklist = query.list();
		return tracklist;
	}

	@Override
	public List<Track> RandomPickFour()
	{
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery("SELECT * FROM track ORDER BY RAND()");
		query.addEntity(Track.class);
		query.setMaxResults(4);
		List<Track> tracklist = query.list();
		return tracklist;
	}

	@Override
	public List<Track> getTrackByGenre(String genre){
		Session session = sessionFactory.getCurrentSession();
		Query query =session.getNamedQuery("getTrackByGenre");
		query.setParameter("genre", genre);
		List<Track> tracklist = query.list();
		return tracklist;
	}

	@Override
	public Set<Track> addTrackToPlaylist(Playlist playlist, Track track){
		Session session = sessionFactory.getCurrentSession();
		track.addTrackToPlaylist(playlist);
		session.save(track);
		Set<Track> tracks = playlist.getTrackSet();
		return tracks;
	}

	@Override
	public Set<Track> deleteTrackFromPlaylist(Playlist playlist, Track track){
		Session session = sessionFactory.getCurrentSession();
		track.removechild(playlist);
		session.save(track);
		Set<Track> tracks = playlist.getTrackSet();
		return tracks;
	}
}
