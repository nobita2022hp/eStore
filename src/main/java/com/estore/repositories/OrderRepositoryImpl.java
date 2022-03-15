package com.estore.repositories;

import com.estore.domain.Customer;
import com.estore.domain.Order;
import com.estore.domain.OrderDetail;
import com.estore.domain.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    private SessionFactory factory;

    @Override
    public Order findById(Integer id) {
        var session = factory.getCurrentSession();
        return session.find(Order.class, id);
    }

    @Override
    public List<Order> findAll() {
        var session = factory.getCurrentSession();
        var hql = "FROM Order";
        var query = session.createQuery(hql, Order.class);
        return query.getResultList();
    }

    @Override
    public Order create(Order entity) {
        var session = factory.getCurrentSession();
        session.save(entity);
        return entity;
    }

    @Override
    public void update(Order entity) {
        var session = factory.getCurrentSession();
        session.update(entity);
    }

    @Override
    public Order delete(Integer id) {
        var session = factory.getCurrentSession();
        var entity = session.find(Order.class, id);
        if (entity == null) {
            return null;
        }

        session.delete(entity);
        return entity;
    }

    @Override
    public void create(Order order, List<OrderDetail> orderDetails) {
        var session = factory.getCurrentSession();
        order.setOrderDetails(orderDetails);
        session.save(order);
        orderDetails.forEach(session::save);
    }

    @Override
    public List<Order> findByUser(Customer user) {
        var session = factory.getCurrentSession();
        var hql = "FROM Order o WHERE o.customer.id =: uid ORDER BY o.orderDate DESC";
        var query = session.createQuery(hql, Order.class);
        query.setParameter("uid", user.getId());
        return query.getResultList();
    }

    @Override
    public List<Product> findItemsByUser(Customer user) {
        var session = factory.getCurrentSession();
        var hql = "SELECT DISTINCT d.product " +
                "FROM OrderDetail d " +
                "WHERE d.order.customer.id =: uid";
        var query = session.createQuery(hql, Product.class);
        query.setParameter("uid", user.getId());
        return query.getResultList();
    }
}
