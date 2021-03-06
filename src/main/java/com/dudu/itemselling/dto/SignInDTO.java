package com.dudu.itemselling.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter @Setter
public class SignInDTO { // 로그인

    @NotEmpty
    @Size(min = 10, max = 20)
    private String userId; // 유저 아이디

    @NotEmpty
    @Size(min = 10, max = 20)
    private String password; // 비밀번호
}
