package com.estore.admin.controller;

import com.estore.domain.Customer;
import com.estore.repositories.CustomerRepository;
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
@RequestMapping("admin/customer")
public class CustomerManager {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private ServletContext context;

    @RequestMapping("index")
    public String index(Model model){
        var entity = new Customer();
        model.addAttribute("entity", entity);
        model.addAttribute("list", repository.findAll());
        return "admin/customer/index";
    }

    @RequestMapping("edit/{id}")
    public String edit(@PathVariable("id") String id, Model model){
        var entity = repository.findById(id);
        model.addAttribute("entity", entity);
        model.addAttribute("list", repository.findAll());
        return "admin/customer/index";
    }

    @RequestMapping("create")
    public String create(@ModelAttribute("entity") Customer entity,
                         RedirectAttributes model,
                         @RequestParam("photo_file") MultipartFile file) throws IOException {

        if (file.isEmpty()){
            entity.setPhoto("user.png");
        }else {
            entity.setPhoto(file.getOriginalFilename());
            var path = context.getRealPath("/static/images/customers/" + entity.getPhoto());
            file.transferTo(new File(path));
        }

        repository.create(entity);
        model.addAttribute("message", "Add new OK");
        return "redirect:/admin/customer/index";
    }

    @RequestMapping("update")
    public String update(@ModelAttribute("entity") Customer entity,
                         RedirectAttributes model,
                         @RequestParam("photo_file") MultipartFile file) throws IOException {

        if (!file.isEmpty()){
            entity.setPhoto(file.getOriginalFilename());
            var path = context.getRealPath("/static/images/customers/" + entity.getPhoto());
            file.transferTo(new File(path));
        }

        repository.update(entity);
        model.addAttribute("message", "Update OK");
        return "redirect:/admin/customer/edit/" + entity.getId();
    }

    @RequestMapping(value = {"delete", "/delete/{id}"})
    public String delete(@RequestParam(name = "id", required = false) String id1,
                         @PathVariable(name = "id", required = false) String id2,
                         RedirectAttributes model){
        var id = id1 == null? id2 : id1;
        repository.delete(id);
        model.addAttribute("message", "Delete OK");
        return "redirect:/admin/customer/index";
    }
}
