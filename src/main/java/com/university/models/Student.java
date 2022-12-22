package com.university.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student extends AuditModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

	@NotNull
    @Column(name = "first_name")
    private String firstName;
    
	@NotNull
    @Column(name = "last_name")
    private String lastName ;
    
	@NotNull
    @Column(name = "cin", unique = true)
    private String cin;
    
	@NotNull
    @Column(name = "passport", unique = true)
    private String passport;
    
	@NotNull
    @Column(name = "birth_day")
    private Date birthDay;
    
	@NotNull
    @Column(name = "picture")
    private String picture;
    
	@NotNull
    @Column(name = "phone",unique = true)
    private String phone;
    
	@NotNull
    @Column(name = "adress")
    private String adress;
    
	@NotNull
    @Column(name = "zip_code", unique = true)
    private String zipCode;
    
	@NotNull
    @Column(name = "country")
    private String country;
    
	@NotNull
    @Column(name = "town")
    private String town;

	@NotNull
    @Column(name = "study_level")
    private String studyLevel;
    
	@NotNull
    @Column(name = "status")
    private String status;
    
	@NotNull
    @Column(name = "sex")
    private String sex;
    
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Diploma> diplomas;
    
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DocStudent> docStudents;
    
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Language> languages;
    
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Admission> admissions;
    
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Enrollment enrollment;
    

}
