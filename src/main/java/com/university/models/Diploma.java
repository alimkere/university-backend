package com.university.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
@Table(name = "diploma")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Diploma extends AuditModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@NotNull
	@Column(name = "begin_date")
	private Date beginDate;
	
	@NotNull
	@Column(name = "end_date")
	private Date endDate;
	
	@NotNull
	@Column(name = "title")
	private String title;
	
	@NotNull
	@Column(name = "serie")
	private String serie;
	
	@NotNull
	@Column(name = "file", unique = true)
	private String file;
	
	@NotNull
	@Column(name = "mention")
	private String mention;
	
	@NotNull
	@Column(name = "school")
	private String school;
	
	@NotNull
	@Column(name = "country")
	private String country;
	
	@ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
	@JsonIdentityReference(alwaysAsId=true)
	@JsonProperty("student_id")
    private Student student;

}
