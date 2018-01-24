<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>Music - Index</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <link rel="stylesheet" href="../js/jPlayer/jplayer.flat.css" type="text/css" />
  <link rel="stylesheet" href="../css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="../css/animate.css" type="text/css" />
  <link rel="stylesheet" href="../css/font-awesome.min.css" type="text/css" />
  <link rel="stylesheet" href="../css/simple-line-icons.css" type="text/css" />
  <link rel="stylesheet" href="../css/font.css" type="text/css" />
  <link rel="stylesheet" href="../css/app.css" type="text/css" />
</head>
<body class="">
  <section class="vbox">
    <header class="bg-white-only header header-md navbar navbar-fixed-top-xs">
      <div class="navbar-header aside bg-info nav-xs">
        <a class="btn btn-link visible-xs" data-toggle="class:nav-off-screen,open" data-target="#nav,html">
          <i class="icon-list"></i>
        </a>
        <a href="<%=basePath%>user/index_trans.do" class="navbar-brand text-lt">
          <i class="icon-earphones"></i>
          <span class="hidden-nav-xs m-l-sm">Music</span>
        </a>
        <a class="btn btn-link visible-xs" data-toggle="dropdown" data-target=".user">
          <i class="icon-settings"></i>
        </a>
      </div>
      <ul class="nav navbar-nav hidden-xs">
        <li>
          <a href="#nav,.navbar-header" data-toggle="class:nav-xs,nav-xs" class="text-muted">
            <i class="fa fa-indent text"></i>
            <i class="fa fa-dedent text-active"></i>
          </a>
        </li>
      </ul>
      <form class="navbar-form navbar-left input-s-lg m-t m-l-n-xs hidden-xs" role="search" action="<%=basePath%>user/search.do" method="POST" style="width: 600px; height:20px;">
        <div class="form-group">
          <div class="input-group">
            <span class="input-group-btn">
              <button type="submit" class="btn btn-sm bg-white btn-icon rounded"><i class="fa fa-search"></i></button>
            </span>
            <input name="keyword" type="text" class="form-control input-sm no-border rounded" placeholder="Search songs, albums..." style="display: inline; width: 200px;">
            <select name="type" class="form-control m-b" style="display: inline; width:200px;">
              <option>track</option>
              <option>album</option>
              <option>artist</option>
              <option>playlist</option>
              <option>user</option>
            </select>
          </div>
        </div>
      </form>
      <div class="navbar-right">
        <ul class="nav navbar-nav m-n hidden-xs nav-user user">
          <li class="dropdown">
            <a href="#" class="dropdown-toggle bg clear" data-toggle="dropdown">
              ${username}
              <b class="caret"></b>
            </a>
            <ul class="dropdown-menu animated fadeInDown">            
              <li>
                <a href="<%=basePath%>user/user_info_trans.do">Settings</a>
              </li>
              <li>
                <a href="<%=basePath%>user/profile_trans.do?uid=${uid}">Profile</a>
              </li>
              <li class="divider"></li>
              <li>
                <a href="<%=basePath%>user/log_out.do" >Logout</a>
              </li>
            </ul>
          </li>
        </ul>
      </div>      
    </header>
    <section>
      <section class="hbox stretch">
        <!-- .aside -->
        <aside class="bg-black dk nav-xs aside hidden-print" id="nav">          
          <section class="vbox">
            <section class="w-f-md scrollable">
              <div class="slim-scroll" data-height="auto" data-disable-fade-out="true" data-distance="0" data-size="10px" data-railOpacity="0.2">
                <!-- nav -->                 
                <nav class="nav-primary hidden-xs">
                  <ul class="nav bg clearfix">
                    <li class="hidden-nav-xs padder m-t m-b-sm text-xs text-muted">
                      Discover
                    </li>
                    <li>
                      <a href="<%=basePath%>user/index_trans.do">
                        <i class="icon-disc icon text-success"></i>
                        <span class="font-bold">What's new</span>
                      </a>
                    </li>
                    <li>
                      <a href="<%=basePath%>user/search_genre.do?type=All">
                        <i class="icon text-info fa fa-tags"></i>
                        <span class="font-bold">Genres</span>
                      </a>
                    </li>
                    <li>
                      <a href="<%=basePath%>playlist/playlist_trans.do">
                        <i class="text-warning icon-list icon icon-music-tone-alt"></i>
                        <span class="font-bold">Playlists</span>
                      </a>
                    </li>
                    <li>
                      <a href="<%=basePath%>user/history.do">
                        <i class="text-danger icon icon-music-tone-alt"></i>
                        <span class="font-bold">History</span>
                      </a>
                    </li>
                  </ul>
                </nav>
                <!-- / nav -->
              </div>
            </section>
          </section>
        </aside>
        <!-- /.aside -->
        <section id="content">
          <section class="hbox stretch">
            <section>
              <section class="vbox">
                <section class="scrollable padder-lg w-f-md" id="bjax-target">
                  <div class="row">
                    <div class="col-md-9">
                      <h3 class="font-bold">What's New</h3>
                      <div class="row row-sm">
                          <c:forEach var="t" items="${randomtracklist}" varStatus="loop">
                            <div class="col-xs-6 col-sm-3">
                                <div class="item">
                                    <div class="pos-rlt">
                                        <div class="item-overlay opacity r r-2x bg-black">
                                            <div class="center text-center m-t-n">
                                                <a href="<%=basePath%>track/track_trans.do?tid=${t.tid}"><i class="fa fa-play-circle i-2x"></i></a>
                                            </div>
                                        </div>
                                        <a href="<%=basePath%>track/track_trans.do?tid=${t.tid}"><img src="../images/a2.png" alt="" class="r r-2x img-full"></a>
                                    </div>
                                    <div class="padder-v">
                                        <a href="<%=basePath%>track/track_trans.do?tid=${t.tid}" class="text-ellipsis">${t.ttitle}</a>
                                        <a href="<%=basePath%>user/artist_trans.do?aid=${t.artist.aid}" class="text-ellipsis text-xs text-muted">${t.artist.aname}</a>
                                    </div>
                                </div>
                            </div>
                          </c:forEach>
                    </div>
                    </div>
                    <div class="col-md-3">
                      <h3 class="font-bold">Continue Playing</h3>
                      <div class="list-group bg-white list-group-lg no-bg auto">
                        <c:forEach var="t" items="${recenttracklist}" varStatus="loop">
                        <a href="<%=basePath%>track/track_trans.do?tid=${t.tid}" class="list-group-item clearfix">
                          <span class="pull-right h2 text-muted m-l">${loop.index + 1}</span>
                          <span class="pull-left thumb-sm avatar m-r">
                            <img src="../images/a4.png" alt="...">
                          </span>
                          <span class="clear">
                            <span>${t.ttitle}</span>
                            <small class="text-muted clear text-ellipsis">by ${t.artist.aname}</small>
                          </span>
                        </a>
                        </c:forEach>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-9">
                        <h3 class="font-bold">Activity</h3>
                        <table class="table table-striped m-b-none">
                        <thead>
                          <tr>
                            <th>#</th>
                            <th>Event</th>
                            <th>Time</th>                   
                          </tr>
                        </thead>
                        <tbody>
                          <c:forEach var="p" items="${randomlatestplaylist}" varStatus="loop">
                              <tr>
                                  <td>${loop.index + 1}</td>
                                  <td>
                                      <span>
                                        <a href="<%=basePath%>user/profile_trans.do?uid=${p.user.uid}">${p.user.uname}</a>
                                      </span>
                                      has created a new playlist:
                                      <span>
                                        <a href="<%=basePath%>playlist/one_playlist_trans.do?id=${p.pid}">${p.ptitle}</a>
                                      </span>
                                  </td>
                                  <td>${p.pdate}</td>
                              </tr>
                          </c:forEach>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </section>
              </section>
            </section>
          </section>
          <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen,open" data-target="#nav,html"></a>
        </section>
      </section>
    </section>    
  </section>
  <script src="../js/jquery.min.js"></script>
  <!-- Bootstrap -->
  <script src="../js/bootstrap.js"></script>
  <!-- App -->
  <script src="../js/app.js"></script>
  <script src="../js/slimscroll/jquery.slimscroll.min.js"></script>
  <script src="../js/app.plugin.js"></script>
</body>
</html>