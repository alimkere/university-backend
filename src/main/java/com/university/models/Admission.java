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
	
	@NotNull
	@Column(name = "main_choice")
	private String mainChoice;

	@NotNull
	@Column(name = "second_choice")
	private String secondChoice;
	
	@NotNull
	@Column(name = "app_fees_pay_date")
	private Date appFeesPayDate ;
	
	@NotNull
	@Column(name = "app_fees")
	private double appFees ;
	
	@NotNull
	@Column(name = "graduation_place")
	private String graduationPlace ;
	
	@NotNull
	@Column(name = "app_fees_proof")
	private String appFeesProof;
	
	@NotNull
	@Column(name = "session")
	private String session;
	
	@ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
	@JsonIdentityReference(alwaysAsId=true)
	@JsonProperty("student_id")
    private Student student;
}
