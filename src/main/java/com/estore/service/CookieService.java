package com.estore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Base64;

@Service
public class CookieService {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    public Cookie create(String name, String value, int days){
        var encode = Base64.getEncoder().encodeToString(value.getBytes());
        var cookie = new Cookie(name, encode);
        cookie.setMaxAge(days * 24 * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
        return cookie;
    }

    /*public Cookie read(String name){
        var cookies = request.getCookies();
        return cookies == null ? null :
                Arrays.stream(cookies)
                        .filter(c -> {
                            if (c.getName().equalsIgnoreCase(name)){
                                var decode = Base64.getDecoder().decode(c.getValue());
                                c.setValue(new String(decode));
                                return true;
                            }

                            return false;
                        })
                        .findFirst().orElse(null);
    }*/

    public Cookie read(String name){
        var cookies = request.getCookies();
        if (cookies != null) {
            for (var c: cookies) {
                if (c.getName().equalsIgnoreCase(name)){
                    var decode = Base64.getDecoder().decode(c.getValue());
                    c.setValue(new String(decode));
                    return c;
                }
            }
        }

       return null;
    }

    public void delete(String name){
        create(name, "" , 0);
    }
}
