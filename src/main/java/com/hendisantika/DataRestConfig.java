package com.hendisantika;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

import com.hendisantika.entity.Personne;

public class DataRestConfig implements RepositoryRestConfigurer {
	private EntityManager entityManager;

	@Autowired
	public DataRestConfig(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		HttpMethod[] unsupportedActions = { HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE };

		config.getExposureConfiguration().forDomainType(Personne.class)
				.withItemExposure((metadata, httpdMethods) -> httpdMethods.disable(unsupportedActions))
				.withCollectionExposure((metadata, httpdMethods) -> httpdMethods.disable(unsupportedActions));

		// call an internal helper method
		// exposeIds(config);

		config.exposeIdsFor(entityManager.getMetamodel().getEntities().stream().map(e -> e.getJavaType())
				.collect(Collectors.toList()).toArray(Class[]::new));
		// config.exposeIdsFor(AbstractModel.class);
		// config.exposeIdsFor(Personne.class);
	}

	private void exposeIds(RepositoryRestConfiguration config) {

		// expose entity ids
		//

		// - get a list of all entity classes from the entity manager
		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

		// - create an array of the entity types
		List<Class> entityClasses = new ArrayList<>();

		// - get the entity types for the entities
		for (EntityType tempEntityType : entities) {
			entityClasses.add(tempEntityType.getJavaType());
		}

		// - expose the entity ids for the array of entity/domain types
		Class[] domainTypes = entityClasses.toArray(new Class[0]);
		config.exposeIdsFor(domainTypes);
	}
}
