package com.nviz.vo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

public class Survey {
	private int surveyId;
	private String title;
	private String description;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "QUALITY_MASTER")
	private List<Quality> qualities = new ArrayList<>();
	private int qualitiesCount;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}
	public List<Quality> getQualities() {
		return qualities;
	}
	public void setQualities(List<Quality> qualities) {
		this.qualities = qualities;
	}
	public int getQualitiesCount() {
		return this.qualitiesCount = this.qualities.size();
	}
	public void setQualitiesCount(int qualitiesCount) {
		this.qualitiesCount = qualitiesCount;
	}
}
