/*package com.university.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.university.models.Admission;
import com.university.models.Contact;
import com.university.models.Diploma;
import com.university.models.Document;
import com.university.models.Employe;
import com.university.models.Department;
import com.university.models.Student;

@Configuration
public class MyDataRestConfig  implements RepositoryRestConfigurer{

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
		
		HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PATCH};
		
		// disable HTTP methods for Admission: PUT, POST, DELETE and PATCH
        config.getExposureConfiguration()
                .forDomainType(Admission.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));

        // disable HTTP methods for Contact: PUT, POST, DELETE and PATCH
        config.getExposureConfiguration()
                .forDomainType(Contact.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
        
     // disable HTTP methods for Diploma: PUT, POST, DELETE and PATCH
        config.getExposureConfiguration()
                .forDomainType(Diploma.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));

        // disable HTTP methods for Document: PUT, POST, DELETE and PATCH
        config.getExposureConfiguration()
                .forDomainType(Document.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));

     // disable HTTP methods for Employee: PUT, POST, DELETE and PATCH
        config.getExposureConfiguration()
                .forDomainType(Employe.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));

        // disable HTTP methods for Department: PUT, POST, DELETE and PATCH
        config.getExposureConfiguration()
                .forDomainType(Department.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));

     // disable HTTP methods for Student: PUT, POST, DELETE and PATCH
        config.getExposureConfiguration()
                .forDomainType(Student.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));

	}
	
	
}*/