package com.university.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.metamodel.EntityType;

import javax.persistence.EntityManager;


@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private final EntityManager entityManager;

    public MyDataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        @SuppressWarnings("rawtypes")
		List<Class> entityClasses = new ArrayList<>();
        for (@SuppressWarnings("rawtypes") EntityType tempEntityType : entities) {
            entityClasses.add(tempEntityType.getJavaType());
        }
        @SuppressWarnings("rawtypes")
		Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
	