package com.estore.controller;

import com.estore.bean.MailInfo;
import com.estore.repositories.CategoryRepository;
import com.estore.repositories.ProductRepository;
import com.estore.service.CookieService;
import com.estore.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CookieService cookieService;

    @Autowired
    private MailService mailService;

    @RequestMapping("list-by-category/{cid}")
    public String listByCategory(@PathVariable("cid") Integer categoryId, Model model){
        var c = categoryRepository.findById(categoryId);
        var p = productRepository.findByCategoryId(categoryId);
        model.addAttribute("list", p);
        model.addAttribute("categoryName", c.getNameVN());
        return "product/list";
    }

    @RequestMapping("list-by-keywords")
    public String listByKeywords(@RequestParam String keywords, Model model){
        var p = productRepository.findByKeywords(keywords);
        model.addAttribute("list", p);
        model.addAttribute("keywords", keywords);
        return "product/list";
    }

    @RequestMapping("detail/{id}")
    public String detail(@PathVariable("id") Integer pId, Model model){
        var p = productRepository.findById(pId);

        p.setViewCount(p.getViewCount() + 1);
        productRepository.update(p);

        var list = productRepository.findByCategoryId(p.getCategory().getId());
        list.remove(p);
        model.addAttribute("prod", p);
        model.addAttribute("list", list);

        var cookie = cookieService.read("favo");
        if (cookie != null) {
            var ids = cookie.getValue();
            if (ids != null) {
                var favo_list = productRepository.findByIds(ids);
                model.addAttribute("favo_list", favo_list);
            }
        }

        var viewed = cookieService.read("viewed");
        var value = pId.toString();
        if (viewed != null) {
            value = viewed.getValue() + "," + pId;
        }

        cookieService.create("viewed", value, 10);
        var viewed_list = productRepository.findByIds(value);
        model.addAttribute("viewed_list", viewed_list);
        return "product/detail";
    }

    @RequestMapping("add-to-favo/{id}")
    @ResponseBody
    public boolean add2Favorite(@PathVariable("id") Integer pId){
        var c = cookieService.read("favo");
        var value = pId.toString();
        if (c != null) {
            value = c.getValue();
            if (value.contains(pId.toString()))
                return false;

            value += "," + pId;
        }

        cookieService.create("favo", value, 30);
        return true;
    }

    @RequestMapping("list-by-special/{id}")
    public String listBySpecial(Model model, @PathVariable Integer id){
        var p = productRepository.findBySpecial(id);
        model.addAttribute("list", p);
        return "product/list";
    }

    @RequestMapping("send-to-friend")
    @ResponseBody
    public boolean send2Friend(MailInfo info, HttpServletRequest request){
        var sub = "Thong tin hang hoa";
        info.setSubject(sub);
        try {
            var id = request.getParameter("id");
            var link = request.getRequestURL().toString()
                    .replace("send-to-friend", "detail/" + id);
            var body = String.format("%s <hr/> <a href='%s'>Xem chi tiet</a>"
                    , info.getBody(), link);
            info.setBody(body);
            mailService.send(info);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}
