package com.estore.admin.controller;

import com.estore.domain.Customer;
import com.estore.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("pager")
public class PagerController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("customer/page-count/{pageSize}")
    public Long pageCount(@PathVariable("pageSize") int size){
        return customerRepository.getPageCount(size);
    }

    @RequestMapping("customer/page")
    public List<Customer> getPage(@RequestParam("pageNo") int number,
                        @RequestParam("pageSize") int size){
        return customerRepository.getPage(number, size);
    }
}
