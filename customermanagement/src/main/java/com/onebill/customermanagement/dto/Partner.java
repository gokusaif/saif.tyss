package com.onebill.customermanagement.dto;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "partner")
public class Partner {

	@GeneratedValue
	@Id
	@Column
	private int id;
	
	@Column
	private String name;
	
	@Column
	private String date;
	
	@Column
	private String status;
	
	@Transient
	private Integer partnerId;
	
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
	
	@OneToMany
	@Column
	private List<Customer> customers;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "partnerId")
	private Partner partner;
	
	@OneToMany
	@Column
	private List<Partner> subPartners;
	
}
