<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%> 
<div style="margin-left: 140px;">
<ul>
	<li>
		<div class="left-content-admin">
			<h1><span>Survey</span></h1>
		</div>
		<div class="right-content-admin">
		<c:forEach var="survey" items="${surveyItems}" varStatus="status">
			<div style="width: 400px;padding-top: 8px;">
				<h3 style="float:left">${survey.title}</h3> <a style="float:right;"href="javascript:generateReport('${survey.surveyId}');" class="more">Generate report</a>
			</div>
			<%-- <h3>${survey.title}</h3> --%>
			<br/><br/><br/><div><p>
				${survey.description}
			</p></div>
		</c:forEach>
		<a href="javascript:loadCreateSurveyForm();" class="more">Create Survey</a>
		</div>
	</li>
	<li>
		<div class="left-content-admin">
			<h1><span>User</span></h1>
		</div>
		<div class="right-content-admin">
		<c:forEach var="user" items="${userItems}" varStatus="status">
			<h3>${user.firstName}</h3>
		</c:forEach>
		<a href="javascript:loadCreateUserForm();" class="more">Create User</a>
		</div>
	</li>
</ul>
</div>