package com.estore.repositories;

import java.util.List;

public interface ReportRepository {
    List<Object[]> inventory();
    List<Object[]> revenueByCategory();
    List<Object[]> revenueByCustomer();
    List<Object[]> revenueByYear();
    List<Object[]> revenueByQuarter();
    List<Object[]> revenueByMonth();
}
