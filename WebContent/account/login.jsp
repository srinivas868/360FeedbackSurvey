<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Login</title>
<c:set var="reqParam">
${param.error}</c:set>
</head>
<body>
	<div id="wrap">
		<div id="topbar">
			<jsp:include page="/snips/header.jsp" />
			<div id="content">
				<div id="mainpage">
					<div class="form">
						<div class="LoginError" align="center">
							<c:if test="${param.error eq 'true'}">
								Invalid Credentials
							</c:if>
						</div>
						<form method="post" id="loginForm" onsubmit="logIn();return false">	
							<h2 style="text-align: center;">
								Login
							</h2>
							<br> <label for="login">UserName
								<input name="username" type="text"
									id="login" tabindex="1" />
							</label>
							<span></span>
							<label for="password">Password</label> 
							<input name="password" type="password" id="password" tabindex="2" /> 
							<input type="submit" tabindex="3" value="Login" id="gobutton" name="requestType" title="Login" />
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/snips/footer.jsp" />
<!-- <script src="js/script.js"></script> -->
<script>
function logIn(){
	var username=document.getElementById("login").value;
	var password=document.getElementById("password").value;
    var userEmailDetails = new InputCustomerDetailItem(username, password);
    var jsonProfileRequest = JSON.stringify(userEmailDetails);
    
    var validateEmailURL = '/nviz/rest/user/login';
    if(username!=null && password!=null && username != "" && password != "")
	{
    $.ajax({
        url : validateEmailURL,
        type : 'GET',
        data: jsonProfileRequest,
        async : false,
        datatype : "application/json",
        contentType: "application/json; charset=utf-8",
        success : function(data) {
        	var msg = data.message;
        	if(data.status == "OK"){
                window.location.href='/nviz/index.jsp';
        	}else{
                window.location.href='/nviz/login.jsp?error=true';
                $('div.LoginError').text(msg).css('color','red');
        	}
        }
        
    });
	}
    else if(username == "" && password == "")
	{
		$('div.LoginError').text("Please Provide User Credentials").css('color','red');
	}
	else if(username == "" || password == "")
	{
		if(username == "")
		{
			$('div.LoginError').text("Please enter UserName").css('color','red');
		}
		else if(password == "")
		{
			$('div.LoginError').text("Please enter a Password").css('color','red');
		}			
	}
}


function InputCustomerDetailItem(username, password) {
    this.username = username;
    this.password = password;
}

</script>
</body>
</html>