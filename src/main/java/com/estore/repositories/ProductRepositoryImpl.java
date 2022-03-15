package com.estore.repositories;

import com.estore.domain.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepository{

    @Autowired
    private SessionFactory factory;

    @Override
    public Product findById(Integer id) {
        var session = factory.getCurrentSession();
        return session.find(Product.class, id);
    }

    @Override
    public List<Product> findAll() {
        var session = factory.getCurrentSession();
        var hql = "FROM Product";
        var query = session.createQuery(hql, Product.class);
        return query.getResultList();
    }

    @Override
    public Product create(Product entity) {
        var session = factory.getCurrentSession();
        session.save(entity);
        return entity;
    }

    @Override
    public void update(Product entity) {
        var session = factory.getCurrentSession();
        session.update(entity);
    }

    @Override
    public Product delete(Integer id) {
        var session = factory.getCurrentSession();
        var entity = session.find(Product.class, id);
        if (entity == null) {
            return null;
        }

        session.delete(entity);
        return entity;
    }

    @Override
    public List<Product> findByCategoryId(Integer categoryId) {
        var session = factory.getCurrentSession();
        var hql = "FROM Product p WHERE p.category.id =:cid";
        var query = session.createQuery(hql, Product.class);
        query.setParameter("cid", categoryId);
        return query.getResultList();
    }

    @Override
    public List<Product> findByKeywords(String keywords) {
        var session = factory.getCurrentSession();
        var hql = "FROM Product p WHERE p.name LIKE :kw OR" +
                " p.category.name LIKE :kw OR" +
                " p.category.nameVN LIKE :kw";
        var query = session.createQuery(hql, Product.class);
        query.setParameter("kw", "%" + keywords + "%");
        return query.getResultList();
    }

    @Override
    public List<Product> findByIds(String ids) {
        var session = factory.getCurrentSession();
        var hql = "FROM Product p WHERE p.id IN (" + ids + ")";
        var query = session.createQuery(hql, Product.class);
        return query.getResultList();
    }

    @Override
    public List<Product> findBySpecial(Integer id) {
        var session = factory.getCurrentSession();
        var hql = "FROM Product p";
        switch (id){
            case 0:
                hql = "FROM Product p ORDER BY p.productDate DESC";
                break;

            case 1:
                hql = "FROM Product p ORDER BY size(p.orderDetails) DESC";
                break;

            case 2:
                hql = "FROM Product p ORDER BY p.viewCount DESC";
                break;

            case 3:
                hql = "FROM Product p ORDER BY p.discount DESC";
                break;
        }

        var query = session.createQuery(hql, Product.class);
        query.setMaxResults(12);
        return query.getResultList();
    }
}
