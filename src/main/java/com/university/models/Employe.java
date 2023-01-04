package com.university.models;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


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
public class Employe{
	
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
	@Column(name = "picture")
	private String picture;
	
	@NotBlank
	@Column(name = "poste")
	private String poste;
	
	//optional=false is a runtime instruction
	 @ManyToOne()
	 private Department department;
	 
	 @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<DocEmploye> docEmployes;
	 
	 @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "created_at", nullable = false, updatable = false)
		@CreationTimestamp
	    private Date createdAt;

	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "updated_at", nullable = false)
	    @UpdateTimestamp
	    private Date updatedAt;

}
