package com.nviz.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.json.JSONException;

import com.nviz.rest.SurveyManagerService;

public class ItemsManagerTag extends SimpleTagSupport {
	
	private SurveyManagerService surveyManagerService;
	private String type;
	
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		try {
			List items = getSurveyManagerService().getItems(getType());
			out.println(items);
		} catch (Exception e) {
			out.println("EXception while fetching survey items "+e);
		}
	    out.println("Hello Custom Tag!");
	  }

	public SurveyManagerService getSurveyManagerService() {
		if(this.surveyManagerService == null){
			this.surveyManagerService = new SurveyManagerService();
		}
		return surveyManagerService;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
