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

    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName ;
    
    @Column(name = "cin")
    private String cin;
    
    @Column(name = "passport")
    private String passport;
    
    @Column(name = "birth_day")
    private Date birthDay;
    
    @Column(name = "picture")
    private String picture;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "adress")
    private String adress;
    
    @Column(name = "zip_code")
    private String zipCode;
    
    @Column(name = "country")
    private String country;
    
    @Column(name = "town")
    private String town;

    @Column(name = "study_level")
    private String studyLevel;
    
    @Column(name = "status")
    private String status;
    
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
