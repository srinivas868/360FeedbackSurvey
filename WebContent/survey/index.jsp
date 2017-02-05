<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%> 
<%@ include file="/common/static-content.jsp" %>
<div class="article">
	<c:set var="survey" value="${surveyItems[0]}" />
	<form id="surveyForm" name="surveyForm" method="POST" action="surveyFormSubmit">
		<input type="hidden" id="qualitiesCount" value="${survey.qualitiesCount}" />
		<input type="hidden" name="surveyId" id="surveyId" value="${survey.surveyId}" />
		<div align="center" class="pagination-holder black clearfix">
		</div>
		<c:forEach var="quality" items="${survey.qualities}" varStatus="qStatus">
			<c:choose>
				<c:when test="${qStatus.count ne '1'}">
					<c:set var="qualityStyle" value="display:none;" />
				</c:when>
				<c:otherwise>
					<c:set var="qualityStyle" value="" />
				</c:otherwise>
			</c:choose>
	        <div id="quality-field-${qStatus.count}" class="question-single-choice-radio qn question vertical" align="center" style="${qualityStyle}">
	            <fieldset class=" question-fieldset" style="text-align:center;margin-left:-65px;">
	                <h4 id="question-title-${status.count}" class="question-title-container">
                       <span class="question-number">
                           ${status.count}<span class="question-dot">.</span>
                       </span>
                       <span class="user-generated notranslate">${quality.description}</span>
	                </h4>
	               	<table width="700" border="0" cellspacing="0" cellpadding="0" class="skillTable">
	               	  <thead>
	               	  	<tr>
	               	  		<th>Employee</th>
	               	  		<c:forEach var="rating" items="${ratingItems}" varStatus="rStatus">
					        		<th width="95"><strong>
					        			${rating.title}
					        		</strong></th>
					        	</c:forEach>
	               	  	</tr>
	               	  	<tr><td></td></tr>
	               	  </thead>
					  <tbody id="skillTb_id">
			        	<c:forEach var="user" items="${userItems}" varStatus="uStatus">
		        		<tr>
			        		<div class="question-body clearfix notranslate ">
					        <div class="answer-option-cell" style="display: flex;">
					        	<td width="84"><strong>
					        		${user.firstName}
					        	</strong></td>
					        	<c:forEach var="rating" items="${ratingItems}" varStatus="rStatus">
					        		<td width="95"><strong>
					        			<input name="${quality.qualityId}-${user.id}-rating" type="radio" value="${rating.input}" class=""/>
					        		</strong></td>
					        	</c:forEach>
				        	</div>
				        	</div>
			        	</tr>
				        </c:forEach>
					 </tbody>
					</table>
	             </fieldset>
                </div>
		</c:forEach>
        <div align="center"><button id="survey-form-next" type="submit" class="button" onclick="reloadSurveyForm();return false;">
           Next
        </button>
        <button id="survey-form-submit" type="submit" style="display:none;" class="button" onclick="return submitSurveyForm();">
           Submit
        </button></div>
       </div>
	</form>
</div>
</html>
