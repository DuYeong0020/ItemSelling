package com.dudu.itemselling.repository;

import com.dudu.itemselling.domain.Item;
import com.dudu.itemselling.domain.User;

import java.util.List;

public interface UserRepository {

    public Long save(User user); // 유저를 저장

    public User findById(Long id); // id로 유저의 아이디 찾기

    public User findByUserId(String id);


}
