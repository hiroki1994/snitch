package com.snitch.domain.model.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.snitch.domain.validation.UniqueUserName;
import com.snitch.domain.validation.ValidGroup1;
import com.snitch.domain.validation.ValidGroup2;

import lombok.Data;

@Data
public class UserForm {

    @NotBlank(groups = ValidGroup1.class)
    @UniqueUserName(groups = ValidGroup1.class)
    @Length(min = 3, max = 20, groups = ValidGroup2.class)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup2.class)
    private String userName;

    @NotBlank(groups = ValidGroup1.class)
    @Email(groups = ValidGroup2.class)
    private String mailAddress;

    @NotBlank(groups = ValidGroup1.class)
    @Length(min = 3, max = 20, groups = ValidGroup2.class)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup2.class)
    private String password;
}
