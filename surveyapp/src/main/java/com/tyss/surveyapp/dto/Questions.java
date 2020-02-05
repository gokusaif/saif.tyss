package com.tyss.surveyapp.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table( name = "form")
public class Questions implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@Column
	private String surveyName;
	
	@Column
	@OneToMany(cascade = CascadeType.ALL)
	private List<Question> questions;
	
}
