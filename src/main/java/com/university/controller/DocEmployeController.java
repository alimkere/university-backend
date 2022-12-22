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

import com.university.models.DocEmploye;
import com.university.repository.DocEmployeRepository;
import com.university.repository.EmployeRepository;

@RequestMapping("/api")
@RestController
public class DocEmployeController {
	
	@Autowired
	private DocEmployeRepository docEmployeRepository;
	
	@Autowired
	private EmployeRepository employeRepository;
	
    @GetMapping("/docemployes")
	public List<DocEmploye> listDocuments(){
		return docEmployeRepository.findAll();
	}
    
    @GetMapping("/docemployes/{documentId}")
	public ResponseEntity<DocEmploye> getDocEmployeById(@PathVariable Long documentId){
    	DocEmploye docEmploye = docEmployeRepository.findById(documentId).
		orElseThrow(()-> new ResourceNotFoundException("The Document with id "+ documentId +" doesn't exit in the database !!"));
		
		return new ResponseEntity<>(docEmploye,HttpStatus.OK);
		
	}
    
    @GetMapping("/employes/{employeId}/docemployes")
    public Page<DocEmploye> getAllDocumentsByEmployeId(@PathVariable  Long employeId,
                                                Pageable pageable) {
        return docEmployeRepository.findByEmployeId(employeId, pageable);
    }
    
    @PostMapping("/employes/{employeId}/docemployes")
    public DocEmploye createDocumentByEmployeId(@PathVariable  Long employeId,
                                 @Valid @RequestBody DocEmploye docEmploye) {
    	return employeRepository.findById(employeId).map(employe -> {
        	docEmploye.setEmploye(employe);
            return docEmployeRepository.save(docEmploye);
        }).orElseThrow(() -> new ResourceNotFoundException("EmployeId " + employeId + " not found"));
    }
    
    @PutMapping("/employes/{employeId}/docemployes/{documentId}")
    public DocEmploye updateDocumentFromEmploye(@PathVariable  Long employeId,
                                 @PathVariable  Long documentId,
                                 @Valid @RequestBody DocEmploye documentRequest) {
        if(!employeRepository.existsById(employeId)) {
            throw new ResourceNotFoundException("EmployeId " + employeId + " not found");
        }

        return docEmployeRepository.findById(documentId).map(docEmploye -> {
        	docEmploye.setName(documentRequest.getName());
        	docEmploye.setFile(documentRequest.getFile());
            return docEmployeRepository.save(docEmploye);
        }).orElseThrow(() -> new ResourceNotFoundException("Employe DocumentId " + documentId + "not found"));
    }
  
    
    @DeleteMapping("/employes/{employeId}/docemployes/{documentId}")
    public ResponseEntity<?> deleteDocumentFromEmploye(@PathVariable Long employeId,
                              @PathVariable Long documentId) {
        return docEmployeRepository.findByIdAndEmployeId(documentId, employeId).map(docEmploye -> {
        	docEmployeRepository.delete(docEmploye);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Employe Document not found with id " + documentId + " and employeId " + employeId));
    }

}
