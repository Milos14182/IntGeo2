package com.milos.neo4j.validators;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.milos.neo4j.data.ContactData;

@Component
public class ContactValidator implements Validator {
	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	@Override
	public boolean supports(Class<?> clazz) {
		return ContactData.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ContactData contactData = (ContactData) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "register.label.firstname.error");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "register.label.lastname.error");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "register.label.age.error");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "register.label.email.error");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message", "register.label.message.error");
		
		if (!(contactData.getEmail() != null && contactData.getEmail().isEmpty())) {
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(contactData.getEmail());
			if (!matcher.matches()) {
				errors.rejectValue("email", "register.label.email.error");
			}
		}
	}
	
}
