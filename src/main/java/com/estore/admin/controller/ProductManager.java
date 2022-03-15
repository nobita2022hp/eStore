package com.estore.admin.controller;

import com.estore.domain.Product;
import com.estore.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("admin/product")
public class ProductManager {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ServletContext context;

    @RequestMapping("index")
    public String index(Model model) {
        var entity = new Product();
        model.addAttribute("entity", entity);
        model.addAttribute("list", repository.findAll());
        return "admin/product/index";
    }

    @RequestMapping("edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        var entity = repository.findById(id);
        model.addAttribute("entity", entity);
        model.addAttribute("list", repository.findAll());
        return "admin/product/index";
    }

    @RequestMapping("create")
    public String create(@ModelAttribute("entity") Product entity,
                         RedirectAttributes model,
                         @RequestParam("image_file") MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            entity.setImage("user.png");
        } else {
            entity.setImage(file.getOriginalFilename());
            var path = context.getRealPath("/static/images/products/" + entity.getImage());
            file.transferTo(new File(path));
        }

        repository.create(entity);
        model.addAttribute("message", "Add new OK");
        return "redirect:/admin/product/index";
    }

    @RequestMapping("update")
    public String update(@ModelAttribute("entity") Product entity,
                         RedirectAttributes model,
                         @RequestParam(name = "image_file", required = false) MultipartFile file) throws IOException {

        if (file != null && !file.isEmpty()) {
            entity.setImage(file.getOriginalFilename());
            var path = context.getRealPath("/static/images/products/" + entity.getImage());
            file.transferTo(new File(path));
        }

        repository.update(entity);
        model.addAttribute("message", "Update OK");
        return "redirect:/admin/product/edit/" + entity.getId();
    }

    @RequestMapping(value = {"delete", "/delete/{id}"})
    public String delete(@RequestParam(name = "id", required = false) Integer id1,
                         @PathVariable(name = "id", required = false) Integer id2,
                         RedirectAttributes model) {
        var id = id1 == null ? id2 : id1;
        repository.delete(id);
        model.addAttribute("message", "Delete OK");
        return "redirect:/admin/product/index";
    }
}
