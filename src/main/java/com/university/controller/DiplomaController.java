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

import com.university.models.Diploma;
import com.university.repository.DiplomaRepository;
import com.university.repository.StudentRepository;

@RequestMapping("/api")
@RestController
public class DiplomaController {
	
	@Autowired
	private DiplomaRepository diplomaRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
    @GetMapping("/diplomas")
	public List<Diploma> listDiplomas(){
		return diplomaRepository.findAll();
	}
    
    @GetMapping("/diplomas/{diplomaId}")
	public ResponseEntity<Diploma> getDiplomaById(@PathVariable Long diplomaId){
		Diploma diploma = diplomaRepository.findById(diplomaId).
		orElseThrow(()-> new ResourceNotFoundException("The Diploma with id "+ diplomaId +" doesn't exit in the database !!"));
		
		return new ResponseEntity<>(diploma,HttpStatus.OK);
		
	}
    
    @GetMapping("/students/{studentId}/diplomas")
    public Page<Diploma> getAllDiplomasByStudentId(@PathVariable  Long studentId,
                                                Pageable pageable) {
        return diplomaRepository.findByStudentId(studentId, pageable);
    }
    
    @PostMapping("/students/{studentId}/diplomas")
    public Diploma createDiploma(@PathVariable  Long studentId,
                                 @Valid @RequestBody Diploma diploma) {
        return studentRepository.findById(studentId).map(student -> {
           diploma.setStudent(student);
            return diplomaRepository.save(diploma);
        }).orElseThrow(() -> new ResourceNotFoundException("StudentId " + studentId + " not found"));
    }
    
    @PutMapping("/students/{studentId}/diplomas/{diplomaId}")
    public Diploma updateDiploma(@PathVariable  Long studentId,
                                 @PathVariable  Long diplomaId,
                                 @Valid @RequestBody Diploma diplomaRequest) {
        if(!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("StudentId " + studentId + " not found");
        }

        return diplomaRepository.findById(diplomaId).map(diploma -> {
            diploma.setBeginDate(diplomaRequest.getBeginDate());
            diploma.setEndDate(diplomaRequest.getEndDate());
            diploma.setTitle(diplomaRequest.getTitle());
            diploma.setFile(diplomaRequest.getFile());
            diploma.setMention(diplomaRequest.getMention()); 
            diploma.setSchool(diplomaRequest.getSchool()); 
            diploma.setCountry(diplomaRequest.getCountry()); 
            return diplomaRepository.save(diploma);
        }).orElseThrow(() -> new ResourceNotFoundException("DiplomaId " + diplomaId + "not found"));
    }

    @DeleteMapping("/students/{studentId}/diplomas/{diplomaId}")
    public ResponseEntity<?> deleteDiploma(@PathVariable (value = "studentId") Long studentId,
                              @PathVariable Long diplomaId) {
        return diplomaRepository.findByIdAndStudentId(diplomaId, studentId).map(diploma -> {
            diplomaRepository.delete(diploma);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Diploma not found with id " + diplomaId + " and studentId " + studentId));
    }
}
