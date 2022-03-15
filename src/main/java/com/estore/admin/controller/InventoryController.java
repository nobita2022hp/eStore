package com.estore.admin.controller;

import com.estore.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class InventoryController {

    @Autowired
    private ReportRepository repository;

    @GetMapping("inventory/index")
    public String index(Model model){
        model.addAttribute("data", repository.inventory());
        return "admin/report/inventory";
    }

    @GetMapping("revenue/category")
    public String revenueByCategory(Model model){
        model.addAttribute("data", repository.revenueByCategory());
        return "admin/report/revenue-by-category";
    }

    @GetMapping("revenue/customer")
    public String revenueByCustomer(Model model){
        model.addAttribute("data", repository.revenueByCustomer());
        return "admin/report/revenue-by-customer";
    }

    @GetMapping("revenue/year")
    public String revenueByYear(Model model){
        model.addAttribute("data", repository.revenueByYear());
        return "admin/report/revenue-by-year";
    }

    @GetMapping("revenue/quarter")
    public String revenueByQuarter(Model model){
        model.addAttribute("data", repository.revenueByQuarter());
        return "admin/report/revenue-by-quarter";
    }

    @GetMapping("revenue/month")
    public String revenueByMonth(Model model){
        model.addAttribute("data", repository.revenueByMonth());
        return "admin/report/revenue-by-month";
    }
}
