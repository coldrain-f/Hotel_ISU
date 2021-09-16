package edu.coldrain.hotel_isu.domain.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class User {

    /**
     * @NotNull null 을 허용하지 않는다.
     * @NotEmpty "", null 을 허용하지 않음
     * @NotBlank "", " ", null 을 허용허지 않음
     */

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String loginId;

    @NotEmpty
    private String password;

    @NotBlank
    private String mobile;

    public User() { }

    public User(Long id, String name, String loginId, String password, String mobile) {
        this.id = id;
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.mobile = mobile;
    }

    public User(String name, String loginId, String password, String mobile) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.mobile = mobile;
    }
}
