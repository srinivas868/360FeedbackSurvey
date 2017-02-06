package com.nviz.account.rest;

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
import com.nviz.util.SurveyManagerTools;
import com.nviz.vo.User;

@Path("/usermanager")
public class UserManagerRestService {

	private SurveyManagerTools surveyManagerTools;
	
	@Context private HttpServletRequest request;
	@GET
	@Path("/validateLogin")
    @Produces(MediaType.APPLICATION_JSON)
	public String validateUserLogin(@Context UriInfo uriInfo) throws JSONException {
		JSONObject responseJson = new JSONObject();
		if(getRequest() != null){
			User user = (User) getRequest().getSession().getAttribute("user");
			if(user != null){
				responseJson.put(SurveyConstants.STATUS, "Success");
			} else{
				responseJson.put(SurveyConstants.STATUS, "Failed");
			}
		} else{
			getSurveyManagerTools().logDebug("UserManagerRestService:: validateUserLogin:: request found empty.");
			responseJson.put(SurveyConstants.STATUS, "Failed");
		}
		return responseJson.toString();
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public SurveyManagerTools getSurveyManagerTools() {
		if(this.surveyManagerTools == null){
			this.surveyManagerTools = new SurveyManagerTools();
		}
		return surveyManagerTools;
	}
}
