package com.nviz.formhandler;

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

/**
 * @author Srinivas Sangishetty
 */
public class LogoutFormServlet extends HttpServlet {
	private static final long serialVersionUID = 5183708453315757974L;
	private SurveyManagerTools SurveyManagerTools;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject responseJson = new JSONObject();
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user != null){
				responseJson.put("code", "success");
				request.getSession().setAttribute("user", null);
				request.getSession().invalidate();
			} else{
				responseJson.put("code", "error");
				getSurveyManagerTools().logDebug("LogoutFormServlet:: doPost:: User is not logged in");
			}
		} catch (Exception e) {
			getSurveyManagerTools().logDebug("LogoutFormServlet:: doPost:: exception while performing logout perations "+e);
			try {
				responseJson.put("code", "error");
			} catch (JSONException e1) {
				getSurveyManagerTools().logDebug("LogoutFormServlet:: doPost:: exception while writing data to JSON "+e);
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
