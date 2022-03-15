package com.estore.repositories;

import com.estore.domain.Category;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CategoryRepositoryImpl implements CategoryRepository{

    @Autowired
    private SessionFactory factory;

    @Override
    public Category findById(Integer id) {
        var session = factory.getCurrentSession();
        return session.find(Category.class, id);
    }

    @Override
    public List<Category> findAll() {
        var session = factory.getCurrentSession();
        var hql = "FROM Category";
        var query = session.createQuery(hql, Category.class);
        return query.getResultList();
    }

    @Override
    public Category create(Category entity) {
        var session = factory.getCurrentSession();
        session.save(entity);
        return entity;
    }

    @Override
    public void update(Category entity) {
        var session = factory.getCurrentSession();
        session.update(entity);
    }

    @Override
    public Category delete(Integer id) {
        var session = factory.getCurrentSession();
        var entity = session.find(Category.class, id);
        if (entity == null) {
            return null;
        }

        session.delete(entity);
        return entity;
    }
}
