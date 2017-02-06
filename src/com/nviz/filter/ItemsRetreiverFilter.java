package com.nviz.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.nviz.util.SurveyManagerTools;

public class ItemsRetreiverFilter implements Filter{

	private static final String TYPE = "type";
	private SurveyManagerTools SurveyManagerTools;
	
	@Override
	public void destroy() {
		this.SurveyManagerTools = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain pChain) throws IOException, ServletException {
		try {
			List surveyItems = getSurveyManagerTools().getItems("Survey",false);
			List userItems = getSurveyManagerTools().getItems("User",false);
			List ratingItems = getSurveyManagerTools().getItems("Rating",false);
			request.setAttribute("surveyItems", surveyItems);
			request.setAttribute("userItems", userItems);
			request.setAttribute("ratingItems", ratingItems);
		} catch (Exception e) {
			getSurveyManagerTools().logDebug("Exception while fetching survey items "+e);
		} finally{
			pChain.doFilter(request,response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		if(this.SurveyManagerTools == null){
			this.SurveyManagerTools = new SurveyManagerTools();
		}
	}
	
	public SurveyManagerTools getSurveyManagerTools() {
		return SurveyManagerTools;
	}

}
