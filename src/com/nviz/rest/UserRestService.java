package com.nviz.rest;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.json.JSONException;
import org.json.JSONObject;

import com.nviz.util.SurveyConstants;
import com.nviz.vo.User;
import com.nviz.vo.UserDAO;

@Path(SurveyConstants.USER_PATH)
public class UserRestService {
	
	@Context private HttpServletRequest request;
	@GET
	@Path(SurveyConstants.LOGIN_PATH)
    @Produces(MediaType.APPLICATION_JSON)
	
	public String login(@Context UriInfo uriInfo) throws JSONException {
		String parameters = uriInfo.getRequestUri().getQuery();
		JSONObject jObject = new JSONObject(parameters);
		JSONObject json = new JSONObject();
		String username = jObject.getString(SurveyConstants.USERNAME);
		String password = jObject.getString(SurveyConstants.PASSWORD);
		UserDAO userDAO = new UserDAO();
		Map<String, Object> userMap = null;
		userMap = userDAO.userLogIn(username , password);
		if (userMap != null && !userMap.isEmpty()) {
			getRequest().getSession(true).setAttribute(SurveyConstants.USERNAME, username);
			getRequest().getSession(true).setAttribute(SurveyConstants.PASSWORD,password );
			getRequest().getSession(true).setAttribute("userId",userMap.get("userId") ); 
			json.put(SurveyConstants.STATUS, "OK");
			json.put(SurveyConstants.MESSAGE, "Successfully Login");
		} else {
			json.put(SurveyConstants.STATUS, "Failed");
			json.put(SurveyConstants.MESSAGE, "Invalid Credentials");
		}
		return json.toString();
	}
	
	@GET
	@Path(SurveyConstants.ADD_USER_PATH)
	@Produces(MediaType.APPLICATION_JSON)
	public String addUser(@Context UriInfo uriInfo) throws JSONException {
		String parameters = uriInfo.getRequestUri().getQuery();
		JSONObject userJson = new JSONObject(parameters);
		UserDAO userDAO = new UserDAO();
		JSONObject json = new JSONObject();
		boolean userAdded = false;
		
		//userAdded = userDAO.createUser(userJson);
		if (userAdded) {
			json.put(SurveyConstants.STATUS, "OK");
			json.put(SurveyConstants.STATUS_DESCRIPTION, "User added successfully");
		} else {
			json.put(SurveyConstants.STATUS, "Failed");
			json.put(SurveyConstants.STATUS_DESCRIPTION, "Failed to add user");
		}
		return json.toString();
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
}
