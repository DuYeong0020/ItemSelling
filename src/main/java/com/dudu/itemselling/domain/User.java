package com.dudu.itemselling.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    private String name; // 유니크 제약 조건 !!!!!

    private String password;

    private LocalDate create_date; // 가입 일자

    @OneToMany(mappedBy = "user")
    private List<Item> items = new ArrayList<>();



}
