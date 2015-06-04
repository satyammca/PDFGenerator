package com.competency.pdfgenerator.examples.beans;


public class WorkExpModel {
    private String company;
    private String startDate;
    private String endDate;
    
	public WorkExpModel(String company, String startDate, String endDate) {
		super();
		this.company = company;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
    
}
