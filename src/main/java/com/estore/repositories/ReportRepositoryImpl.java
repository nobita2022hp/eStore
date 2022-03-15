package com.estore.repositories;

import com.estore.domain.Order;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ReportRepositoryImpl implements ReportRepository{

    @Autowired
    private SessionFactory factory;

    @Override
    public List<Object[]> inventory() {
        var hql = "SELECT p.category.nameVN, " +
                "SUM(p.quantity), " +
                "SUM(p.unitPrice*p.quantity), " +
                "MIN(p.unitPrice), " +
                "MAX(p.unitPrice), " +
                "AVG(p.unitPrice) " +
                "FROM Product p " +
                "GROUP BY p.category.nameVN";

        return getData(hql);
    }

    private List<Object[]> getData(String hql){
        var session = factory.getCurrentSession();
        var query = session.createQuery(hql, Object[].class);
        return query.getResultList();
    }

    @Override
    public List<Object[]> revenueByCategory() {
        var hql = "SELECT d.product.category.nameVN, " +
                "SUM(d.quantity), " +
                "SUM(d.unitPrice * d.quantity), " +
                "MIN(d.unitPrice), " +
                "MAX(d.unitPrice), " +
                "AVG(d.unitPrice) " +
                "FROM OrderDetail d " +
                "GROUP BY d.product.category.nameVN";

        return getData(hql);
    }

    @Override
    public List<Object[]> revenueByCustomer() {
        var hql = "SELECT d.order.customer.id, " +
                "SUM(d.quantity), " +
                "SUM(d.unitPrice * d.quantity), " +
                "MIN(d.unitPrice), " +
                "MAX(d.unitPrice), " +
                "AVG(d.unitPrice) " +
                "FROM OrderDetail d " +
                "GROUP BY d.order.customer.id";

        return getData(hql);
    }

    @Override
    public List<Object[]> revenueByYear() {
        var hql = "SELECT YEAR(d.order.orderDate), " +
                "SUM(d.quantity), " +
                "SUM(d.unitPrice * d.quantity), " +
                "MIN(d.unitPrice), " +
                "MAX(d.unitPrice), " +
                "AVG(d.unitPrice) " +
                "FROM OrderDetail d " +
                "GROUP BY YEAR(d.order.orderDate) " +
                "ORDER BY YEAR(d.order.orderDate) DESC";

        return getData(hql);
    }

    @Override
    public List<Object[]> revenueByQuarter() {
        var hql = "SELECT CEILING(MONTH(d.order.orderDate) / 3.0), " +
                "SUM(d.quantity), " +
                "SUM(d.unitPrice * d.quantity), " +
                "MIN(d.unitPrice), " +
                "MAX(d.unitPrice), " +
                "AVG(d.unitPrice) " +
                "FROM OrderDetail d " +
                "GROUP BY CEILING(MONTH(d.order.orderDate) / 3.0) " +
                "ORDER BY CEILING(MONTH(d.order.orderDate) / 3.0)";

        return getData(hql);
    }

    @Override
    public List<Object[]> revenueByMonth() {
        var hql = "SELECT MONTH(d.order.orderDate), " +
                "SUM(d.quantity), " +
                "SUM(d.unitPrice * d.quantity), " +
                "MIN(d.unitPrice), " +
                "MAX(d.unitPrice), " +
                "AVG(d.unitPrice) " +
                "FROM OrderDetail d " +
                "GROUP BY MONTH(d.order.orderDate) " +
                "ORDER BY MONTH(d.order.orderDate) DESC";

        return getData(hql);
    }
}
