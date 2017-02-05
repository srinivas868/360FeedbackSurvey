package com.nviz.rest;

import java.util.Map;

import javax.servlet.ServletException;
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

@Path(SurveyConstants.USER_PATH)
public class UserManagerRestService {

	private SurveyManagerTools surveyManagerTools;
	
	@Context private HttpServletRequest request;
	@GET
	@Path("/validateLogin")
    @Produces(MediaType.APPLICATION_JSON)
	public String validateUserLogin(@Context UriInfo uriInfo) throws JSONException {
		JSONObject json = new JSONObject();
		if(getRequest() != null){
			User user = (User) getRequest().getSession().getAttribute("user");
			if(user != null){
				json.put(SurveyConstants.STATUS, "Success");
			} else{
				json.put(SurveyConstants.STATUS, "Failed");
			}
		} else{
			getSurveyManagerTools().logDebug("UserManagerRestService:: validateUserLogin:: request found empty.");
			json.put(SurveyConstants.STATUS, "Failed");
		}
		return json.toString();
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
