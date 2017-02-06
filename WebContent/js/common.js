function loadSurvey(){
	$("#body").load( "/nviz/survey/index.jsp?type=Survey" );
	$('#navigation li').removeClass();
	$('#survey').attr('class','selected');
	window.setTimeout(function(){
		loadPagination();}, 700);
}
function loadAdminConsole(){
	$("#body").load( "/nviz/admin/index.jsp" );
	$('#navigation li').removeClass();
	$('#admin').attr('class','selected');
}
function loadMyAccount(){
	$("#body").load( "/nviz/account/index.jsp" );
	$('#navigation li').removeClass();
	$('#my-account').attr('class','selected');
}
function loadHome(){
	$("#body").load( "/nviz/snips/main-content.jsp" );
	$('#navigation li').removeClass();
	$('#home').attr('class','selected');
}
function logout(){
	var options = {
			success: logoutResponse,
			dataType: 'json'
		};
	var form = $("#logoutForm");
	form.ajaxForm(options);
	form.submit();
	return false;
}
function logoutResponse(data){
	if('success' == data.code){
		window.location.href='/nviz/index.jsp';
	}
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
		success: surveyResponse,
		dataType: 'json'
	};
	var form = $("#surveyForm");
	form.ajaxForm(options);
	form.submit();
	return false;
}
function surveyResponse(data){
	if('success' == data.code){
		$("#body").load( "/nviz/survey/confirmation.jsp" );
	}
}
function loadCreateUserForm(){
	$("#body").load( "/nviz/admin/create-user.jsp" );
}
function loadCreateSurveyForm(){
	$("#body").load( "/nviz/survey/create-survey.jsp" );
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
		$("#body").load( "/nviz/index.jsp" );
	}
}
function loginUser(){
	var options = {
		success: loginResponse,
		dataType: 'json'
	};
	var form = $("#loginForm");
	form.ajaxForm(options);
	form.submit();
	return false;
}
function loginResponse(data){
	if('success' == data.status){
		window.location.href='/nviz/index.jsp';
	} else{
		$('div.error-message p').empty();
		$('div.error-message p').append(data.message);
	}
}
function viewReport(surveyId,userId){
	var input = new InputCustomerDetailItem(surveyId, userId);
    var jsonInput = JSON.stringify(input);
	$.ajax({
        url : '/nviz/report/rest/reportmanager/view',
        type : 'GET',
        data: jsonInput,
        async : false,
        datatype : "application/json",
        contentType: "application/json; charset=utf-8",
        success : function(data) {
        	if(data.status == 'success'){
        		$('#dlink-'+surveyId).attr('href',data.recordPath);
        		document.getElementById('dlink-'+surveyId).click();
        	} else{
        		alert(data.message);
        	}
        }
	});
}
function generateReport(surveyId){
	var input = new InputCustomerDetailItem(surveyId,null);
	var jsonInput = JSON.stringify(input);
	$.ajax({
        url : '/nviz/report/rest/reportmanager/generate',
        type : 'GET',
        data: jsonInput,
        async : false,
        datatype : "application/json",
        contentType: "application/json; charset=utf-8",
        success : function(data) {
        	if(data.status != 'Success'){
        		
        	}
        }
	});
}
$(document).ready(function(){
	$('#my-account, ul.drop-down').hover(
		function(){
			$('.drop-down').show();
		},
		function(){
			$('.drop-down').hide();
		}
	);
});

$(document).ready(function(){
	$.ajax({
        url : '/nviz/account/rest/usermanager/validateLogin',
        type : 'GET',
        async : false,
        datatype : "application/json",
        contentType: "application/json; charset=utf-8",
        success : function(data) {
        	if(data.status != 'Success'){
        		console.log("User is not logged in");
        		var loginPage = $('#loginPage').val();
        		if(loginPage == undefined){
        			$("#body").load( "/nviz/account/login.jsp" );
        		}
        	}
        }
	});
});
function InputCustomerDetailItem(param1, param2) {
    this.param1 = param1;
    this.param2 = param2;
}