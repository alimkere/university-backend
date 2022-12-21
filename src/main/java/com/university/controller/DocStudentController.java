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

import com.university.models.DocStudent;
import com.university.repository.DocStudentRepository;
import com.university.repository.StudentRepository;

@RequestMapping("/api")
@RestController
public class DocStudentController {
	
	@Autowired
	private DocStudentRepository docStudentRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
    @GetMapping("/docstudents")
	public List<DocStudent> listDocuments(){
		return docStudentRepository.findAll();
	}
    
    @GetMapping("/docstudents/{documentId}")
	public ResponseEntity<DocStudent> getDocStudentById(@PathVariable Long documentId){
    	DocStudent docStudent = docStudentRepository.findById(documentId).
		orElseThrow(()-> new ResourceNotFoundException("The Document with id "+ documentId +" doesn't exit in the database !!"));
		
		return new ResponseEntity<>(docStudent,HttpStatus.OK);
		
	}
    
    @GetMapping("/students/{studentId}/docstudents")
    public Page<DocStudent> getAllDocumentsByStudentId(@PathVariable  Long studentId,
                                                Pageable pageable) {
        return docStudentRepository.findDocByStudentId(studentId, pageable);
    }
    
    @PostMapping("/students/{studentId}/docstudents")
    public DocStudent createDocumentByStudentId(@PathVariable  Long studentId,
                                 @Valid @RequestBody DocStudent docStudent) {
        return studentRepository.findById(studentId).map(student -> {
           docStudent.setStudent(student);
            return docStudentRepository.save(docStudent);
        }).orElseThrow(() -> new ResourceNotFoundException("StudentId " + studentId + " not found"));
    }
    
    
    @PutMapping("/students/{studentId}/docstudents/{documentId}")
    public DocStudent updateDocumentFromStudent(@PathVariable  Long studentId,
                                 @PathVariable  Long documentId,
                                 @Valid @RequestBody DocStudent documentRequest) {
        if(!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("StudentId " + studentId + " not found");
        }

        return docStudentRepository.findById(documentId).map(document -> {
            document.setName(documentRequest.getName());
            document.setFile(documentRequest.getFile());
            return docStudentRepository.save(document);
        }).orElseThrow(() -> new ResourceNotFoundException("DocumentId " + documentId + "not found"));
    }

    @DeleteMapping("/students/{studentId}/docstudents/{documentId}")
    public ResponseEntity<?> deleteDocumentFromStudent(@PathVariable Long studentId,
                              @PathVariable Long documentId) {
        return docStudentRepository.findByIdAndStudentId(documentId, studentId).map(document -> {
            docStudentRepository.delete(document);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Document not found with id " + documentId + " and studentId " + studentId));
    }

}
