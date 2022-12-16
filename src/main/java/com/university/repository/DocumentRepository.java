package com.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.university.models.Document;

@RepositoryRestResource
public interface DocumentRepository extends JpaRepository<Document, Long>{

}
