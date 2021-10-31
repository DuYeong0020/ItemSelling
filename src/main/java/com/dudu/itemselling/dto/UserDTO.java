package com.dudu.itemselling.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter @Setter @AllArgsConstructor @ToString
public class UserDTO {

    private Long id;

    private String name;

}
