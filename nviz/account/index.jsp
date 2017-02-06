<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%> 
<div style="margin-left: 140px;">
<ul>
	<li>
		<div class="left-content-account">
			<h1 style="font-size:18px;"><span>Account Overview</span></h1>
		</div>
		<div style="width: 400px;padding-top: 8px;">
		<c:if test="${not empty user}">
			<h3>${user.firstName} ${user.lastName}</h3>
			<p>
				${user.employeeId}
			</p>
		</c:if>
		<br/>
		<a href="javascript:loadCreateSurveyForm();" class="more">Add address</a>
		</div>
	</li>
	<li>
		<div class="left-content-account">
			<h1 style="font-size:18px;"><span>Survey Overview</span></h1>
		</div>
		<c:forEach var="survey" items="${surveyItems}" varStatus="status">
			<div style="width: 400px;padding-top: 8px;">
				<h3 style="float:left">${survey.title}</h3> 
				<a style="float:right;"href="javascript:viewReport('${survey.surveyId}','${user.id}');" class="more">View report</a>
			</div>
			<br/><br/><br/><div><p>
				${survey.description}
			</p></div>
			<a id="dlink-${survey.surveyId}" href="" style="display:none;" download></a>
		</c:forEach>
		<!-- <a href="javascript:loadCreateUserForm();" class="more">Create User</a> -->
	</li>
</ul>
</div>