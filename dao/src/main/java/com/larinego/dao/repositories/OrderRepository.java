package com.larinego.dao.repositories;


import com.larinego.dao.entities.User;
import com.larinego.dao.entities.UserOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<UserOrder, Long> {

    Page<UserOrder> findByUser(User user, Pageable pageable);
}
