package com.estore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("home")
public class HomeController {

    @RequestMapping("index")
    public String index(){
        return "home/index";
    }

    @RequestMapping("about")
    public String about(){
        return "home/about";
    }

    @RequestMapping("language")
    @ResponseBody
    public void language(){
    }

    @RequestMapping("contact")
    public String contact(){
        return "home/contact";
    }

    @RequestMapping("faq")
    public String faq(){
        return "home/faq";
    }

    @RequestMapping("feedback")
    public String feedback(){
        return "home/feedback";
    }
}
