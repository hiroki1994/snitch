package com.snitch.unittest.domain.model.searchform;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.snitch.domain.model.searchform.SearchForm;

@SpringBootTest
public class SearchFormTest {

    private Validator validator;

    @BeforeEach
    public void setup() {
	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	validator = factory.getValidator();
    }

    @Test
    public void setSearchGift_empty() throws Exception {

	SearchForm searchForm = new SearchForm();
	searchForm.setKeyword("チョコ");

	String actualKeyword = searchForm.getKeyword();

	assertEquals("チョコ", actualKeyword);

	Set<ConstraintViolation<SearchForm>> constraintValidation = validator.validate(searchForm);

	assertThat(constraintValidation, is(empty()));
    }

    @Test
    public void searchGift_validationError() throws Exception {

	SearchForm searchForm = new SearchForm();
	searchForm.setKeyword("");

	Set<ConstraintViolation<SearchForm>> constraintValidation = validator.validate(searchForm);

	assertThat(constraintValidation.size(), is(1));

	ConstraintViolation<SearchForm> violation = constraintValidation.iterator().next();

	assertThat(violation.getInvalidValue(), is(""));
    }
}