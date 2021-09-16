package edu.coldrain.hotel_isu.controller.user.form;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserLoginForm {

    @NotBlank(message = "아이디는 비어있으면 안 됩니다.")
    private String loginId;

    @NotBlank(message = "비밀번호는 비어있으면 안 됩니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 ~ 20자의 비밀번호여야 합니다.")
    private String password;
}