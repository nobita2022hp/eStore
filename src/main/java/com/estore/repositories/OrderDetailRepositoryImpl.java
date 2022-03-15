package com.estore.repositories;

import com.estore.domain.Order;
import com.estore.domain.OrderDetail;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class OrderDetailRepositoryImpl implements OrderDetailRepository{

    @Autowired
    private SessionFactory factory;

    @Override
    public OrderDetail findById(Integer id) {
        var session = factory.getCurrentSession();
        return session.find(OrderDetail.class, id);
    }

    @Override
    public List<OrderDetail> findAll() {
        var session = factory.getCurrentSession();
        var hql = "FROM OrderDetail";
        var query = session.createQuery(hql, OrderDetail.class);
        return query.getResultList();
    }

    @Override
    public OrderDetail create(OrderDetail entity) {
        var session = factory.getCurrentSession();
        session.save(entity);
        return entity;
    }

    @Override
    public void update(OrderDetail entity) {
        var session = factory.getCurrentSession();
        session.update(entity);
    }

    @Override
    public OrderDetail delete(Integer id) {
        var session = factory.getCurrentSession();
        var entity = session.find(OrderDetail.class, id);
        if (entity == null) {
            return null;
        }

        session.delete(entity);
        return entity;
    }

    @Override
    public List<OrderDetail> findByOrder(Order order) {
        var session = factory.getCurrentSession();
        var hql = "FROM OrderDetail d where d.order.id =: oid";
        var query = session.createQuery(hql, OrderDetail.class);
        query.setParameter("oid", order.getId());
        return query.getResultList();
    }
}
