package com.dudu.itemselling.repository;

import com.dudu.itemselling.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserMysqlRepository implements UserRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Long save(User user) {
        em.persist(user);

        return user.getId();
    }

    @Override
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User findByUserId(String id) {
        List<User> findUser = em.createQuery("select u from User u where u.userId = :id", User.class)
                .setParameter("id", id)
                .getResultList();

        if(findUser.isEmpty()){ // 비어있으면 null 반환
            return null;
        } else{
            return findUser.get(0);
        }
    }


}
