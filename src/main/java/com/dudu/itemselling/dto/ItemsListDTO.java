package com.dudu.itemselling.dto;

import com.dudu.itemselling.domain.ItemType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter @Setter @AllArgsConstructor
public class ItemsListDTO {

    private Long id; // 아이템 아이디

    private String userName; // 판매자

    private String itemName; // 아이템 이름

    private Integer price; // 가격

    private Integer quantity; // 수량

    private Boolean sold; // 판매 여부



}
