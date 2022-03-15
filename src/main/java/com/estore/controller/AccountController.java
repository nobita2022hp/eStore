package com.estore.controller;

import com.estore.bean.MailInfo;
import com.estore.domain.Customer;
import com.estore.repositories.CustomerRepository;
import com.estore.service.CookieService;
import com.estore.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("account")
public class AccountController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private HttpSession session;

    @Autowired
    private ServletContext context;

    @Autowired
    private CookieService cookieService;

    @Autowired
    private MailService mailService;

    @Autowired
    private HttpServletRequest servletRequest;

    @GetMapping("login")
    public String login(Model model){
        var uid = cookieService.read("userid");
        if (uid != null) {
            var id = uid.getValue();
            var pw = cookieService.read("pass").getValue();
            model.addAttribute("uid", id);
            model.addAttribute("pwd", pw);
        }

        return "account/login";
    }

    @PostMapping("login")
    public String login(Model model,
                        @RequestParam("id") String userName,
                        @RequestParam("pw") String password,
                        @RequestParam(value = "rm", defaultValue = "false") boolean rememberMe){

        var user = customerRepository.findById(userName);
        var msg = "message";
        if (user == null) {
            model.addAttribute(msg, "Invalid username");
        }else if (!password.equals(user.getPassword())){
            model.addAttribute(msg, "Invalid password");
        }else if (!user.getIsActivated()){
            model.addAttribute(msg, "Your account inactivated!");
        }else {
            model.addAttribute(msg, "Login success!");
            session.setAttribute("user", user);
            if (rememberMe){
                cookieService.create("userid", user.getId(), 30);
                cookieService.create("pass", user.getPassword(), 30);
            }else {
                cookieService.delete("userid");
                cookieService.delete("pass");
            }

            var backUrl = session.getAttribute("back-url");
            if (backUrl != null) {
                return "redirect:" + backUrl;
            }
        }

        return "account/login";
    }

    @RequestMapping("logout")
    public String logout(){
        session.removeAttribute("user");
        return "redirect:/home/index";
    }

    @GetMapping("register")
    public String register(Model model){
        model.addAttribute("form", new Customer());
        return "account/register";
    }

    @PostMapping("register")
    public String register(Model model,
                           @Validated @ModelAttribute("form") Customer user,
                           BindingResult errors,
                           @RequestParam("photo_file") MultipartFile file) {
       if (errors.hasErrors()){
           model.addAttribute("message", "Please fix these errors");
           return "account/register";
       }

       var user2 = customerRepository.findById(user.getId());
        if (user2 != null) {
            model.addAttribute("message", "User name not available");
            return "account/register";
        }

        try {
            if (file.isEmpty()){
                user.setPhoto("user.png");
            }else {
                var dir = context.getRealPath("/static/images/customers");
                var f = new File(dir, file.getOriginalFilename());
                file.transferTo(f);
                user.setPhoto(f.getName());
            }

            user.setIsActivated(false);
            user.setIsAdmin(false);
            customerRepository.create(user);

            var from = "henrylarkson102@gmail.com";
            var to = user.getEmail();
            var subject = "Welcome";
            var url = servletRequest.getRequestURL().toString().replace("register", "activate/" + user.getId());
            var body = String.format("Click <a href='%s'>here</a>", url);
            var mailInfo = new MailInfo(from, to, subject, body);
            mailService.send(mailInfo);

            model.addAttribute("message", "Register success");
        }catch (Exception ex){
            model.addAttribute("message", "Register error:" + ex.getMessage());
        }

        return "account/register";
    }

    @GetMapping("activate/{id}")
    public String activate(@PathVariable("id") String uid){
        var user = customerRepository.findById(uid);
        user.setIsActivated(true);
        customerRepository.update(user);
        return "redirect:/account/login";
    }

    @GetMapping("forgot")
    public String forgot(){
        return "account/forgot";
    }

    @PostMapping("forgot")
    public String forgot(Model model,
                         @RequestParam("id") String uid,
                         @RequestParam("email") String email){
        var user = customerRepository.findById(uid);
        if (user == null) {
            model.addAttribute("message", "Invalid username");
        }else if (!email.equals(user.getEmail())){
            model.addAttribute("message", "Invalid email");
        }else {
            var from = "henrylarkson102@gmail.com";
            var to = user.getEmail();
            var subject = "Forgot password";
            var body = String.format("Your password is: %s", user.getPassword());
            var mailInfo = new MailInfo(from, to, subject, body);
            try {
                mailService.send(mailInfo);
                model.addAttribute("message", "Send mail, please check");
            } catch (MessagingException e) {
                model.addAttribute("error", e.toString());
                model.addAttribute("message", "Error:" + e.getMessage());
            }
        }

        return "account/forgot";
    }

    @GetMapping("change")
    public String change(){
        return "account/change";
    }

    @PostMapping("change")
    public String change(Model model,
                         @RequestParam("id") String uid,
                         @RequestParam("pw") String pw,
                         @RequestParam("pw1") String pw1,
                         @RequestParam("pw2") String pw2){

        if (!pw1.equals(pw2)){
            model.addAttribute("message", "Confirm password is not match");
        }else {
            var user = customerRepository.findById(uid);
            if (user == null) {
                model.addAttribute("message", "Invalid username");
            }else if (!pw.equals(user.getEmail())){
                model.addAttribute("message", "Invalid password");
            }else {
                user.setPassword(pw1);
                model.addAttribute("message", "Changed password");
            }
        }

        return "account/change";
    }

    @GetMapping("edit")
    public String edit(Model model){
        Customer user = (Customer) session.getAttribute("user");
        model.addAttribute("form", user);
        return "account/edit";
    }

    @PostMapping("edit")
    public String edit(Model model,
                           @RequestParam("photo_file") MultipartFile file,
                           @ModelAttribute("form") Customer user) {
        try {
            if (!file.isEmpty()){
                var dir = context.getRealPath("/static/images/customers");
                var f = new File(dir, file.getOriginalFilename());
                file.transferTo(f);
                user.setPhoto(f.getName());
            }

            customerRepository.update(user);
            session.setAttribute("user", user);
            model.addAttribute("message", "Update success");
        }catch (Exception ex){
            ex.printStackTrace();
            model.addAttribute("message", "Update error:" + ex.getMessage());
        }

        return "account/edit";
    }
}
