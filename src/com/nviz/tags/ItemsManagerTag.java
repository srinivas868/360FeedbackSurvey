package com.nviz.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.nviz.util.SurveyManagerTools;

public class ItemsManagerTag extends SimpleTagSupport {
	
	private SurveyManagerTools SurveyManagerTools;
	private String type;
	
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		try {
			List items = getSurveyManagerTools().getItems(getType());
			out.println(items);
		} catch (Exception e) {
			out.println("EXception while fetching survey items "+e);
		}
	    out.println("Hello Custom Tag!");
	  }

	public SurveyManagerTools getSurveyManagerTools() {
		if(this.SurveyManagerTools == null){
			this.SurveyManagerTools = new SurveyManagerTools();
		}
		return SurveyManagerTools;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
