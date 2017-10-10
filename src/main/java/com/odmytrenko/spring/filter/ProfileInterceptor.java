package com.odmytrenko.spring.filter;

import com.odmytrenko.spring.dao.UserDao;
import com.odmytrenko.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Stream;

public class ProfileInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Stream.of(cookies).forEach(c -> {
                String name = c.getName().toUpperCase();
                if (name.equals("TOKEN")) {
                    String token = c.getValue();
                    User user = userDao.findByToken(token);
                    request.setAttribute("user", user);
                }
            });
            return true;
        } else {
            return true;
        }
    }
}
