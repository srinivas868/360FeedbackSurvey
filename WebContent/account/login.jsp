<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%> 
<%@ include file="/common/static-content.jsp" %>
<div class="article">
	<input type="hidden" id="loginPage" name="loginPage" value="true">
	<form id="loginForm" name="loginForm" method="POST" action="login" style="width:300px;padding-bottom:50px;">
	<div align="center" class="error-message"><p></p></div>
	<div class="input-div"><p>Login </p><br/><br/><input type="text" name="login" class="input-login" value=""><br/></div>
	<div class="input-div"><p>Password </p><br/><input type="password" name="password" class="input-login" value=""><br/></div>
	<button id="survey-form-submit" type="submit" class="button" onclick="return loginUser();" style="width:258px;">
           Submit
    </button>
    </form>
</div>
</html>