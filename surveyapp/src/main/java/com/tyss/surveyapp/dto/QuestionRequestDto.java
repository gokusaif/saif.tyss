package com.tyss.surveyapp.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class QuestionRequestDto {

	@Id
	@Column
	@GeneratedValue
	private int surveyId;
	
	@Column
	QuestionDetailsDto allQuestionsOfSurvey;
	
	
	
	
}
