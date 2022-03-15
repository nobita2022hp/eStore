package com.estore.controller;

import com.estore.domain.Customer;
import com.estore.domain.Order;
import com.estore.domain.OrderDetail;
import com.estore.repositories.OrderDetailRepository;
import com.estore.repositories.OrderRepository;
import com.estore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    private HttpSession session;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @GetMapping("checkout")
    public String showFormCheckout(@ModelAttribute("order") Order order) {
        var user = session.getAttribute("user");
        order.setOrderDate(new Date());
        order.setCustomer((Customer) user);
        order.setAmount(cartService.getAmount());
        return "order/checkout";
    }

    @PostMapping("checkout")
    public String checkout(@ModelAttribute("order") Order order) {
        var list = cartService.getItems();
        var orderDetails = list.stream().map(p -> {
            var orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(p);
            orderDetail.setUnitPrice(p.getUnitPrice());
            orderDetail.setQuantity(p.getQuantity());
            orderDetail.setDiscount(p.getDiscount());
            return orderDetail;
        }).collect(Collectors.toList());

        orderRepository.create(order, orderDetails);
        cartService.clear();
        return "redirect:/order/list";
    }

    @GetMapping("list")
    public String list(Model model) {
        var user = session.getAttribute("user");
        var listOrder = orderRepository.findByUser((Customer) user);
        model.addAttribute("orders", listOrder);
        return "order/list";
    }

    @GetMapping("detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer oId) {
        var order = orderRepository.findById(oId);
        var orderDetails = orderDetailRepository.findByOrder(order);
        model.addAttribute("order", order);
        model.addAttribute("details", orderDetails);
        return "order/detail";
    }

    @GetMapping("items")
    public String items(Model model) {
        var user = (Customer) session.getAttribute("user");
        var list = orderRepository.findItemsByUser(user);
        model.addAttribute("list", list);
        return "product/list";
    }
}
