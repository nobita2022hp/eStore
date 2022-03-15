package com.estore.admin.controller;

import com.estore.domain.Order;
import com.estore.repositories.OrderDetailRepository;
import com.estore.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("admin/order")
public class OrderManager {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @RequestMapping("index")
    public String index(Model model){
        var entity = new Order();
        model.addAttribute("entity", entity);
        model.addAttribute("details", orderDetailRepository.findByOrder(entity));
        model.addAttribute("list", repository.findAll());
        return "admin/order/index";
    }

    @RequestMapping("edit/{id}")
    public String edit(@PathVariable("id") Integer cId, Model model){
        var entity = repository.findById(cId);
        model.addAttribute("entity", entity);
        model.addAttribute("details", orderDetailRepository.findByOrder(entity));
        model.addAttribute("list", repository.findAll());
        return "admin/order/index";
    }

    @RequestMapping("create")
    public String create(@ModelAttribute("entity") Order order, RedirectAttributes model){
        repository.create(order);
        model.addAttribute("message", "Add new OK");
        return "redirect:/admin/order/index";
    }

    @RequestMapping("update")
    public String update(@ModelAttribute("entity") Order order, RedirectAttributes model){
        repository.update(order);
        model.addAttribute("message", "Update OK");
        return "redirect:/admin/order/edit/" + order.getId();
    }

    @RequestMapping(value = {"delete", "/delete/{id}"})
    public String delete(@RequestParam(name = "id", required = false) Integer id1,
                         @PathVariable(name = "id", required = false) Integer id2,
                         RedirectAttributes model){
        var id = id1 == null? id2 : id1;
        repository.delete(id);
        model.addAttribute("message", "Delete OK");
        return "redirect:/admin/order/index";
    }

}
