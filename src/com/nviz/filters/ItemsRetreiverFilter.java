package com.nviz.filters;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.json.JSONException;

import com.nviz.rest.SurveyManagerService;
import com.nviz.vo.Survey;

public class ItemsRetreiverFilter implements Filter{

	private static final String TYPE = "type";
	private SurveyManagerService surveyManagerService;
	
	@Override
	public void destroy() {
		this.surveyManagerService = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain pChain) throws IOException, ServletException {
		try {
			List surveyItems = getSurveyManagerService().getItems("Survey",false);
			List userItems = getSurveyManagerService().getItems("User",false);
			List ratingItems = getSurveyManagerService().getItems("Rating",false);
			request.setAttribute("surveyItems", surveyItems);
			request.setAttribute("userItems", userItems);
			request.setAttribute("ratingItems", ratingItems);
		} catch (Exception e) {
			getSurveyManagerService().logDebug("Exception while fetching survey items "+e);
		} finally{
			pChain.doFilter(request,response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		if(this.surveyManagerService == null){
			this.surveyManagerService = new SurveyManagerService();
		}
	}
	
	public SurveyManagerService getSurveyManagerService() {
		return surveyManagerService;
	}

}
