package com.competency.pdfgenerator.examples.beans;


import java.util.List;

public class PDFMapObject {
	
	private PersonalDetailsModel personalDetailsModel;
	private List<WorkExpModel> workExpModelList;
	//To-Do if we require then we can declare beans here to map to PDF.
	public PersonalDetailsModel getPersonalDetailsModel() {
		return personalDetailsModel;
	}

	public void setPersonalDetailsModel(PersonalDetailsModel personalDetailsModel) {
		this.personalDetailsModel = personalDetailsModel;
	}

	public List<WorkExpModel> getWorkExpModelList() {
		return workExpModelList;
	}

	public void setWorkExpModelList(List<WorkExpModel> workExpModelList) {
		this.workExpModelList = workExpModelList;
	}
	
}
