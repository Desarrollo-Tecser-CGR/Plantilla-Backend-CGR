package com.cgr.bbp.infrastructure.utilities.Anotations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
 
@Component
public class ExistsFieldValidator implements ConstraintValidator<ExistsField, String>  {
    @PersistenceContext
	private EntityManager entityManager;
 
	private Class<?> entityClass;
	private String fieldName;
 
	@Override
	public void initialize(ExistsField constraintAnnotation) {
		this.entityClass = constraintAnnotation.entityClass();
		this.fieldName = constraintAnnotation.fieldName();
	}
 
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if (value == null || entityManager == null) {
			return true;
		}
 
		String queryStr = String.format("SELECT COUNT(e) FROM %s e WHERE e.%s = :value", entityClass.getSimpleName(),
				fieldName);
		Query query = entityManager.createQuery(queryStr);
		query.setParameter("value", value);
 
		Long count = (Long) query.getSingleResult();
		return count == 0;
	}
}
