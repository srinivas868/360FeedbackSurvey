<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%> 
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>NO TITLE</title>
	<jsp:include page="/common/static-content.jsp"/>
</head>
<body>
	<div id="header">
		<a href="index.html" class="logo">
			<img src="images/icons/nviz_logo.jpg" alt="">
		</a>
		<c:if test="${not empty user}">
			<ul id="navigation">
				<li id="home" class="selected">
					<a href="javascript:loadHome();">Home</a>
				</li>
				<li id="about-us">
					<a href="javascript:loadAboutUs();">About Us</a>
				</li>
				<li id="my-account">
					<a href="javascript:loadMyAccount();">My Account</a>
				</li>
				<li id="survey">
					<a href="javascript:loadSurvey();">Survey</a>
				</li>
				<li id="admin">
					<a href="javascript:loadAdminConsole();">Admin Console</a>
				</li>
			</ul>
			<ul class="drop-down">
				<li id="logout" >
					<a href="javascript:logout();">Logout</a>
				</li>
			</ul>
			<form id="logoutForm" name="logoutForm" method="POST" action="logout" style="display:none;">
			</form>
		</c:if>
	</div>
</html>
