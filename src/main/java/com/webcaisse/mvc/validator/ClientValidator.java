package com.webcaisse.mvc.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.webcaisse.ws.model.ClientIn;

@Component
public class ClientValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return ClientIn.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nom","nom.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prenom","prenom.required");
		ValidationUtils.rejectIfEmpty(errors, "telephone", "telephone.required");
		ValidationUtils.rejectIfEmpty(errors, "numeroRue", "numeroRue.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nomRue","nomRue.required");
//		ClientIn client=  (ClientIn) target;
//		if (user.getCommunity().length == 0) {
//			errors.rejectValue("community", "community.required");
//		}
	}

}
