package com.dudu.itemselling.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "Item")
@Entity
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 아이템 아이디

    @Column(name = "item_name")
    private String itemName; // 아이템 이름

    // 유저
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 유저 FK

    private Integer price; // 가격

    private Integer quantity; // 수량

    private Boolean sold; // 판매 여부

    private String regions; // 등록 지역

    @Column(name = "item_type")
    private ItemType itemType; // 상품 종류

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
    public void changeUser(User user){
        this.user = user;
        user.getItems().add(this);
    }
}
