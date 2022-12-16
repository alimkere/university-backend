package com.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.university.models.Language;

@RepositoryRestResource
public interface LanguageRepository extends JpaRepository<Language, Long>{

}
