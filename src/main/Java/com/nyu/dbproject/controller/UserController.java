package com.nyu.dbproject.controller;
;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nyu.dbproject.entity.*;
import com.nyu.dbproject.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nyu.dbproject.common.MD5;
import com.nyu.dbproject.service.UserService;
import com.nyu.dbproject.service.TrackService;



/**
 * @author Yang Feng
 * User Controller
 */

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private TrackService trackService;

	@Autowired
	private PlaylistService playlistService;

	 MD5 md5 = new MD5();
	/**
	 * 方式一： 使用ModelAndView return new ModelAndView("redirect:/toList");
	 * 这样可以重定向到toList这个方法 方式二：返回String return "forward:index.jsp"; return
//	 * "forward:user.do?method=reg5"; //转发 return
//	 * "redirect:user.do?method=reg5"; //重定向 return
//	 * "redirect:http://www.baidu.com"; //重定向
//	 */
//
	/**
	 * transfer to register page
	 * @return
	 */
	@RequestMapping("/login_trans")
	    public ModelAndView login_trans(HttpServletRequest request){
		User user= (User) request.getSession().getAttribute("SYSUSER");
		if(user == null)
			return new ModelAndView("signin");
		else
			return new ModelAndView("redirect:/user/index_trans.do");
	    }

	/**
	 * transfer to register page
	 * @return
	 */
	@RequestMapping("/register_trans")
    public ModelAndView register_trans(HttpServletRequest request){
		User user= (User) request.getSession().getAttribute("SYSUSER");
		if(user == null)
			return new ModelAndView("signup");
		else
			return new ModelAndView("redirect:/user/index_trans.do");
	}


	/**
	 * transfer to index page
	 * @return
	 */
	@RequestMapping("/index_trans")
	public ModelAndView index_trans(HttpServletRequest request,Map<String,Object> model){

		User user= (User) request.getSession().getAttribute("SYSUSER");
		if(user == null) return new ModelAndView("signin");

		model.put("username", user.getUsername());
		model.put("uid", user.getUid());

		List<Track> randomtracklist = trackService.RandomPickFour();
		List<Track> recenttracklist = trackService.displayLastFiveTrack(user);
		List<Playlist> randomlatestplaylist = playlistService.FollowingLatestPlaylist(user);

		model.put("randomtracklist", randomtracklist);
		model.put("recenttracklist", recenttracklist);
		model.put("randomlatestplaylist", randomlatestplaylist);
		return new ModelAndView("index");
	}

	/**
	 * transfer to update user profile
	 */
	@RequestMapping("/user_info_trans")
    public ModelAndView user_info_trans(HttpServletRequest request,Map<String,Object> model){
		User user= (User) request.getSession().getAttribute("SYSUSER");
		if(user == null) return new ModelAndView("signin");
		model.put("username", user.getUsername());
        model.put("uid", user.getUid());
		model.put("uname", user.getUname());
		model.put("email", user.getUemail());
		model.put("city",user.getUcity());
		ModelAndView mav=new ModelAndView("settings");
		return mav;
    }

	/**
	 * transfer to history
	 */
	@RequestMapping(value = "/history", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView history(HttpServletRequest request,Map<String,Object> model){
		User user= (User) request.getSession().getAttribute("SYSUSER");
		if(user == null) return new ModelAndView("signin");
		model.put("username", user.getUsername());
        model.put("uid", user.getUid());
		List<Track> tracks = trackService.displayRecentTrack(user);
		System.out.println(tracks.size());
		model.put("tracks", tracks);
		ModelAndView mav=new ModelAndView("history");
		return mav;
	}

	/**
	 * transfer to artist
	 */
	@RequestMapping(value = "/artist_trans", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView artist_trans(HttpServletRequest request,Map<String,Object> model,String aid){
		User user= (User) request.getSession().getAttribute("SYSUSER");
		if(user == null) return new ModelAndView("signin");
		model.put("username", user.getUsername());
        model.put("uid", user.getUid());
		Artist artist = userService.displayArtist(aid);
		List<Album> albums = userService.displayAlbumByArtist(aid);
		List<Track> tracks = trackService.displayTrackByArtist(artist);
		List<Artist> artistList = userService.getRelatedArtist(artist);
		if(userService.checkFavorite(user,artist))
		{
			model.put("isFavorite", 1);
		}
		else
		{
			model.put("isFavorite", 0);
		}
		model.put("albums", albums);
		System.out.println(albums.size());
		model.put("tracks", tracks);
		System.out.println(tracks.size());
		model.put("artist", artist);
		model.put("artistlist",artistList);
		ModelAndView mav=new ModelAndView("artist");
		return mav;
	}

	/**
	 * login
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login",  method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView login(HttpServletRequest request,Map<String, Object> model) {
		User modeluser = new User();
		modeluser.setUsername(request.getParameter("username"));
		String password = md5.convertMD5(request.getParameter("password"));
		modeluser.setUpassword(password);
		User user = userService.login(modeluser);
		if(user == null)
		{
			return new ModelAndView("login","fail_login1","The username or password is not correct!");
		}
		else
		{
            request.getSession().setAttribute("SYSUSER", user);
            return new ModelAndView("redirect:/user/index_trans.do");
		}
	}

	/**
	 * log out
	 */
	@RequestMapping(value = "/log_out",  method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView log_out(HttpServletRequest request,Map<String, Object> model) {
		request.getSession().removeAttribute("SYSUSER");
		request.getSession().invalidate();
		return new ModelAndView("signin");
	}

	/**
	 * register
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request,HttpServletResponse response) {
		User modeluser = new User();
		modeluser.setUsername(request.getParameter("username"));
		String password = md5.convertMD5(request.getParameter("password"));
		modeluser.setUpassword(password);
		modeluser.setUemail(request.getParameter("email"));
		modeluser.setUcity(request.getParameter("city"));
		modeluser.setUname(request.getParameter("name"));
		try
		{
			userService.register(modeluser);
			return new ModelAndView("signin","success_register","Successful");
		}
		catch (Exception e)
		{
			return new ModelAndView("signup","fail_register","The username is already used,change it!");
		}
	}

	/**
	 * Update User profile
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateSelfInfo", method = RequestMethod.POST)
	public ModelAndView updateSelfInfo(HttpServletRequest request,HttpServletResponse response) {
		User modeluser= (User) request.getSession().getAttribute("SYSUSER");
		if(modeluser == null) return new ModelAndView("login");
		modeluser.setUemail(request.getParameter("email"));
		modeluser.setUcity(request.getParameter("city"));
		modeluser.setUname(request.getParameter("uname"));
		String password = md5.convertMD5(request.getParameter("password"));
		modeluser.setUpassword(password);
		ModelMap mmap = new ModelMap();
		try
		{
			userService.updateUser(modeluser);
			mmap.addAttribute("success_update", "Successful");
			return new ModelAndView("redirect:/user/user_info_trans.do",mmap);
		}
		catch(Exception e)
		{
			mmap.addAttribute("fail_update", "Fail,please contact the manager!");
			return new ModelAndView("redirect:/user/user_info_trans.do",mmap);
		}
	}

	/**
	 * transfer to search
	 */
	@RequestMapping(value = "/search", method = {RequestMethod.POST})
	public ModelAndView search(HttpServletRequest request,Map<String,Object> model){
		User user= (User) request.getSession().getAttribute("SYSUSER");
		if(user == null) return new ModelAndView("signin");
		model.put("username", user.getUsername());
        model.put("uid", user.getUid());
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		if (type.equals("track")){
			List<Track> tracklist = userService.searchTrack(keyword);
			model.put("tracklist",tracklist);
			return new ModelAndView("search_track");
		}
		else if (type.equals("artist")) {
			List<Artist> artistlist = userService.searchArtist(keyword);
			model.put("artistlist", artistlist);
			return new ModelAndView("search_artist");
		}
		else if (type.equals("album")){
			List<Album> albumlist = userService.searchAlbum(keyword);
			model.put("albumlist",albumlist);
			return new ModelAndView("search_album");

		}
		else if (type.equals("playlist")){
			List<Playlist> playlist = userService.searchPlaylist(keyword);
			model.put("playlist",playlist);
			return new ModelAndView("search_playlist");

		}
		else {
			List<User> userlist = userService.searchUser(keyword);
			model.put("userlist", userlist);
		}
		return new ModelAndView("search_user");

	}

	/**
	 * transfer to profile
	 */
	@RequestMapping(value = "/profile_trans", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView profile_trans(HttpServletRequest request,HttpServletResponse response, Map<String,Object> model,String uid) {
		User user= (User) request.getSession().getAttribute("SYSUSER");
		if(user == null) return new ModelAndView("signin");
		model.put("username", user.getUsername());
        model.put("uid", user.getUid());
		Long id = Long.parseLong(uid);
		List<User> followinglist;
		List<User> followerlist;
		List<Playlist> playlists;
		if(user.getUid() == id)
		{
			model.put("isOther", 0);
			model.put("uname",user.getUname());
			model.put("city",user.getUcity());
			model.put("otheruid",user.getUid());
			followinglist = userService.displayallFollowing(user);
			followerlist = userService.displayallFollower(user);
			playlists = playlistService.displayMyPlaylist(user);
		}
		else
		{
			model.put("isOther", 1);
			User modeluser = userService.getUserbyId(id);
			model.put("uname",modeluser.getUname());
			model.put("city",modeluser.getUcity());
			model.put("otheruid",modeluser.getUid());
			followinglist = userService.displayallFollowing(modeluser);
			followerlist = userService.displayallFollower(modeluser);
			playlists = playlistService.displayFollowingPlaylist(modeluser);
			if(userService.checkFollow(user,modeluser)){
				model.put("isFollowing", 1);
			}
			else
			{
				model.put("isFollowing", 0);
			}
		}
		model.put("following_number",followinglist.size());
		model.put("follower_number", followerlist.size());
		model.put("playlist_number", playlists.size());
		return new ModelAndView("profile");
	}

	/**
	 * change follow status
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/change_follow_status", method = RequestMethod.POST)
	public ModelAndView change_follow_status(HttpServletRequest request,HttpServletResponse response,  Map<String,Object> model, String otheruid) {
		User user= (User) request.getSession().getAttribute("SYSUSER");
		if(user == null) return new ModelAndView("signin");
		model.put("username", user.getUsername());
        model.put("uid", user.getUid());
		Long id = Long.parseLong(otheruid);
		User otheruser = userService.getUserbyId(id);
		if(userService.checkFollow(user,otheruser)){
			userService.Unfollow(user,otheruser);
		}
		else
		{
			Follow f = new Follow();
			f.setUser(user);
			f.setFollowing(otheruser);
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
			String timestamp = dateFormat.format( now );
			f.setTimestamp(timestamp);
			userService.Follow(f);
		}
		model.put("uid",otheruid);
		return new ModelAndView("redirect:/user/profile_trans.do");
	}

	/**
	 * display all following
	 */
	@RequestMapping(value = "/display_following", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView display_following(HttpServletRequest request,HttpServletResponse response,  Map<String,Object> model, String uid) {
		User user= (User) request.getSession().getAttribute("SYSUSER");
		if(user == null) return new ModelAndView("signin");
		model.put("username", user.getUsername());
        model.put("uid", user.getUid());
		Long id = Long.parseLong(uid);
		List<User> followinglist;
		List<User> followerlist;
		List<Playlist> playlists;
		if(user.getUid() == id)
		{
			model.put("isOther", 0);
			model.put("uname",user.getUname());
			model.put("city",user.getUcity());
			model.put("otheruid",user.getUid());
			followinglist = userService.displayallFollowing(user);
			followerlist = userService.displayallFollower(user);
			playlists = playlistService.displayMyPlaylist(user);
		}
		else
		{
			model.put("isOther", 1);
			User modeluser = userService.getUserbyId(id);
			model.put("uname",modeluser.getUname());
			model.put("city",modeluser.getUcity());
			model.put("otheruid",modeluser.getUid());
			followinglist = userService.displayallFollowing(modeluser);
			followerlist = userService.displayallFollower(modeluser);
			playlists = playlistService.displayFollowingPlaylist(modeluser);
			if(userService.checkFollow(user,modeluser)){
				model.put("isFollowing", 1);
			}
			else
			{
				model.put("isFollowing", 0);
			}
		}
		model.put("followinglist",followinglist);
		model.put("followerlist", followerlist);
		model.put("following_number",followinglist.size());
		model.put("follower_number", followerlist.size());
		model.put("playlist_number", playlists.size());
		return new ModelAndView("profile_following");
	}

	/**
	 * display all follower
	 */
	@RequestMapping(value = "/display_follower", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView display_follower(HttpServletRequest request,HttpServletResponse response,  Map<String,Object> model, String uid) {
		User user= (User) request.getSession().getAttribute("SYSUSER");
		if(user == null) return new ModelAndView("signin");
		model.put("username", user.getUsername());
        model.put("uid", user.getUid());
		Long id = Long.parseLong(uid);
		List<User> followinglist;
		List<User> followerlist;
		List<Playlist> playlists;
		if(user.getUid() == id)
		{
			model.put("isOther", 0);
			model.put("uname",user.getUname());
			model.put("city",user.getUcity());
			model.put("otheruid",user.getUid());
			followinglist = userService.displayallFollowing(user);
			followerlist = userService.displayallFollower(user);
			playlists = playlistService.displayMyPlaylist(user);
		}
		else
		{
			model.put("isOther", 1);
			User modeluser = userService.getUserbyId(id);
			model.put("uname",modeluser.getUname());
			model.put("city",modeluser.getUcity());
			model.put("otheruid",modeluser.getUid());
			followinglist = userService.displayallFollowing(modeluser);
			followerlist = userService.displayallFollower(modeluser);
			playlists = playlistService.displayFollowingPlaylist(modeluser);
			if(userService.checkFollow(user,modeluser)){
				model.put("isFollowing", 1);
			}
			else
			{
				model.put("isFollowing", 0);
			}
		}
		model.put("followinglist",followinglist);
		model.put("followerlist", followerlist);
		model.put("following_number",followinglist.size());
		model.put("follower_number", followerlist.size());
		model.put("playlist_number", playlists.size());
		return new ModelAndView("profile_follower");
	}


	/**
	 * display playlist
	 */
	@RequestMapping(value = "/display_playlist", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView display_playlist(HttpServletRequest request,HttpServletResponse response,  Map<String,Object> model, String uid) {
		User user= (User) request.getSession().getAttribute("SYSUSER");
		if(user == null) return new ModelAndView("signin");
		model.put("username", user.getUsername());
        model.put("uid", user.getUid());
		Long id = Long.parseLong(uid);
		List<Playlist> playlist;
		List<User> followinglist;
		List<User> followerlist;
		if(user.getUid() == id)
		{
			model.put("isOther", 0);
			model.put("uname",user.getUname());
			model.put("city",user.getUcity());
			model.put("otheruid",user.getUid());
			playlist = playlistService.displayMyPlaylist(user);
			followinglist = userService.displayallFollowing(user);
			followerlist = userService.displayallFollower(user);
		}
		else
		{
			model.put("isOther", 1);
			User modeluser = userService.getUserbyId(id);
			model.put("uname",modeluser.getUname());
			model.put("city",modeluser.getUcity());
			model.put("otheruid",modeluser.getUid());
			playlist = playlistService.displayFollowingPlaylist(modeluser);
			followinglist = userService.displayallFollowing(modeluser);
			followerlist = userService.displayallFollower(modeluser);
			if(userService.checkFollow(user,modeluser)){
				model.put("isFollowing", 1);
			}
			else
			{
				model.put("isFollowing", 0);
			}
		}
		model.put("playlists", playlist);
		model.put("following_number",followinglist.size());
		model.put("follower_number", followerlist.size());
		model.put("playlist_number", playlist.size());
		return new ModelAndView("profile_playlists");
	}


	/**
	 * change like status
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/like", method = RequestMethod.POST)
	public ModelAndView like(HttpServletRequest request,HttpServletResponse response,  Map<String,Object> model, String aid) {
		User user= (User) request.getSession().getAttribute("SYSUSER");
		if(user == null) return new ModelAndView("signin");
		model.put("username", user.getUsername());
        model.put("uid", user.getUid());
		Artist artist = userService.displayArtist(aid);
		if(userService.checkFavorite(user,artist))
		{
			userService.unFavoriteArtist(user,artist);
		}
		else
		{
			Favorite favorite = new Favorite();
			favorite.setUser(user);
			favorite.setArtist(artist);
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
			String timestamp = dateFormat.format( now );
			favorite.setTimestamp(timestamp);
			userService.FavoriteArtist(favorite);
		}
		model.put("aid",aid);
		return new ModelAndView("redirect:/user/artist_trans.do");
	}


	/**
	 * transfer to search genre
	 */
	@RequestMapping(value = "/search_genre", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView search_genre(HttpServletRequest request,Map<String,Object> model, String type){
		User user= (User) request.getSession().getAttribute("SYSUSER");
		if(user == null) return new ModelAndView("signin");
		model.put("username", user.getUsername());
        model.put("uid", user.getUid());
		System.out.println(type);
		if (type.equals("Pop")){
			List<Track> tracklist = trackService.searchGenre("Pop");
			model.put("tracks",tracklist);
			model.put("type","Pop");
			return new ModelAndView("genre");
		}
		else if (type.equals("Jazz")) {
			List<Track> tracklist = trackService.searchGenre("Jazz");
			model.put("tracks",tracklist);
			model.put("type","Jazz");
			return new ModelAndView("genre");
		}
		else if (type.equals("Rock")){
			List<Track> tracklist = trackService.searchGenre("Rock");
			model.put("tracks",tracklist);
			model.put("type","Rock");
			return new ModelAndView("genre");

		}
		else {
			List<Track> tracklist = trackService.getTrackbyTtitle("");
			model.put("tracks",tracklist);
			model.put("type","All");
			return new ModelAndView("genre");

		}
	}
}
