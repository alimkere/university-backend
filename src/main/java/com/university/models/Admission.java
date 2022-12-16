package com.university.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "admission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Admission extends AuditModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "main_choice")
	private String mainChoice;
	
	@Column(name = "second_choice")
	private String secondChoice;
	
	@Column(name = "app_fees_date")
	private Date appFeesDate ;
	
	@Column(name = "app_fees")
	private double appFees ;
	
	@Column(name = "graduation_place")
	private String graduationPlace ;
	
	@Column(name = "app_fees_proof")
	private String appFeesProof;
	
	@Column(name = "session")
	private String session;
}
