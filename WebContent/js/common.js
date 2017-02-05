function loadSurvey(){
	$("#body").load( "/nviz/survey/index.jsp?type=Survey" );
	$('#navigation li').removeClass();
	$('#survey').attr('class','selected');
	window.setTimeout(function(){
		loadPagination();}, 700);
}
function loadAdminConsole(){
	$("#body").load( "/nviz/admin/index.jsp" );
}

function loadPagination() {
	var qualitiesCount = $('#qualitiesCount').val();
    $('.pagination-holder').pagination({
    	pages: qualitiesCount,
        cssStyle: 'dark-theme',
        onPageClick: function(pageNumber){loadQuality(pageNumber)}
    });
}

function loadQuality(index){
	$('.question-single-choice-radio').hide();
	$('#quality-field-'+index).show();
}

function reloadSurveyForm(){
	$('.pagination-holder.black').pagination('nextPage');
	var currentPage = $('.pagination-holder.black').pagination('getCurrentPage');
	var pagesCount = $('.pagination-holder.black').pagination('getPagesCount');
	if(currentPage == pagesCount){
		$('#survey-form-next').hide();
		$('#survey-form-submit').show();
	}
}

function submitSurveyForm(){
	var options = {
		success: loginResponse,
		dataType: 'json'
	};
	var form = $("#surveyForm");
	form.ajaxForm(options);
	form.submit();
	return false;
}
function loginResponse(data){
	if('success' == data.code){
		$("#body").load( "/nviz/survey/confirmation.jsp" );
	}
}
function loadCreateUserForm(){
	$("#body").load( "/nviz/admin/create-user.jsp" );
}
function createUser(){
	var options = {
		success: userFormResponse,
		dataType: 'json'
	};
	var form = $("#createUserForm");
	form.ajaxForm(options);
	form.submit();
	return false;
}
function userFormResponse(data){
	if('success' == data.code){
		$("#body").load( "/nviz/admin/confirmation.jsp" );
	}
}