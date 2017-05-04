package com.mythsman.interceptor;

import com.mythsman.dao.LoginTicketDao;
import com.mythsman.dao.UserDao;
import com.mythsman.model.LoginTicket;
import com.mythsman.model.User;
import com.mythsman.model.UserComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by myths on 5/4/17.
 */
@Component
public class PassportInterceptor implements HandlerInterceptor {
    @Autowired
    LoginTicketDao loginTicketDao;

    @Autowired
    UserDao userDao;

    @Autowired
    UserComponent userComponent;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String ticket=null;
        for(Cookie cookie : httpServletRequest.getCookies()){
            if(cookie.getName().equals("ticket")){
                ticket=cookie.getValue();
                break;
            }
        }
        if(ticket!=null){
            LoginTicket loginTicket=loginTicketDao.selectByTicket(ticket);
            if(loginTicket==null||loginTicket.getExpire().before(new Date())||loginTicket.getValid()!=1){
                return true;
            }

            User user=userDao.selectById(loginTicket.getUid());
            userComponent.setUser(user);

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if(modelAndView!=null){
            modelAndView.addObject("user",userComponent.getUser());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        userComponent.clear();
    }
}
