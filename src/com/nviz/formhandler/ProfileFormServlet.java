package com.nviz.formhandler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.nviz.account.util.PasswordUtil;
import com.nviz.util.SurveyManagerTools;
import com.nviz.vo.User;


/**
 * this servlet will collect user survey data
 * @author Srinivas Sangishetty
 */
public class ProfileFormServlet extends HttpServlet {

	private static final String DASH = "-";
	private static final String RATING = "rating";
	private static final long serialVersionUID = -7450126892652140289L;
	private SurveyManagerTools surveyManagerTools;

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject responseJson = new JSONObject();
		try {
			User user = new User();
			String password = PasswordUtil.encrypt(request.getParameter("password"));
			user.setPassword(password);
			user.setLogin(request.getParameter("login"));
			user.setFirstName(request.getParameter("firstName"));
			user.setLastName(request.getParameter("lastName"));
			user.setEmployeeId(request.getParameter("employeeId"));
			getSurveyManagerTools().addItem(user);
			responseJson.put("code", "success");
		} catch (Exception e) {
			getSurveyManagerTools().logDebug("ProfileFormServlet:: doGet:: exception while performing suvey form data operations "+e);
			try {
				responseJson.put("code", "error");
			} catch (JSONException e1) {
				getSurveyManagerTools().logDebug("ProfileFormServlet:: doGet:: exception while writing data to JSON "+e);
			}
		}finally{
			PrintWriter out = response.getWriter();
			out.print(responseJson);
			out.flush();
		}
	}
	
	@Override
	public void destroy() {
		this.surveyManagerTools = null;
	}
	@Override
	public void init() throws ServletException {
		if(this.surveyManagerTools == null){
			this.surveyManagerTools = new SurveyManagerTools();
		}
	}
	public SurveyManagerTools getSurveyManagerTools() {
		return surveyManagerTools;
	}
}
