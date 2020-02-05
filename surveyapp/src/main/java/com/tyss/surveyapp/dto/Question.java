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

@Data
@Entity
@Table( name = "questions")
public class Question implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@GeneratedValue
	@Id
	@Column
	private int questionId;
	
	@Column
	private  String questionName;
	
	@Column
	private String questionType;
	

	@OneToMany(cascade = CascadeType.ALL)
	@Column
	private List<Options> options;
	
	
}
              