package com.estore.service;

import com.estore.domain.Product;
import com.estore.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service // named in session: scopedTarget.cartService
@SessionScope
public class CartService {

    @Autowired
    private ProductRepository repository;

    private final Map<Integer, Product> cart = new HashMap<>();

    public void add(Integer productId){
        var product = cart.get(productId);
        if (product == null) {
            product = repository.findById(productId);
            product.setQuantity(1);
            cart.put(productId, product);
        }else {
            product.setQuantity(product.getQuantity() + 1);
        }
    }

    public void remove(Integer productId){
        cart.remove(productId);
    }

    public void update(Integer productId, int newQuantity){
        var p = cart.get(productId);
        p.setQuantity(newQuantity);
    }

    public void clear(){
        cart.clear();
    }

    public int getCount(){
        var ps = getItems();
        var count = new AtomicInteger();
        ps.forEach(p -> count.addAndGet(p.getQuantity()));
        return count.get();
    }

    public double getAmount(){
        var ps = getItems();
        var amount = 0;
        for (var p: ps) {
            amount += p.getQuantity() * p.getUnitPrice() * (1 - p.getDiscount());
        }

        return amount;
    }

    public Collection<Product> getItems(){
        return cart.values();
    }
}
