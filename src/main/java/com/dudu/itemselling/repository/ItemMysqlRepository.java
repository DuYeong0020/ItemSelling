package com.dudu.itemselling.repository;

import com.dudu.itemselling.domain.Item;
import com.dudu.itemselling.domain.User;
import com.dudu.itemselling.dto.ItemDTO;
import com.dudu.itemselling.dto.ItemsListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
@RequiredArgsConstructor
public class ItemMysqlRepository implements ItemRepository{

    @PersistenceContext
    private final EntityManager em;


    @Override
    public Item save(Item item, User user) {
        item.changeUser(user);
        em.persist(item);
        return item;
    }

    @Override // 아이템 아이디로 찾기
    public ItemDTO findById(Long id) {
        return em.createQuery("select new com.dudu.itemselling.dto.ItemDTO(i.id, u.name, u.id, i.itemName, i.price, i.quantity, i.sold, i.regions, i.itemType)" +
                " from Item i inner join i.user u on i.id=:itemId", ItemDTO.class).setParameter("itemId", id).getResultList().get(0);
    }

    @Override
    public Item findByItemId(Long id) {
        return em.find(Item.class, id);
    }

    @Override
    public List<ItemsListDTO> findAll() {
        return em.createQuery("select new com.dudu.itemselling.dto.ItemsListDTO(i.id, u.name, i.itemName, i.price, i.quantity, i.sold)" +
                " from Item i inner join i.user u", ItemsListDTO.class).getResultList();
    }




//    @Override
//    public void update(Long itemId, Item updateParam, Long userId) {
//
//    }

}
