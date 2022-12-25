package com.university.models;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "employe")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employe extends AuditModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@NotBlank
	@Column(name = "first_name")
	private String firstName;
	
	@NotBlank
	@Column(name = "last_name")
	private String lastName;
	
	@NotBlank
	@Column(name = "email",unique = true)
	private String email;
	
	@NotBlank
	@Column(name = "phone", unique = true)
	private String phone;
	
	@NotBlank
	@Column(name = "poste")
	private String poste;
	
	//optional=false is a runtime instruction
	 @ManyToOne(fetch = FetchType.LAZY, optional = false)
	 
	//nullable=false is an instruction for generating the schema
	 @JoinColumn(name = "department_id", nullable = false)
	 
	//OnDelete decides whether deleting an entry from database will delete the rows represented by joined sub class or not
	  @OnDelete(action = OnDeleteAction.CASCADE)
	 
	//JsonIdentityInfo is used to indicate that object identity will be used during serialization/de-serialization
   //Or it is used when objects have parent child relationship
	  @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
	 
	//JsonIdentityReference annotation can be used along with @JsonIdentityInfo to serialize Object by its id instead of as full POJO
	  @JsonIdentityReference(alwaysAsId=true)
	 
	//This annotation can be used on fields or getters or setters. It permit to rename properties
	  @JsonProperty("department_id")
	 private Department department;
	 
	 @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<DocEmploye> docEmployes;

}
