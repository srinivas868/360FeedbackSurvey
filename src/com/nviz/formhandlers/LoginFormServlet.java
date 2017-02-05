package com.nviz.formhandlers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.nviz.util.SurveyManagerTools;
import com.nviz.vo.User;


public class LoginFormServlet extends HttpServlet {

	private static final long serialVersionUID = -7356580162088706365L;
	private SurveyManagerTools SurveyManagerTools;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject responseJson = new JSONObject();
		try {
			User user = getSurveyManagerTools().login(request);
			if(user != null){
				responseJson.put("code", "success");
				request.getSession().setAttribute("user", user);
			} else{
				responseJson.put("code", "error");
			}
		} catch (Exception e) {
			getSurveyManagerTools().logDebug("LoginFormServlet:: doGet:: exception while performing login perations "+e);
			try {
				responseJson.put("code", "error");
			} catch (JSONException e1) {
				getSurveyManagerTools().logDebug("LoginFormServlet:: doGet:: exception while writing data to JSON "+e);
			}
		}finally{
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
