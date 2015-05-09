package org.tahsan.web.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.metamodel.relational.Constraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.tahsan.web.repository.UserRepository;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String>{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public void initialize(UniqueUsername constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		
		if(userRepository == null) {
			
			return true;
		}
		return userRepository.findByName(username) == null;
	}

}
