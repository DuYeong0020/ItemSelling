package com.dudu.itemselling.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter @Setter
public class SignUpDTO { // 회원가입

    @NotEmpty
    @Size(min = 10, max = 20)
    private String userId;

    @NotEmpty
    @Size(min = 3, max = 8)
    private String name;


    @NotEmpty
    @Size(min = 10, max = 20)
    private String password;

}
