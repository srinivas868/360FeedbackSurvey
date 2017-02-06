package com.nviz.formhandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.nviz.util.SurveyManagerTools;
import com.nviz.vo.Quality;
import com.nviz.vo.Record;
import com.nviz.vo.Survey;
import com.nviz.vo.User;


/**
 * this servlet will collect user survey data
 * @author Srinivas Sangishetty
 */
public class SurveyFormDataServlet extends HttpServlet {

	private static final String DASH = "-";
	private static final String RATING = "rating";
	private static final long serialVersionUID = -7450126892652140289L;
	private SurveyManagerTools SurveyManagerTools;

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject responseJson = new JSONObject();
		try {
			String surveyId = (String) request.getParameter("surveyId");
			if(surveyId != null){
				Survey survey = (Survey) getSurveyManagerTools().getItem(Survey.class,surveyId); 
				List<Quality> qualityItems = survey.getQualities();
				List<User> userItems = getSurveyManagerTools().getItems("User");
				for(User user : userItems){
					for(Quality quality : qualityItems){
						Record record = new Record();
						record.setSurvey(survey);
						record.setUser(user);
						record.setQuality(quality);
						String reqInputstring = quality.getQualityId()+DASH+user.getId()+DASH+RATING;
						String input = request.getParameter(reqInputstring);
						if(input != null){
							record.setInput(Integer.valueOf(input));
							getSurveyManagerTools().addItem(record);
						}
					}
				}
				responseJson.put("code", "success");
			} else{
				getSurveyManagerTools().logDebug("Survey Id is empty");
				responseJson.put("code", "error");
			}
		} catch (Exception e) {
			getSurveyManagerTools().logDebug("SurveyFormDataServlet:: doGet:: exception while performing suvey form data operations "+e);
			try {
				responseJson.put("code", "error");
			} catch (JSONException e1) {
				getSurveyManagerTools().logDebug("SurveyFormDataServlet:: doGet:: exception while writing data to JSON "+e);
			}
		} finally{
			PrintWriter out = response.getWriter();
			out.print(responseJson);
			out.flush();
		}
	}
	
	@Override
	public void destroy() {
		this.SurveyManagerTools = null;
	}
	@Override
	public void init() throws ServletException {
		if(this.SurveyManagerTools == null){
			this.SurveyManagerTools = new SurveyManagerTools();
		}
	}
	public SurveyManagerTools getSurveyManagerTools() {
		return SurveyManagerTools;
	}
}
