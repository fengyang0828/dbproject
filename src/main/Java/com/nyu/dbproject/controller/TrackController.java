package com.nyu.dbproject.controller;

import com.nyu.dbproject.entity.*;
import com.nyu.dbproject.service.PlaylistService;
import com.nyu.dbproject.service.TrackService;
import com.nyu.dbproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author Yang Feng
 * Playlist Controller
 */

@Controller
@RequestMapping("/track")
public class TrackController {

	@Autowired
	private UserService userService;

	@Autowired
	private TrackService trackService;

	@Autowired
	private PlaylistService playlistService;

	/**
	 * transfer to track
	 * @return
	 */
	@RequestMapping("/track_trans")
	public ModelAndView track_trans(HttpServletRequest request,Map<String,Object> model, String tid){
		User user= (User) request.getSession().getAttribute("SYSUSER");
		if(user == null) return new ModelAndView("signin");
		model.put("username", user.getUsername());
        model.put("uid", user.getUid());
		Track track = trackService.getTrackbyId(tid);
		List<Playlist> playlists = playlistService.displayMyPlaylist(user);
		model.put("track", track);
		model.put("playlists",playlists);
		Rate rate = userService.getTrackRate(user,track);
		if (rate != null)
			model.put("score", rate.getScore());
		else
			model.put("score", 0);
		return new ModelAndView("track");
	}

	/**
	 * Rate
	 * @return
	 */
	@RequestMapping(value = "/rate", method = {RequestMethod.POST})
	public void rate(HttpServletRequest request,Map<String,Object> model){
		User user = (User) request.getSession().getAttribute("SYSUSER");
		if(user == null) return;
		Track track = trackService.getTrackbyId(request.getParameter("tid"));
		Rate rate = new Rate();
		rate.setScore(Integer.parseInt (request.getParameter("score")));
		rate.setUser(user);
		rate.setTrack(track);
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
		String timestamp = dateFormat.format( now );
		rate.setTimestamp(timestamp);
		userService.Rate(rate);
	}

	/**
	 * Add track to playlist
	 * @return
	 */
	@RequestMapping("/add_track_to_playlist")
	public ModelAndView add_track_to_playlist(HttpServletRequest request,Map<String,Object> model){
		User user= (User) request.getSession().getAttribute("SYSUSER");
		if(user == null) return new ModelAndView("signin");
		model.put("username", user.getUsername());
        model.put("uid", user.getUid());
		String pid = request.getParameter("pid");
		String tid = request.getParameter("tid");
		Long id=Long.parseLong(pid);
		Playlist playlist = playlistService.getPlaylistbyId(id);
		Track track = trackService.getTrackbyId(tid);
		track.addTrackToPlaylist(playlist);
		Set<Track> tracks = playlistService.addTrackToPlaylist(playlist,track);
		model.put("tracks", tracks);
		model.put("tid",tid);
		return new ModelAndView("redirect:/track/track_trans.do");
	}

	/**
	 * Play
	 * @return
	 */
	@RequestMapping(value = "/play", method = {RequestMethod.POST})
	public void play(HttpServletRequest request,Map<String,Object> model){
		User user = (User) request.getSession().getAttribute("SYSUSER");
		if(user == null) return;
		Track track = trackService.getTrackbyId(request.getParameter("tid"));
		Play play = new Play();
		play.setTrack(track);
		play.setUser(user);
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
		String timestamp = dateFormat.format( now );
		play.setTimestamp(timestamp);
		userService.Play(play);
	}
}
