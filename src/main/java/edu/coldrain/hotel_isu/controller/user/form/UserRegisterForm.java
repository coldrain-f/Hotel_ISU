package edu.coldrain.hotel_isu.controller.user.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterForm {

    @NotBlank(message = "이름은 비어있으면 안 됩니다.")
    private String name;

    @NotBlank(message = "아이디는 비어있으면 안 됩니다.")
    private String loginId;

    @NotEmpty(message = "비밀번호는 비어있으면 안 됩니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 ~ 20자의 비밀번호여야 합니다.")
    private String password;

    @NotEmpty(message = "비밀번호 확인은 비어있으면 안 됩니다.")
    @Size(min = 8, max = 20, message = "비밀번호 확인은 8자 ~ 20자의 비밀번호여야 합니다.")
    private String passwordCheck;

    @NotBlank(message = "핸드폰 번호는 비어있으면 안 됩니다.")
    private String mobile;
}
