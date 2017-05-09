package com.milos.neo4j.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.milos.neo4j.data.UserData;
import com.milos.neo4j.services.UserService;

@Component
public class EditUserProfileValidator implements Validator {
	@Autowired
	UserService userService;

	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	private static final String ID_PATTERN = "[0-9]+";

	@Override
	public boolean supports(Class<?> clazz) {
		return UserData.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserData userData = (UserData) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "register.label.firstname.error");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "register.label.lastname.error");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "register.label.age.error");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "register.label.password.error");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "register.label.email.error");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "register.label.city.error");

//		if (!(userData.getEmail() != null && userData.getEmail().isEmpty())) {
//			pattern = Pattern.compile(EMAIL_PATTERN);
//			matcher = pattern.matcher(userData.getEmail());
//			if (!matcher.matches()) {
//				errors.rejectValue("email", "register.label.email.error");
//			}
//		}
		if (!(userData.getAge() != null && userData.getAge().isEmpty())) {
			pattern = Pattern.compile(ID_PATTERN);
			matcher = pattern.matcher(userData.getAge());
			if (!matcher.matches()) {
				errors.rejectValue("age", "register.label.age.error");
			}
		}
		if (userData.getUserImage().getSize() != 0) {
			if (!userData.getUserImage().getContentType().equals("image/jpeg")
					&& !userData.getUserImage().getContentType().equals("image/png")) {
				errors.rejectValue("userImage", "registration.error.userImage");
			}
		}
                if (userData.getBase64Image()!=null && userData.getBase64Image().getBytes().length > 3000000) {
                    errors.rejectValue("base64Image", "register.label.base64Image.error");
                }
	}
}
