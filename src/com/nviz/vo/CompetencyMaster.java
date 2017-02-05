package com.nviz.vo;

public class CompetencyMaster {
	
	private int competencyId;
	private String title;
	private String description;
	private String additionalSkills;

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
	public String getAdditionalSkills() {
		return additionalSkills;
	}
	public void setAdditionalSkills(String additionalSkills) {
		this.additionalSkills = additionalSkills;
	}
	public int getCompetencyId() {
		return competencyId;
	}
	public void setCompetencyId(int competencyId) {
		this.competencyId = competencyId;
	}

}
