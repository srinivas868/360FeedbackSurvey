<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%> 
<div style="margin-left: 140px;">
<ul>
	<li>
		<div class="left-content-account">
			<h1 style="font-size:18px;"><span>Account Overview</span></h1>
		</div>
		<div class="right-content-account">
		user ${user}
		<c:if test="${not empty user}">
			<h3>${user.firstName} ${user.lastName}</h3>
			<p>
				${user.employeeId}
			</p>
		</c:if>
		<a href="javascript:loadCreateSurveyForm();" class="more">Add address</a>
		</div>
	</li>
	<li>
		<div class="left-content-account">
			<h1 style="font-size:18px;"><span>Survey Overview</span></h1>
		</div>
		<div class="right-content-account">
		<c:forEach var="survey" items="${surveyItems}" varStatus="status">
			<h3>${survey.title}</h3>
			<p>
				${survey.description}
			</p>
		</c:forEach>
		<!-- <a href="javascript:loadCreateUserForm();" class="more">Create User</a> -->
		</div>
	</li>
</ul>
</div>