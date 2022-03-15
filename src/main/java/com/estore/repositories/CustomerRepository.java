package com.estore.repositories;

import com.estore.domain.Customer;

import java.util.List;

public interface CustomerRepository {
    Customer findById(String id);
    List<Customer> findAll();
    Customer create(Customer entity);
    void update(Customer entity);
    Customer delete(String id);

    Long getPageCount(int pageSize);

    List<Customer> getPage(int number, int size);
}
