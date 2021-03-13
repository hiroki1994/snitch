package com.example.demo;


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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.login.domain.model.SearchForm;

@SpringBootTest
@AutoConfigureMockMvc
public class SearchFormTest {

	private Validator validator;

	@BeforeEach
	public void init() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void 検索キーワード情報投入取得() throws Exception {

		SearchForm searchForm = new SearchForm();

		searchForm.setKeyword("チョコ");

		String actualKeyword = searchForm.getKeyword();

		assertEquals("チョコ", actualKeyword);
	}

	@Test
	public void 検索キーワード情報投入_バリデーションエラー() throws Exception {

		SearchForm searchForm = new SearchForm();

		searchForm.setKeyword("");

		Set<ConstraintViolation<SearchForm>> constraintValidation = validator.validate(searchForm);

		assertThat(constraintValidation.size(), is(1));

		ConstraintViolation<SearchForm> violation = constraintValidation.iterator().next();

		assertThat(violation.getInvalidValue(), is(""));
	}
}
