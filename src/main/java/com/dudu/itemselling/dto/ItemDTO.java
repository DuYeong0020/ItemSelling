package com.dudu.itemselling.dto;

import com.dudu.itemselling.domain.ItemType;
import com.dudu.itemselling.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class ItemDTO {


    private Long id; // 아이템 아이디

    private String userName; //유저 이름

    private Long userId; // 유저 아이디

    @NotEmpty
    @Size(min = 2, max = 10)
    private String itemName; // 아이템 이름

    @NotNull
    @Range(min = 1000, max = 1000000)
    private Integer price; // 가격

    @NotNull
    @Range(min = 1, max = 1000)
    private Integer quantity; // 수량

    @NotNull
    private Boolean sold; // 판매 여부

    @NotEmpty
    private String regions; // 등록 지역

    @NotNull
    private ItemType itemType; // 상품 종류

}
