package com.odmytrenko.spring.filter;

import com.odmytrenko.spring.dao.UserDao;
import com.odmytrenko.spring.model.Roles;
import com.odmytrenko.spring.model.RolesClassWrapper;
import com.odmytrenko.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class AdminInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        Cookie[] cookies = request.getCookies();
        if (cookies != null && Arrays.stream(cookies).anyMatch(p -> p.getName().equals("TOKEN"))) {
            User user = userDao.findByToken(Arrays.stream(cookies).filter(p -> p.getName().equals("TOKEN")).
                    findFirst().get().getValue());
            if (!user.getRoles().contains(RolesClassWrapper.wrapRole(Roles.ADMIN))) {
                throw new RuntimeException("User doesn't have privileges to enter this page");
            } else {
                return true;
            }
        } else {
            throw new RuntimeException("You must be logged on to enter this page");
        }
    }
}
