package com.estore.admin.controller;

import com.estore.domain.Category;
import com.estore.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("admin/category")
public class CategoryManager {

    @Autowired
    private CategoryRepository repository;

    @RequestMapping("index")
    public String index(Model model){
        var entity = new Category();
        model.addAttribute("entity", entity);
        model.addAttribute("list", repository.findAll());
        return "admin/category/index";
    }

    @RequestMapping("edit/{id}")
    public String edit(@PathVariable("id") Integer cId, Model model){
        var entity = repository.findById(cId);
        model.addAttribute("entity", entity);
        model.addAttribute("list", repository.findAll());
        return "admin/category/index";
    }

    @RequestMapping("create")
    public String create(@ModelAttribute("entity") Category category, RedirectAttributes model){
        repository.create(category);
        model.addAttribute("message", "Add new OK");
        return "redirect:/admin/category/index";
    }

    @RequestMapping("update")
    public String update(@ModelAttribute("entity") Category category, RedirectAttributes model){
        repository.update(category);
        model.addAttribute("message", "Update OK");
        return "redirect:/admin/category/edit/" + category.getId();
    }

    @RequestMapping(value = {"delete", "/delete/{id}"})
    public String delete(@RequestParam(name = "id", required = false) Integer id1,
                         @PathVariable(name = "id", required = false) Integer id2,
                         RedirectAttributes model){
        var id = id1 == null? id2 : id1;
        repository.delete(id);
        model.addAttribute("message", "Delete OK");
        return "redirect:/admin/category/index";
    }

}
