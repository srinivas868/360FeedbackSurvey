<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%> 
<%@ include file="/common/static-content.jsp" %>
<div class="article">
	<input type="hidden" id="loginPage" name="loginPage" value="true">
	<form id="loginForm" name="loginForm" method="POST" action="login" style="width:300px;">
	<div class="input-div"><p>Login </p><input type="text" name="login" class="input-textbox" value=""><br/></div>
	<div class="input-div"><p>Password </p><input type="password" name="firstName" class="input-textbox" value=""><br/></div>
	<div align="center"><button id="survey-form-submit" type="submit" class="button" onclick="return login();">
           Submit
    </button></div>
</div>
</html>