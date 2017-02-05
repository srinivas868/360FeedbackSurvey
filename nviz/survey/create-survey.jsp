<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%> 
<%@ include file="/common/static-content.jsp" %>
<form id="createSurveyForm" name="createSurveyForm" method="POST" action="createUser" style="width:300px;">
	<div class="input-div"><p>Title </p><input type="text" name="title" class="input-textbox" value=""><br/></div>
	<div class="input-div"><p>Description </p><textarea form ="createSurveyForm" name="description" cols="35" wrap="soft"></textarea><br/></div>
	<div class="input-div"><p>Password </p><input type="text" name="firstName" class="input-textbox" value=""><br/></div>
	<div class="input-div"><p>Last name </p><input type="text" name="lastName" class="input-textbox" value=""><br/></div>
	<div class="input-div"><p>Employee ID </p><input type="text" name="employeeId" class="input-textbox" value=""><br/></div>
	<div align="center"><button id="survey-form-submit" type="submit" class="button" onclick="return createUser();">
           Submit
    </button></div>
</form>
