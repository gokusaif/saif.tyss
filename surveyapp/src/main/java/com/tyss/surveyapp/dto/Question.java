package com.tyss.surveyapp.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@ElementCollection
	@CollectionTable
	@Column
	private List<String> options;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "surveyName")
	private Survey survey;
	
	 
	
}
              