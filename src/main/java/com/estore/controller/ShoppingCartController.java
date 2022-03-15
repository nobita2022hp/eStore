package com.estore.controller;

import com.estore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("cart")
public class ShoppingCartController {

    @Autowired
    private CartService cartService;

    @RequestMapping("update/{id}/{quantity}")
    @ResponseBody
    public Object[] update(@PathVariable("id") Integer id,
                           @PathVariable("quantity") int qty){
        cartService.update(id, qty);
        return new Object[]{cartService.getCount(), cartService.getAmount()};
    }

    @RequestMapping("add/{id}")
    @ResponseBody
    public Object[] add(@PathVariable("id") Integer id){
        cartService.add(id);
        return new Object[]{cartService.getCount(), cartService.getAmount()};
    }

    @RequestMapping("view")
    public String view(){
        return "cart/view";
    }

    @RequestMapping("clear")
    @ResponseBody
    public void clear(){
        cartService.clear();
    }

    @RequestMapping("remove/{id}")
    @ResponseBody
    public Object[] remove(@PathVariable("id") Integer id){
        cartService.remove(id);
        return new Object[]{cartService.getCount(), cartService.getAmount()};
    }
}
