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
	
	@Column(name = "begin_date")
	private Date beginDate;
	
	@Column(name = "end_date")
	private Date endDate;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "file")
	private String file;
	
	@Column(name = "mention")
	private String mention;
	
	@Column(name = "school")
	private String school;
	
	@Column(name = "country")
	private String country;
	
	@ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

}
