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


/**
 * @author Yang Feng
 * Playlist Controller
 */

@Controller
@RequestMapping("/album")
public class AlbumController {

	@Autowired
	private UserService userService;

	@Autowired
	private TrackService trackService;


	/**
	 * transfer to album
	 * @return
	 */
	@RequestMapping("/album_trans")
	public ModelAndView track_trans(HttpServletRequest request,Map<String,Object> model, String alid){
		User user= (User) request.getSession().getAttribute("SYSUSER");
		if(user == null) return new ModelAndView("signin");
		model.put("username", user.getUsername());
        model.put("uid", user.getUid());
		List <Track> tracklist = trackService.displayTrackByAlbum(alid);
		Album album = userService.getAlbumByID(alid);
		model.put("tracklist", tracklist);
		model.put("album",album);
		return new ModelAndView("album");
	}

}
