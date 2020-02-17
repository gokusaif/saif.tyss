package com.onebill.customermanagement.dto;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "customer")
public class Customer {

	
	@GeneratedValue
	@Id
	@Column
	private int id;
	
	@Column
	private String name;
	
	@Column
	private String date;
	
	@Transient
	private Integer partnerId;
	
	@Column
	private String status= "Active";
	
	@ElementCollection
	@CollectionTable(name = "addresses")
	@Column
	private List<String> addresses;
	
	@ElementCollection
	@CollectionTable(name = "emails")
	@Column
	private List<String> emails;
	
	@ElementCollection
	@CollectionTable(name = "phoneNumbers")
	@Column
	private List<String> phoneNumbers;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "partnerId")
	private Partner partner;
}
