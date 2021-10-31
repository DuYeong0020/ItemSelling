package com.dudu.itemselling.repository;

import com.dudu.itemselling.domain.Item;
import com.dudu.itemselling.domain.User;
import com.dudu.itemselling.dto.ItemDTO;
import com.dudu.itemselling.dto.ItemsListDTO;

import java.util.ArrayList;
import java.util.List;

public interface ItemRepository {
    public Item save(Item item, User user);

    public ItemDTO findById(Long id);

    public Item findByItemId(Long id);

    public List<ItemsListDTO> findAll();


//    public void update(Long itemId, Item updateParam);

}
