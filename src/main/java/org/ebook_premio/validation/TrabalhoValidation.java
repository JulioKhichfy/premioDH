package org.ebook_premio.validation;

import org.ebook_premio.models.Trabalho;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class TrabalhoValidation implements Validator {
	

	@Override
    public boolean supports(Class<?> clazz) {
        return Trabalho.class.isAssignableFrom(clazz);
    }

	@Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "titulo", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "descricao", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "dataLancamento", "field.required");

        Trabalho trabalho = (Trabalho) target;    
        if(trabalho.getPaginas() <= 0){
            errors.rejectValue("paginas", "field.required");
        }
    }
}
