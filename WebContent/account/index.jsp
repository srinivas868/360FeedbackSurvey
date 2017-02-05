<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%> 
<%@ include file="/common/static-content.jsp" %>
<div style="margin-left: 140px;">
<ul>
	<li>
		<div class="left-content-admin">
			<h1><span>Account Overview</span></h1>
		</div>
		<div class="right-content-admin">
		<c:forEach var="user" items="${userItems}" varStatus="status">
			<h3>${user.firstName} ${user.lastName}</h3>
			<p>
				${user.description}
			</p>
		</c:forEach>
		<a href="javascript:loadCreateSurveyForm();" class="more">Create Survey</a>
		</div>
	</li>
	<li>
		<div class="left-content-admin">
			<h1><span>Survey Overview</span></h1>
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