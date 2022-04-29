package cn.com.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.com.project.domain.User;
import cn.com.project.service.LoginService;
import cn.com.project.service.UserService;
import cn.com.project.utils.Comm;

/**
 * 
 * @描述:登陆操作
 * @作者:
 * @时间 2022年4月20日 下午2:39:37
 * @获取一个: login
 * @返回值:ModelAndView
 */
@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public ModelAndView a() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    /**
     * 
     * @描述:后台管理员登陆
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: login
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(String account, String password, String role,
            HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        User u = loginService.login(account, password);
        //获取session
        HttpSession session = request.getSession();
        if (u == null) {//登陆失败
            mav.addObject("message", "用户名或密码错误");
            mav.setViewName("login");
        } else if ("0".equals(u.getRole())) {//系统管理员
            session.setAttribute("id", u.getId());
            session.setAttribute("account", u.getAccount());
            session.setAttribute("password", u.getPassword());
            session.setAttribute("role", u.getRole());
            mav.setViewName("admin/index");
        } else if ("1".equals(u.getRole())) {//用户
            session.setAttribute("id", u.getId());
            session.setAttribute("account", u.getAccount());
            session.setAttribute("password", u.getPassword());
            session.setAttribute("role", u.getRole());
            mav.setViewName("yh/index");
        } else if ("2".equals(u.getRole())) {//工人
            if ("0".equals(u.getWh())) {
                request.setAttribute("message", "暂无权限登录!");
                mav.setViewName("login");
            } else {
                session.setAttribute("id", u.getId());
                session.setAttribute("account", u.getAccount());
                session.setAttribute("password", u.getPassword());
                session.setAttribute("role", u.getRole());
                session.setAttribute("banji", u.getJsnumber());
                mav.setViewName("gr/index");
            }

        } else {
            request.setAttribute("message", "用户所属角色不统一");
            mav.setViewName("login");
        }
        return mav;
    }

    /**
     * 
     * @描述:修改密码
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/updatePassword")
    public ModelAndView updatePassword(HttpServletRequest request,
            String gpassword) throws Exception {
        ModelAndView mav = new ModelAndView();
        Integer id = Comm.getUserInfoId(request);
        User a = new User();
        a.setId(id);
        a.setPassword(gpassword);
        userService.updatePassword(a);
        mav.setViewName("admin/password/updatePassword");
        return mav;
    }
}