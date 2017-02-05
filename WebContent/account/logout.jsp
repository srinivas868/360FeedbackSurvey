<%@page import="org.json.JSONObject"%>
<%@page import="com.nviz.vo.UserDAO" %>
<%@page import="javax.servlet.http.HttpServletRequest" %>
<%!	JSONObject json = new JSONObject();
   UserDAO userDAO=new UserDAO();
	  %>
<%
     String userName=(String)session.getAttribute("username");
	if (!session.isNew()) {
		session.invalidate();
		response.sendRedirect("/Nviz/login.jsp");
	} else {
		json.put("STATUS", "FAILED");
		json.put("StatusDescription", "Process failed");
		json.put("Error message", "Process failed");
		out.println(json);
		response.sendRedirect("/Nviz/index.jsp");
	}
%>