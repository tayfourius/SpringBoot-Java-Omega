package com.omega.system.orderservice.repository;

import com.omega.system.orderservice.model.OrderMysqlModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends JpaRepository<OrderMysqlModel, Integer> {
    Iterable<OrderMysqlModel> findByRestaurantId(int id);

    Iterable<OrderMysqlModel> findByUserId(int id);
}
