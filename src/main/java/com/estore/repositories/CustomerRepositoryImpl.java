package com.estore.repositories;

import com.estore.domain.Customer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CustomerRepositoryImpl implements CustomerRepository{

    @Autowired
    private SessionFactory factory;

    @Override
    public Customer findById(String id) {
        var session = factory.getCurrentSession();
        return session.find(Customer.class, id);
    }

    @Override
    public List<Customer> findAll() {
        var session = factory.getCurrentSession();
        var hql = "FROM Customer";
        var query = session.createQuery(hql, Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer create(Customer entity) {
        var session = factory.getCurrentSession();
        session.save(entity);
        return entity;
    }

    @Override
    public void update(Customer entity) {
        var session = factory.getCurrentSession();
        session.update(entity);
    }

    @Override
    public Customer delete(String id) {
        var session = factory.getCurrentSession();
        var entity = session.find(Customer.class, id);
        if (entity == null) {
            return null;
        }

        session.delete(entity);
        return entity;
    }

    @Override
    public Long getPageCount(int pageSize) {
        var session = factory.getCurrentSession();
        var hql = "SELECT count(c) FROM Customer c";
        var query = session.createQuery(hql, Long.class);
        var rowCount = query.getSingleResult();
        return (long) Math.ceil(1.0 * rowCount / pageSize);
    }

    @Override
    public List<Customer> getPage(int number, int size) {
        var session = factory.getCurrentSession();
        var hql = "FROM Customer";
        var query = session.createQuery(hql, Customer.class);
        query.setFirstResult(number * size);
        query.setMaxResults(size);
        return query.getResultList();
    }
}
