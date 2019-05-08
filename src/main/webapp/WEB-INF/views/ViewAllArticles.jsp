<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Meta, title, CSS, favicons, etc. -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>View Article</title>

<link href="css/datatable/jquery.dataTables.min.css" rel="stylesheet" />
<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet" />
<link href="css/bootstrap-fileinput-npm/fileinput.min.css"
	rel="stylesheet" />
<link href="css/bootstrap/animate.min.css" rel="stylesheet" />
<link href="css/fonts/css/font-awesome.min.css" rel="stylesheet" />
<link href="css/custom/custom.css" rel="stylesheet" />

</head>

<body class="nav-sm">

	<div class="container body">
		<div class="main_container">

			<!-- sidemenu content -->
			<div class="col-md-3 left_col">
				<div class="left_col">

					<div class="navbar nav_title" style="border: 0;">
						<a class="site_title" href="index.html"> <span>Empty!</span>
						</a>
					</div>
					<div class="clearfix"></div>

					<!-- sidebar menu -->
					<div id="sidebar-menu"
						class="main_menu_side hidden-print main_menu">

						<div class="menu_section">

							<ul class="nav side-menu">
								<li><a href="${pageContext.request.contextPath}/home">
										<i class="fa fa-home"></i> Home <!--  <span
										class="fa fa-chevron-down"></span>
										-->
								</a> <!--  
									<ul class="nav child_menu" style="display: none">

										<li><a href="empty.html">Add</a></li>
										<li><a href="empty.html">Edit</a></li>


									</ul>
									--></li>

								<!-- fa-home from font.min.css -->
								<li><a href="#"><i class="fa fa-product-hunt"></i>
										Product <!-- 
								<span
										class="fa fa-chevron-down"></span>
										--> </a> <!-- 
									<ul class="nav child_menu" style="display: none">
									 --> <!-- 
										<li><a href="empty.html">Add</a></li>
										<li><a href="empty.html">Edit</a></li>
										
										 
									</ul>
									--></li>

								<li><a href="${pageContext.request.contextPath}/article">
										<i class="fa fa-newspaper-o"></i> Article <span
										class="fa fa-chevron-down"></span>
								</a> <!--
									<ul class="nav child_menu" style="display: none"> 
										<li><a href="empty.html">Add</a></li>
										<li><a href="empty.html">Edit</a></li>													 
									</ul>
									--></li>

								<li><a href="#"><i class="fa fa-edit"></i> Promotion <span
										class="fa fa-chevron-down"></span></a> <!-- 
									<ul class="nav child_menu" style="display: none">							
										<li><a href="empty.html">Add</a></li>
										<li><a href="empty.html">Edit</a></li>				
										
									</ul>
									 --></li>
							</ul>
						</div>
					</div>
					<!-- /sidebar menu -->

					<!-- /menu footer buttons -->
					<!--
					<div class="sidebar-footer hidden-small"
						style="border: 3px solid purple;">
						<a data-toggle="tooltip" data-placement="top" title="Settings">
							<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
						</a> <a data-toggle="tooltip" data-placement="top" title="FullScreen">
							<span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
						</a> <a data-toggle="tooltip" data-placement="top" title="Lock"> <span
							class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
						</a> <a data-toggle="tooltip" data-placement="top" title="Logout">
							<span class="glyphicon glyphicon-off" aria-hidden="true"></span>
						</a>
					</div>
					end /menu footer buttons -->


				</div>
			</div>
			<!-- sidemenu content  -->

			<!-- top navigation -->
			<div class="top_nav">
				<div class="nav_menu">
					<nav class="" role="navigation">
					<div class="nav toggle">
						<a id="menu_toggle"><i class="fa fa-bars"></i></a>
					</div>
					<div class="nav toggle pull-right">

						<a id="menu_toggle"
							href="${pageContext.request.contextPath}/logout"> <i
							class="fa fa-sign-out fa-lg"></i></a>
					</div>
					</nav>
				</div>
			</div>
			<!-- /top navigation -->


			<!-- page content -->
			<div class="right_col">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<h2>
						<b>View Article</b>
					</h2>
					<hr>


					<div id="container"></div>

				</div>
				<!--  end of <div class="col-md-12 col-sm-12 col-xs-12"> -->
				<br />
			</div>
			<!-- /page content -->

			<!-- footer content -->
			<footer>
			<div class="pull-right">
				<h6>
					Gentelella - Bootstrap Admin Template by <a
						href="https://colorlib.com">Colorlib</a>
				</h6>
			</div>
			<div class="clearfix"></div>
			</footer>
			<!-- /footer content -->
		</div>
	</div>

	<div id="custom_notifications" class="custom-notifications dsp_none">
		<ul class="list-unstyled notifications clearfix"
			data-tabbed_notifications="notif-group">
		</ul>
		<div class="clearfix"></div>
		<div id="notif-group" class="tabbed_notifications"></div>
	</div>
	<script src="js/jquery.js"></script>
	<script src="js/tinymce/tinymce.min.js"></script>
	<!-- <script src="js/bootstrap.min.js"></script> -->
	
	<!--  
	var $ = require('jquery');
    var React = require('react');
    var ReactDOM = require('react-dom');
    require('bootstrap');
	 -->
	<script src="js/dependenciesBundle.js"></script>
	<script src="js/ViewAllArticles/bundle.js"></script>
	<script type="text/javascript" src="js/custom.js" /></script>



</body>
</html>
