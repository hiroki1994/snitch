package com.example.demo.domain.model.searchform;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SearchForm {

    @NotBlank
    private String keyword;
}