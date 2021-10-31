package com.dudu.itemselling.repository;

import com.dudu.itemselling.domain.Item;
import com.dudu.itemselling.domain.User;
import com.dudu.itemselling.dto.ItemDTO;
import com.dudu.itemselling.dto.ItemsListDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ItemMapRepository implements ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); //static
    private static long sequence = 0L; //static

    public Item save(Item item, User user) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public ItemDTO findById(Long id) {
        // return store.get(id);
        return null;
    }

    @Override
    public Item findByItemId(Long id) {
        return null;
    }

    public List<ItemsListDTO> findAll() {
        return null;
        //return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam) {
        // Item findItem = findById(itemId);
//        findItem.setItemName(updateParam.getItemName());
//        findItem.setPrice(updateParam.getPrice());
//        findItem.setQuantity(updateParam.getQuantity());
//        findItem.setSold(updateParam.getSold());
//        findItem.setRegions(updateParam.getRegions());
//        findItem.setItemType(updateParam.getItemType());
    }


    public void clearStore() {

    }


}
