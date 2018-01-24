<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>Music - Track</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <link rel="stylesheet" href="../js/jPlayer/jplayer.flat.css" type="text/css" />
  <link rel="stylesheet" href="../css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="../css/animate.css" type="text/css" />
  <link rel="stylesheet" href="../css/font-awesome.min.css" type="text/css" />
  <link rel="stylesheet" href="../css/simple-line-icons.css" type="text/css" />
  <link rel="stylesheet" href="../css/font.css" type="text/css" />
  <link rel="stylesheet" href="../css/app.css" type="text/css" />
  <link rel="stylesheet" href="../css/fontawesome-stars.css">
  <style type="text/css">
    .br-wrapper.br-theme-fontawesome-stars {
      display: inline-block;
    }
  </style>
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
                  <h3 class="font-bold">${track.ttitle}</h3>
                  <h4 class="font-thin">by <a href="<%=basePath%>user/artist_trans.do?aid=${track.artist.aid}">${track.artist.aname}</a></h4>
                  <h5>Genre: <span>${track.tgenre}</span></h5>
                  <h5>Duration: <span>
                    <jsp:useBean id="dateObject" class="java.util.Date" />
                    <jsp:setProperty name="dateObject" property="time" value="${track.tduration}" />
                    <fmt:formatDate value="${dateObject}" pattern="mm:ss" />
                  </span></h5>
                  <h5>Rating: <select id="rating">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                  </select>
                  </h5>
                  <h5>
                    Choose Playlist:
                    <span>
                    <form action="<%=basePath%>track/add_track_to_playlist.do" method="POST" style="display: inline;">
                        <div class="form-group" style="display: inline;">
                          <select id="my_select" name="account" class="form-control m-b" style="width: 200px; margin: 0 20px; display: inline;">
                            <c:forEach var="p" items="${playlists}" varStatus="loop">
                              <c:choose>
                                <c:when test="${'0' eq loop.index}">
                                  <option selected id="${p.pid}" name="pid">${p.ptitle}</option>
                                </c:when>
                                <c:otherwise>
                                  <option id="${p.pid}" name="pid">${p.ptitle}</option>
                                </c:otherwise>
                              </c:choose>
                            </c:forEach>
                          </select>
                          <input name="tid" value="${track.tid}" type="text" style="display: none;">
                          <input name="pid" value="" type="text" id="out_pid" style="display: none;">
                          <button type="submit" class="btn btn-s-md btn-success"><i class="fa fa-plus"></i>  Add to My Playlist</button>
                        </div>
                      </form>
                    </span>
                  </h5>
                  <br/>
                  <br/>
                  <a id="play_button" class="btn btn-s-md btn-success"><i class="icon icon-control-play"></i>  Play</a >
                  <br/>
                  <br/>
                  <br/>
                  <iframe src=${'"https://open.spotify.com/embed?uri=spotify%3Atrack%3A'.concat(track.tid).concat('"')} width="320" height="400" frameborder="0" allowtransparency="true"></iframe>
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
  <script src="../js/jquery.barrating.min.js"></script>
  <script type="text/javascript">
      $(function() {
          // http://antenna.io/demo/jquery-bar-rating/examples/
          $('#rating').barrating({
              theme: 'fontawesome-stars',
              allowEmpty: true,
              showValues: true,
              emptyValue: '0',
              <c:choose>
                <c:when test="${score > 0}">
              readonly: true,
              initialRating: ${score},
                </c:when>
                <c:otherwise>
              readonly: false,
              initialRating: 0,
                </c:otherwise>
              </c:choose>
              onSelect: function(value, text, event) {
                  if (typeof(event) !== 'undefined') {
                      // rating was selected by a user
                      $.post( "<%=basePath%>track/rate.do",
                          { tid: "${track.tid}", score: value } );
                      // console.log(value);
                      // console.log(event.target);
                      $('#rating').barrating('readonly', true);
                  } else {
                      // rating was selected programmatically
                      // by calling `set` method
                  }
              }
          });
      });
      $("#my_select").change(function(){
         var pid = $(this).children(":selected").attr("id");
         $("#out_pid").val(pid);
      });
      var pid = $("#my_select").children(":selected").attr("id");
      $("#out_pid").val(pid);
      $('#play_button').click( function(e) {
          e.preventDefault();
          console.log("Play");
          $.post( "<%=basePath%>track/play.do",
              { tid: "${track.tid}"} );
          return false;
      });
  </script>

</body>
</html>