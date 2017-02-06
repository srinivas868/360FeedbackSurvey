package com.nviz.report.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.json.JSONException;
import org.json.JSONObject;
import com.nviz.util.SurveyManagerTools;
import com.nviz.vo.ReportRecordTrail;
import com.nviz.vo.Survey;

@Path("/reportmanager")
public class SurveyReportManagerRestService {
	private SurveyManagerTools surveyManagerTools;
	
	@Context private HttpServletRequest request;
	@GET
	@Path("/view")
    @Produces(MediaType.APPLICATION_JSON)
	public String viewReport(@Context UriInfo uriInfo) throws JSONException {
		JSONObject responseJson = new JSONObject();
		try{
			String parameters = uriInfo.getRequestUri().getQuery();
			JSONObject jObject = new JSONObject(parameters);
			String surveyId = jObject.getString("param1");
			String userId = jObject.getString("param2");
			Survey survey = (Survey) getSurveyManagerTools().getItem(Survey.class, surveyId);
			if(survey != null){
				ReportRecordTrail record = getSurveyManagerTools().getReportItem(userId, surveyId);
				if(record != null){
					responseJson.put("recordPath", record.getReportPath());
					responseJson.put("status", "success");
				} else{
					responseJson.put("status", "error");
					responseJson.put("message", "Report not available right now.");
				}
			} else{
				responseJson.put("status", "error");
				responseJson.put("message", "Invalid survey id provided");
			}
		} catch (Exception e) {
			getSurveyManagerTools().logDebug("SurveyReportManagerRestService:: viewReport:: exception while performing survey report operations "+e);
			try {
				responseJson.put("status", "error");
				responseJson.put("message", "Something went wrong!");
			} catch (JSONException e1) {
				getSurveyManagerTools().logDebug("SurveyReportManagerRestService:: viewReport:: exception while writing data to JSON "+e);
			}
		}finally{
		}
		return responseJson.toString();
	}
	
	@GET
	@Path("/generate")
    @Produces(MediaType.APPLICATION_JSON)
	public String generateReport(@Context UriInfo uriInfo) throws JSONException {
		JSONObject responseJson = new JSONObject();
		try{
			String parameters = uriInfo.getRequestUri().getQuery();
			JSONObject jObject = new JSONObject(parameters);
			String surveyId = jObject.getString("param1");
			Survey survey = (Survey) getSurveyManagerTools().getItem(Survey.class, surveyId);
			getSurveyManagerTools().generateReport(survey);
			responseJson.put("status", "success");
			responseJson.put("message", "Reports generated & placed in specified directory");
		} catch (Exception e) {
			getSurveyManagerTools().logDebug("SurveyReportManagerRestService:: generateReport:: exception while generating survey report "+e);
			try {
				responseJson.put("status", "error");
				responseJson.put("message", "Something went wrong!");
			} catch (JSONException e1) {
				getSurveyManagerTools().logDebug("SurveyReportManagerRestService:: generateReport:: exception while writing data to JSON "+e);
			}
		}finally{
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
