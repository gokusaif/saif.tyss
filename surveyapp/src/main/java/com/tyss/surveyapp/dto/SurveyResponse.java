package com.tyss.surveyapp.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table( name = "survey_response")
@Data
public class SurveyResponse implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GeneratedValue
	@Id
	@Column
	private int surveyResponseId;
	
	@Column
	private String userEmail;
	
	@Column
	private String surveyName;
	
	@Column
	@OneToMany(cascade = CascadeType.ALL)
	private List<Question> questions;
	
}
