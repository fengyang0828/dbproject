package com.nyu.dbproject.controller;
import com.nyu.dbproject.entity.Playlist;
import com.nyu.dbproject.entity.Track;
import com.nyu.dbproject.entity.User;
import com.nyu.dbproject.service.PlaylistService;
import com.nyu.dbproject.service.TrackService;
import com.nyu.dbproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.*;
import java.util.Map;



/**
 * @author Yang Feng
 * Playlist Controller
 */

@Controller
@RequestMapping("/playlist")
public class PlaylistController {

	@Autowired
	private UserService userService;

	@Autowired
	private TrackService trackService;

	@Autowired
	private PlaylistService playlistService;

	/**
	 * transfer to playlists page
	 * @return
	 */
	@RequestMapping("/playlist_trans")
	public ModelAndView playlist_trans(HttpServletRequest request,Map<String,Object> model){
		User user= (User) request.getSession().getAttribute("SYSUSER");
		if(user == null) return new ModelAndView("signin");
		model.put("username", user.getUsername());
        model.put("uid", user.getUid());
		List<Playlist> playlists = playlistService.displayMyPlaylist(user);
		model.put("playlists", playlists);
		return new ModelAndView("playlists");
	}

	/**
	 * add_playlist
	 * @return
	 */
	@RequestMapping("/add_playlist")
	public ModelAndView add_playlist(HttpServletRequest request,Map<String,Object> model){
		User user= (User) request.getSession().getAttribute("SYSUSER");
		if(user == null) return new ModelAndView("signin");
		model.put("username", user.getUsername());
        model.put("uid", user.getUid());
		Playlist playlist = new Playlist();
		playlist.setUser(user);
		playlist.setPstatus(0);
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
		String pdate = dateFormat.format( now );
		playlist.setPdate(pdate);
		playlist.setPtitle(request.getParameter("name"));
		userService.createPlaylist(playlist);
		return new ModelAndView("redirect:/playlist/playlist_trans.do");
	}

	/**
	 * Make playlist public/unpublic
	 * @return
	 */
	@RequestMapping(value = "/make_playlist_public", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView make_playlist_public(HttpServletRequest request,Map<String,Object> model,String id){

		User user= (User) request.getSession().getAttribute("SYSUSER");
		if(user == null) return new ModelAndView("signin");
		model.put("username", user.getUsername());
        model.put("uid", user.getUid());
		Long pid=Long.parseLong(id);
		Playlist playlist = playlistService.getPlaylistbyId(pid);
		if(playlist.getPstatus() == 0)
		{
			playlist.setPstatus(1);
		}
		else
		{
			playlist.setPstatus(0);
		}
		playlistService.updateplaylist(playlist);
		return new ModelAndView("redirect:/playlist/playlist_trans.do");
	}

	/**
	 * delete playlist
	 * @return
	 */
	@RequestMapping(value = "/delete_playlist", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView delete_playlist(HttpServletRequest request,Map<String,Object> model,String id){
		User user= (User) request.getSession().getAttribute("SYSUSER");
		if(user == null) return new ModelAndView("signin");
		model.put("username", user.getUsername());
        model.put("uid", user.getUid());
		Long pid=Long.parseLong(id);
		Playlist playlist = playlistService.getPlaylistbyId(pid);
		for(Track t: playlist.getTrackSet())
		{
			Track track = trackService.getTrackbyId(t.getTid());
			playlistService.deleteTrackFromPlaylist(playlist,t);
		}
		Set<Track> tracks = playlist.getTrackSet();
		playlistService.deleteplaylist(playlist);
		return new ModelAndView("redirect:/playlist/playlist_trans.do");
	}

	/**
	 * transfer to one playlist page
	 * @return
	 */
	@RequestMapping(value = "/one_playlist_trans",method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView one_playlist_trans(HttpServletRequest request,Map<String,Object> model,String id){
		User user= (User) request.getSession().getAttribute("SYSUSER");
		if(user == null) return new ModelAndView("signin");
		model.put("username", user.getUsername());
        model.put("uid", user.getUid());
		Long pid=Long.parseLong(id);
		Playlist playlist = playlistService.getPlaylistbyId(pid);
		Set<Track> tracks = new HashSet<Track>();
		if (playlist.getUser().getUid() != user.getUid() && playlist.getPstatus() == 1)
		{
			return new ModelAndView("404");

		}
		tracks = playlist.getTrackSet();
		if(user.getUid() == playlist.getUser().getUid()) {
			model.put("isOther", 0);
		} else {
			model.put("isOther", 1);
		}
		model.put("tracks", tracks);
		model.put("pid",pid);
		model.put("playlist",playlist);
		return new ModelAndView("playlist");
	}

	/**
	 * delete track from playlist
	 * @return
	 */
	@RequestMapping("/delete_track_from_playlist")
	public ModelAndView delete_track_from_playlist(HttpServletRequest request,Map<String,Object> model, String pid, String tid){
		User user= (User) request.getSession().getAttribute("SYSUSER");
		if(user == null) return new ModelAndView("signin");
		model.put("username", user.getUsername());
        model.put("uid", user.getUid());
		Long id=Long.parseLong(pid);
		Playlist playlist = playlistService.getPlaylistbyId(id);
		Track track = trackService.getTrackbyId(tid);
		Set<Track> tracks = playlistService.deleteTrackFromPlaylist(playlist,track);
		model.put("tracks", tracks);
		model.put("id",pid);
		return new ModelAndView("redirect:/playlist/one_playlist_trans.do");
	}


}
