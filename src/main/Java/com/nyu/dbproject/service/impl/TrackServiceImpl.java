package com.nyu.dbproject.service.impl;

import com.nyu.dbproject.dao.TrackDao;
import com.nyu.dbproject.dao.PlayDao;
import com.nyu.dbproject.dao.AlbumDao;
import com.nyu.dbproject.entity.*;
import com.nyu.dbproject.service.TrackService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Track service Implement
 * @author  Yang Feng
 */

@Transactional(readOnly=false)
@Service("trackService")
public class TrackServiceImpl implements TrackService{

	private Logger logger = Logger.getLogger(TrackService.class);

	@Autowired
	TrackDao trackDao;

	@Autowired
	PlayDao playDao;

	@Autowired
	AlbumDao albumDao;

	@Override
	public List<Track> RandomPickFour(){
		List<Track> randomtracklist= trackDao.RandomPickFour();
		logger.debug("Here is a debug");
		return randomtracklist;
	}

	@Override
	public Track getTrackbyId(String tid){
		Track track = trackDao.getTrackByID(tid);
		return track;
	}

	@Override
	public List<Track> getTrackbyTtitle(String ttitle){
		List<Track> tracklist = trackDao.getTrackByKeyword(ttitle);
		return tracklist;
	}

	@Override
	public List<Track> displayLastFiveTrack(User user){
		List<Play> playlist = playDao.getRecent(user,5);
		List<Track> track = new ArrayList<>();
		for(Play p: playlist)
		{
			Track t = p.getTrack();
			track.add(t);
		}
		return track;
	}

	@Override
	public List<Track> displayRecentTrack(User user){
		List<Play> playlist = playDao.getRecent(user,50);
		List<Track> track = new ArrayList<>();
		for(Play p: playlist)
		{
			Track t = p.getTrack();
			track.add(t);
		}
		return track;
	}

	@Override
	public List<Track> displayTrackByAlbum(String alid)
	{
		Album album = albumDao.getAlbumByID(alid);
		List<Track> tracklist = new ArrayList<>();
		for(Track t: album.getTrackset())
		{
			tracklist.add(t);
		}
		return tracklist;
	}

	@Override
	public List<Track> searchGenre(String genre)
	{
		List<Track> tracklist = trackDao.getTrackByGenre(genre);
		return tracklist;
	}

	@Override
	public List<Track> displayTrackByArtist(Artist artist){
		List<Track>  tracks = trackDao.getTrackByArtistid(artist);
		return tracks;

	}


}
