package com.university.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.university.models.Admission;
import com.university.repository.AdmissionRepository;
import com.university.repository.StudentRepository;

@RequestMapping("/api")
@RestController
public class AdmissionController {
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private AdmissionRepository admissionRepository;
	
	@GetMapping("/admissions")
	public List<Admission> listAdmissions(){
		return admissionRepository.findAll();
	}
	
	 @GetMapping("/admissions/{admissionId}")
	   	public ResponseEntity<Admission> getLanguageById(@PathVariable Long admissionId){
		 Admission admission = admissionRepository.findById(admissionId).
	   		orElseThrow(()-> new ResourceNotFoundException("The Admission with id "+ admissionId +" doesn't exit in the database !!"));
	   		
	   		return new ResponseEntity<>(admission,HttpStatus.OK);
	   		
	   	}
	 
	 @GetMapping("/students/{studentId}/admissions")
	    public Page<Admission> getAllAdmisionsByStudentId(@PathVariable  Long studentId,
	                                                Pageable pageable) {
	        return admissionRepository.findByStudentId(studentId, pageable);
	    }
	 
	 @PostMapping("/students/{studentId}/admissions")
	    public Admission createAdmission(@PathVariable  Long studentId,
	                                 @Valid @RequestBody Admission admission) {
	        return studentRepository.findById(studentId).map(student -> {
	           admission.setStudent(student);
	            return admissionRepository.save(admission);
	        }).orElseThrow(() -> new ResourceNotFoundException("StudentId " + studentId + " not found"));
	    }
	    
	 @PutMapping("/students/{studentId}/admissions/{admissionId}")
	    public Admission updateAdmission(@PathVariable  Long studentId,
	                                 @PathVariable  Long admissionId,
	                                 @Valid @RequestBody Admission admissionRequest) {
	        if(!studentRepository.existsById(studentId)) {
	            throw new ResourceNotFoundException("StudentId " + studentId + " not found");
	        }

	        return admissionRepository.findById(admissionId).map(admission -> {
	            admission.setMainChoice(admissionRequest.getMainChoice());
	            admission.setSecondChoice(admissionRequest.getSecondChoice());
	            admission.setAppFeesDate(admissionRequest.getAppFeesDate());
	            admission.setAppFees(admissionRequest.getAppFees());
	            admission.setGraduationPlace(admissionRequest.getGraduationPlace());
	            admission.setAppFeesProof(admissionRequest.getAppFeesProof());
	            admission.setSession(admissionRequest.getSession());
	            return admissionRepository.save(admission);
	        }).orElseThrow(() -> new ResourceNotFoundException("AdmissionId " + admissionId + "not found"));
	    }
	 
	 @DeleteMapping("/students/{studentId}/admissions/{admissionId}")
	    public ResponseEntity<?> deleteAdmission(@PathVariable Long studentId,
	                              @PathVariable Long admissionId) {
	        return admissionRepository.findByIdAndStudentId(admissionId, studentId).map(admission -> {
	            admissionRepository.delete(admission);
	            return ResponseEntity.ok().build();
	        }).orElseThrow(() -> new ResourceNotFoundException("Admission not found with id " + admissionId + " and studentId " + studentId));
	    }

}
