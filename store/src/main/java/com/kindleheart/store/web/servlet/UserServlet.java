package com.kindleheart.store.web.servlet;

import com.kindleheart.store.domain.User;
import com.kindleheart.store.service.UserService;
import com.kindleheart.store.service.impl.UserServiceImpl;
import com.kindleheart.store.utils.MailUtils;
import com.kindleheart.store.utils.MyBeanUtils;
import com.kindleheart.store.utils.UUIDUtils;
import com.kindleheart.store.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by kindleheart happily.
 */
@WebServlet(name = "UserServlet", urlPatterns = "/UserServlet")
public class UserServlet extends BaseServlet {
    //registerUI
    public String registerUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/jsp/register.jsp";
    }
    //userRegister
    public String userRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收表单数据
        Map<String, String[]> map = request.getParameterMap();
        //把数据封装到user
        User user = new User();
        MyBeanUtils.populate(user, map);
        //为其他数据赋值
        user.setUid(UUIDUtils.getId());
        user.setState(0);
        user.setCode(UUIDUtils.getCode());
        System.out.println(user);
        //调用业务逻辑层
        UserService userService = new UserServiceImpl();
        try {
            userService.userRegister(user);
            //注册成功，向用户邮箱发送信息，跳转
            //发送邮件
            MailUtils.sendMail(user.getEmail(), user.getCode());
            request.setAttribute("msg", "用户注册成功，请激活");
        } catch (Exception e) {
            request.setAttribute("msg", "用户注册失败，请重写注册");
            e.printStackTrace();
        }
        return "jsp/info.jsp";
    }
    //active
    public String active(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String code = request.getParameter("code");
        UserService userService = new UserServiceImpl();
        boolean flag = userService.userActive(code);
        if(flag) {
            request.setAttribute("msg", "用户激活成功,请登入");
            return "jsp/login.jsp";
        } else {
            request.setAttribute("msg", "激活失败，请重新激活");
            return "jsp/info.jsp";
        }
    }
    //loginUI
    public String loginUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "jsp/login.jsp";
    }
    //userLogin
    public String userLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取账号，密码
        User user = new User();
        MyBeanUtils.populate(user, request.getParameterMap());
        //调用业务登录
        UserService userService = new UserServiceImpl();
        try {
            //登录成功
            user = userService.userLogin(user);
            request.getSession().setAttribute("user", user);
            response.sendRedirect("index.jsp");
            return null;
        } catch (Exception e) {
            //登录失败
            String msg = e.getMessage();
            System.out.println(msg);
            request.setAttribute("msg", msg);
            return "jsp/login.jsp";
        }
    }
    //logOut
    public String logOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().removeAttribute("user");
        response.sendRedirect("index.jsp");
        return null;
    }
}
