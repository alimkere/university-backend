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
@Table(name = "registration")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Payment extends AuditModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "payment_date")
	private Date paymentDate;
	
	@Column(name = "description")
	private String description;

	@Column(name = "amount")
	private double amount;
	
	@Column(name = "transaction_number")
	private String transactionNumber;

}
