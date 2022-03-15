package com.estore.repositories;

import com.estore.domain.Customer;
import com.estore.domain.Order;
import com.estore.domain.OrderDetail;
import com.estore.domain.Product;

import java.util.List;

public interface OrderRepository {
    Order findById(Integer id);
    List<Order> findAll();
    Order create(Order entity);
    void update(Order entity);
    Order delete(Integer id);

    void create(Order order, List<OrderDetail> orderDetails);

    List<Order> findByUser(Customer user);

    List<Product> findItemsByUser(Customer user);
}
