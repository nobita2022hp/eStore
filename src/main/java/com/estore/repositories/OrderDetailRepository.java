package com.estore.repositories;

import com.estore.domain.Order;
import com.estore.domain.OrderDetail;

import java.util.List;

public interface OrderDetailRepository {
    OrderDetail findById(Integer id);
    List<OrderDetail> findAll();
    OrderDetail create(OrderDetail entity);
    void update(OrderDetail entity);
    OrderDetail delete(Integer id);

    List<OrderDetail> findByOrder(Order order);
}
