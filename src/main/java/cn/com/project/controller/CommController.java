package cn.com.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.com.project.domain.Grade;
import cn.com.project.domain.Kh;
import cn.com.project.domain.Leibie;
import cn.com.project.domain.Ly;
import cn.com.project.domain.Message;
import cn.com.project.domain.Task;
import cn.com.project.domain.User;
import cn.com.project.domain.Xuanke;
import cn.com.project.service.CommService;
import cn.com.project.service.UserService;
import cn.com.project.utils.Comm;
import cn.com.project.utils.DateUtils;

@Controller
@RequestMapping(value = "/comm")
public class CommController {
    @Autowired
    private CommService commService;

    @Autowired
    private UserService userService;

    /**
     * 
     * @描述:添加评估
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/addXx")
    public String addXx(Message record,
            HttpServletRequest request) throws Exception {
        record.setFname(Comm.getUserInfoName(request));
        record.setFdate(DateUtils.GetNowDate());
        commService.insertSelective(record);
        Xuanke x = new Xuanke();
        x.setsId(record.getAid());
        List<Xuanke> list = commService.select(x);
        //完成不好扣一分
        if (list.size() > 0) {
            //质量低扣一分
            if (record.getLx().equals("低")) {
                User u1 = userService.selectByPrimaryKey(list.get(0).getaId());
                u1.setJsnumber(
                    u1.getJsnumber() - 1);
                userService.updateByPrimaryKey(u1);
            }
            //完成质量高加一分
            if (record.getLx().equals("高")) {
                User u1 = userService.selectByPrimaryKey(list.get(0).getaId());
                Integer mum = Integer.valueOf(u1.getJsnumber());
                if (mum <= 100) {
                    u1.setJsnumber(mum + 1);
                }
                userService.updateByPrimaryKey(u1);
            }
        }
        return "redirect:/comm/selXx";
    }

    /**
     * 
     * @描述:查看评估信息
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/selXx")
    public ModelAndView selXx(Integer page, Message g,
            HttpServletRequest request) throws Exception {
        String role = Comm.getUserRole(request);
        if (role.equals("1")) {
            g.setAid(Comm.getUserInfoId(request));
        } else if (role.equals("2")) {
            g.setFname(Comm.getUserInfoName(request));
        }
        ModelAndView mav = new ModelAndView();
        //分页
        if (page == null) {
            page = 1;
        }
        //分页        
        PageHelper.startPage(page, 10, true);
        List<Message> list = commService.select(g);

        PageInfo<Message> pageInfo = new PageInfo<Message>(list);

        mav.addObject("list", list);
        mav.addObject("page", new PageInfo(list));
        mav.addObject("count", pageInfo.getTotal());
        mav.addObject("countPage", pageInfo.getPages());
        mav.addObject("pageInfo", pageInfo);
        if ("2".equals(role)) {
            mav.setViewName("gr/xx/sel");
        } else if ("0".equals(role)) {
            mav.setViewName("gr/xx/sel1");
        } else {
            mav.setViewName("gr/xx/sel2");
        }

        return mav;
    }

    /**
     * 
     * @描述:跳转到添加
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/tzpj")
    public ModelAndView tzpj(Task c, User u) throws Exception {
        ModelAndView mav = new ModelAndView();
        u.setRole("1");
        List<Task> courseList = commService.select(c);
        List<User> stuList = userService.selectUserList(u);

        mav.addObject("courseList", courseList);
        mav.addObject("stuList", stuList);
        mav.setViewName("gr/xx/add");
        return mav;
    }
    /**
     * 
     * @描述: 根据id查询评估
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/showXx")
    public ModelAndView showXx(Integer id) throws Exception {
        ModelAndView mav = new ModelAndView();
        Message data = commService.selectByXx(id);
        Task c = new Task();
        List<Task> courseList = commService.select(c);

        mav.addObject("courseList", courseList);
        mav.addObject("data", data);
        mav.setViewName("comm/xx/update");
        return mav;
    }

    /**
     * 
     * @描述:保存更新评估信息
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/updateXx")
    public String updateGg(Message record) throws Exception {

        commService.updateByPrimaryKeySelective(record);
        return "redirect:/comm/selXx";
    }

    /**
     * 
     * @描述:删除评估信息
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: 
     * @返回值:
     */
    @RequestMapping(value = "/deleteXx")
    public String deleteXx(Integer id) throws Exception {

        commService.deleteByXx(id);

        return "redirect:/comm/selXx";
    }
    

    /**
     * 
     * @描述:跳转到添加发布作业信息页面
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/tzcj1")
    public ModelAndView tz11(Integer id) throws Exception {
        ModelAndView mav = new ModelAndView();
        Xuanke data = commService.selectByXk(id);
        mav.addObject("data", data);
        mav.setViewName("yh/pj/addGra");
        return mav;
    }

    /**
     * 
     * @描述:添加评价信息
     * @作者:
     * @时间 2019年2月20日 下午2:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping("/addGra")
    public String addGra(Grade u) {
        commService.insertGra(u);
        if (u.getFenshu().equals("差评")) {
            User u1 = userService.selectByPrimaryKey(u.getaId());
            u1.setJsnumber(
                u1.getJsnumber() - 5);
            userService.updateByPrimaryKey(u1);
        }
        return "redirect:/comm/selGra";
    }

    /**
     * 
     * @描述:查看评价信息
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/selGra")
    public ModelAndView selGra(Integer page, Grade g,
            HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        String role = Comm.getUserRole(request);
        if (role.equals("2")) {
            g.setaId(Comm.getUserInfoId(request));
        }
        //分页
        if (page == null) {
            page = 1;
        }

        PageHelper.startPage(page, 10, true);
        List<Grade> graList = commService.selectGraList(g);
        PageInfo<Grade> pageInfo = new PageInfo<Grade>(graList);

        mav.addObject("graList", graList);
        mav.addObject("page", new PageInfo(graList));
        mav.addObject("count", pageInfo.getTotal());
        mav.addObject("countPage", pageInfo.getPages());
        mav.addObject("pageInfo", pageInfo);
        if ("2".equals(role)) {
            mav.setViewName("yh/pj/selGra1");
        } else if ("0".equals(role)) {
            mav.setViewName("yh/pj/selGra");
        } else {
            mav.setViewName("yh/pj/selGra");
        }
        return mav;
    }

    /**
     * 
     * @描述:工人查看个人评价信息
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/selMyGra")
    public ModelAndView selMyGra(Grade g,
            HttpServletRequest request) throws Exception {
        Integer id = Comm.getUserInfoId(request);
        g.setaId(id);
        ModelAndView mav = new ModelAndView();
        List<Grade> graList = commService.selectGraList(g);

        mav.addObject("graList", graList);
        mav.setViewName("yh/pj/selMyGra");
        return mav;
    }

    /**
     * 
     * @描述:根据id显示
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/showGra")
    public ModelAndView showGra(Integer id) throws Exception {
        ModelAndView mav = new ModelAndView();
        Grade g = commService.selectGra(id);
        mav.addObject("g", g);
        mav.setViewName("yh/pj/updateGra");
        return mav;
    }

    /**
     * 
     * @描述:保存更新评价信息
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/updateGra")
    public String updateGra(Grade u) throws Exception {
        commService.updateGra(u);

        return "redirect:/comm/selGra";
    }

    /**
     * 
     * @描述:删除评价信息
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/deleteGra")
    public String deleteGra(Integer id) throws Exception {

        commService.deleteGra(id);

        return "redirect:/comm/selGra";
    }

    /**
     * 
     * @描述:已完成任务
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: 
     * @返回值:
     */
    @RequestMapping(value = "/agreeXk")
    public String agreeXk(Integer id,
            HttpServletRequest request) throws Exception {
        Xuanke xk = commService.selectByXk(id);
        xk.setState("已完成");
        commService.updateByPrimaryKeySelective(xk);

        User u1 = userService.selectByPrimaryKey(Comm.getUserInfoId(request));
        Integer mum = u1.getJsnumber();
        if (mum <= 100) {
            u1.setJsnumber(mum + 1);
        }
        userService.updateByPrimaryKey(u1);

        Task c = commService.selectByCou(xk.getsId());
        c.setState("已完成");
        commService.updateByPrimaryKeyCou(c);
        return "redirect:/comm/selXk";
    }

    /**
     * 
     * @描述:添加接取任务
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: 
     * @返回值:
     */
    @RequestMapping(value = "/addXk")
    @ResponseBody
    public Map<String, Object> addXk(Xuanke xk,
            HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        User u1 = userService.selectByPrimaryKey(Comm.getUserInfoId(request));
        Integer mum = u1.getJsnumber();
        if (mum <= 60) {
            map.put("fail", "由于您信用分低于60分，无法接取任务");
        } else {
            if (mum > 60 && mum <= 80) {//信用分低于80，接取任务时收押金
                Kh k = new Kh();
//                map.put("fail", "由于您信用分低于80分，请支付押金后接取任务");
                k.setKname("收押金");
                k.setKhly("押金");
                k.setLxr("100");
                k.setKhjb("信用分低于80分，接取任务收押金");
                k.setDz(DateUtils.GetNowDate());
                k.setBianhao(Comm.getBh());
                k.setLname(Comm.getUserInfoName(request));
                commService.insertSelective(k);
                u1.setState(
                    String.valueOf(Integer.valueOf(u1.getState()) - 100));
                userService.updateByPrimaryKey(u1);
            }
            xk.setState("未完成");
            commService.insertSelective(xk, request);
            map.put("flag", "接取任务成功");

            Task c = commService.selectByCou(xk.getsId());
            c.setState("已接取");
            commService.updateByPrimaryKeyCou(c);
        }
        return map;
    }

    /**
     * 
     * @描述:查看接取任务信息
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/selXk")
    public ModelAndView selXk(Integer page, Xuanke xk,
            HttpServletRequest request) throws Exception {
        String role = Comm.getUserRole(request);
        if ("2".equals(role)) {
            xk.setaId(Comm.getUserInfoId(request));
        }
        ModelAndView mav = new ModelAndView();
        //分页
        if (page == null) {
            page = 1;
        }
        //分页        
        PageHelper.startPage(page, 10, true);
        List<Xuanke> list = commService.select(xk);
        PageInfo<Xuanke> pageInfo = new PageInfo<Xuanke>(list);

        mav.addObject("list", list);
        mav.addObject("page", new PageInfo(list));
        mav.addObject("count", pageInfo.getTotal());
        mav.addObject("countPage", pageInfo.getPages());
        mav.addObject("pageInfo", pageInfo);
        if ("0".equals(role)) {
            mav.setViewName("gr/xuanke/selXk");
        } else if ("1".equals(role)) {
            mav.setViewName("gr/xuanke/selXk1");
        } else {
            mav.setViewName("gr/xuanke/selXk2");
        }
        return mav;
    }

    /**
     * 
     * @描述:删除接取任务
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: 
     * @返回值:
     */
    @RequestMapping(value = "/deleteXk")
    public String deleteXk(Integer id) throws Exception {
        commService.deleteByXk(id);

        return "redirect:/comm/selXk";
    }

    /**
     * 
     * @描述:跳转到添加评价信息页面
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/tzcj")
    public ModelAndView tz(Task c, User u) throws Exception {
        ModelAndView mav = new ModelAndView();
        u.setRole("2");
        List<Task> taskList = commService.select(c);
        List<User> stuList = userService.selectUserList(u);

        mav.addObject("courseList", taskList);
        mav.addObject("stuList", stuList);
        mav.setViewName("yh/pj/addGra");
        return mav;
    }

    /**
     * 
     * @描述:添加公告
     * @作者:
     * @时间 2022年4月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/addLb")
    public String add(Leibie record,
            HttpServletRequest request) throws Exception {
        String role = Comm.getUserRole(request);
        if ("0".equals(role)) {
            record.setState("1");
        } else {
            record.setState("0");
        }
        record.setuId(Comm.getUserInfoId(request));
        commService.insertSelective(record);
        return "redirect:/comm/selLb";
    }

    /**
     * 
     * @描述:查看公告信息
     * @作者:
     * @时间 2022年4月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/selLb")
    public ModelAndView selFl(Integer page, Leibie g,
            HttpServletRequest request) throws Exception {
        String role = Comm.getUserRole(request);
        ModelAndView mav = new ModelAndView();
        //分页
        if (page == null) {
            page = 1;
        }


        //分页        
        PageHelper.startPage(page, 10, true);
        List<Leibie> list = commService.select(g);
        PageInfo<Leibie> pageInfo = new PageInfo<Leibie>(list);

        mav.addObject("list", list);
        mav.addObject("page", new PageInfo(list));
        mav.addObject("count", pageInfo.getTotal());
        mav.addObject("countPage", pageInfo.getPages());
        mav.addObject("pageInfo", pageInfo);
        if ("0".equals(role)) {
            mav.setViewName("admin/leibie/sel");
        } else {
            mav.setViewName("admin/leibie/sel1");
        }
        return mav;
    }

    /**
     * 
     * @描述: 根据id查询公告
     * @作者:
     * @时间 2022年4月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/showLb")
    public ModelAndView showLb(Integer id) throws Exception {
        ModelAndView mav = new ModelAndView();
        Leibie data = commService.selectByLb(id);

        mav.addObject("data", data);
        mav.setViewName("admin/leibie/update");
        return mav;
    }

    /**
     * 
     * @描述:保存更新公告信息
     * @作者:
     * @时间 2022年4月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/updateLb")
    public String updateLb(Leibie record) throws Exception {

        commService.updateByPrimaryKeySelective(record);
        return "redirect:/comm/selLb";
    }

    /**
     * 
     * @描述:删除公告信息
     * @作者:
     * @时间 2022年4月13日 下午5:39:37
     * @获取一个: 
     * @返回值:
     */
    @RequestMapping(value = "/deleteLb")
    public String deleteLb(Integer id) throws Exception {

        commService.deleteByLb(id);

        return "redirect:/comm/selLb";
    }

    /**
     * 
     * @描述:跳转到添加留言信息页面
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/tzly")
    public ModelAndView tzly(User u) throws Exception {
        ModelAndView mav = new ModelAndView();
        u.setRole("1");
        List<User> stuList = userService.selectUserList(u);

        mav.addObject("list", stuList);
        mav.setViewName("yh/ly/add");
        return mav;
    }

    /**
     * 
     * @描述:添加留言信息
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/addLy")
    public String addLy(Ly record,
            HttpServletRequest request) throws Exception {
        record.setAid(Comm.getUserInfoId(request));
        record.setLname(Comm.getUserInfoName(request));
        commService.insertSelective(record);
        return "redirect:/comm/selLy";
    }

    /**
     * 
     * @描述:查看留言信息
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/selLy")
    public ModelAndView selLy(Integer page, Ly cou,
            HttpServletRequest request) throws Exception {
        String role = Comm.getUserRole(request);
        ModelAndView mav = new ModelAndView();
        if ("2".equals(role)) {
            cou.setAid(Comm.getUserInfoId(request));
        } else if ("1".equals(role)) {
            cou.setJid(Comm.getUserInfoId(request));
        }
        //分页
        if (page == null) {
            page = 1;
        }


        //分页        
        PageHelper.startPage(page, 10, true);
        List<Ly> list = commService.select(cou);
        PageInfo<Ly> pageInfo = new PageInfo<Ly>(list);

        mav.addObject("list", list);
        mav.addObject("page", new PageInfo(list));
        mav.addObject("count", pageInfo.getTotal());
        mav.addObject("countPage", pageInfo.getPages());
        mav.addObject("pageInfo", pageInfo);
        if ("0".equals(role)) {
            mav.setViewName("yh/ly/sel");
        } else if ("1".equals(role)) {
            mav.setViewName("yh/ly/sel1");
        } else {
            mav.setViewName("yh/ly/sel2");
        }
        return mav;
    }

    /**
     * 
     * @描述: 根据id查询留言
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/showLy")
    public ModelAndView showLy(Integer id) throws Exception {
        ModelAndView mav = new ModelAndView();
        Ly c = commService.selectByLy(id);

        mav.addObject("data", c);
        mav.setViewName("yh/ly/hf");
        return mav;
    }

    /**
     * 
     * @描述: 跳转押金
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/tzyj")
    public ModelAndView tzyj(Integer id) throws Exception {
        ModelAndView mav = new ModelAndView();
        User c = userService.selectByPrimaryKey(id);

        mav.addObject("data", c);
        mav.setViewName("admin/kh/add1");
        return mav;
    }
    
    /**
     * 
     * @描述:保存更新留言信息
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/updateLy")
    public String updateLy(Ly record) throws Exception {

        commService.updateByPrimaryKeySelective(record);
        return "redirect:/comm/selLy";

    }

    /**
     * 
     * @描述:删除留言信息
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: 
     * @返回值:
     */
    @RequestMapping(value = "/deleteLy")
    public String deleteLy(Integer id) throws Exception {

        commService.deleteByLy(id);

        return "redirect:/comm/selLy";
    }

    /**
     * 
     * @描述:添加钱包
     * @作者:
     * @时间 2022年4月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/addKh")
    public String addKh(Kh record,
            HttpServletRequest request) throws Exception {
        record.setBianhao(Comm.getBh());
        record.setLname(Comm.getUserInfoName(request));
        record.setDz(DateUtils.GetNowDate());
        commService.insertSelective(record);
        User u1 = userService
                .selectByPrimaryKey(Comm.getUserInfoId(request));
        if (record.getKhly().equals("收入")) {
            u1.setState(String.valueOf(Integer.valueOf(record.getLxr())
                    + Integer.valueOf(u1.getState())));
            userService.updateByPrimaryKey(u1);
        }else {
            u1.setState(String.valueOf(Integer.valueOf(u1.getState())
                    - Integer.valueOf(record.getLxr())));
            userService.updateByPrimaryKey(u1);
        }
        return "redirect:/comm/selKh";
    }
    
    /**
     * 
     * @描述:添加押金
     * @作者:
     * @时间 2022年4月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/addKh1")
    public String addKh1(Kh record,
            HttpServletRequest request) throws Exception {

        User u1 = userService.selectByPrimaryKey(record.getAid());
        record.setBianhao(Comm.getBh());
        record.setLname(u1.getAccount());
        record.setDz(DateUtils.GetNowDate());
        record.setKhly("支出");
        commService.insertSelective(record);

        u1.setState(String.valueOf(
            Integer.valueOf(u1.getState()) - Integer.valueOf(record.getLxr())));
        userService.updateByPrimaryKey(u1);
        return "redirect:/comm/selKh";
    }

    /**
     * 
     * @描述:查看钱包信息
     * @作者:
     * @时间 2022年4月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/selKh")
    public ModelAndView selJd(Integer page, Kh g,
            HttpServletRequest request) throws Exception {
        String role = Comm.getUserRole(request);
        if ("2".equals(role) || "1".equals(role)) {
            g.setLname(Comm.getUserInfoName(request));
        }
        ModelAndView mav = new ModelAndView();
        //分页
        if (page == null) {
            page = 1;
        }

        //分页        
        PageHelper.startPage(page, 10, true);
        List<Kh> list = commService.select(g);

        PageInfo<Kh> pageInfo = new PageInfo<Kh>(list);

        mav.addObject("list", list);
        mav.addObject("page", new PageInfo(list));
        mav.addObject("count", pageInfo.getTotal());
        mav.addObject("countPage", pageInfo.getPages());
        mav.addObject("pageInfo", pageInfo);
        if ("2".equals(role)) {
            mav.setViewName("admin/kh/sel");
        } else if ("1".equals(role)) {
            mav.setViewName("admin/kh/sel");
        } else if ("0".equals(role)) {
            mav.setViewName("admin/kh/sel1");
        }
        return mav;
    }

    /**
     * 
     * @描述: 根据id查询钱包
     * @作者:
     * @时间 2022年4月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/showKh")
    public ModelAndView showJd(Integer id) throws Exception {
        ModelAndView mav = new ModelAndView();
        Kh data = commService.selectByKh(id);

        mav.addObject("data", data);
        mav.setViewName("admin/kh/update");
        return mav;
    }

    /**
     * 
     * @描述:保存更新钱包
     * @作者:
     * @时间 2022年4月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/updateKh")
    public String updateJd(Kh record) throws Exception {

        commService.updateByPrimaryKeySelective(record);
        return "redirect:/comm/selKh";
    }

    /**
     * 
     * @描述:保存更新钱包
     * @作者:
     * @时间 2022年4月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/shss")
    public String shss(Kh record) throws Exception {
        record.setLxr("已处理");
        commService.updateByPrimaryKeySelective(record);
        return "redirect:/comm/selKh";
    }

    /**
     * 
     * @描述:删除钱包
     * @作者:
     * @时间 2022年4月13日 下午5:39:37
     * @获取一个: 
     * @返回值:
     */
    @RequestMapping(value = "/deleteKh")
    public String deleteKh(Integer id) throws Exception {

        commService.deleteByKh(id);

        return "redirect:/comm/selKh";
    }

    /**
     * 
     * @描述: 跳转到添加任务表页面
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/tzKb")
    public ModelAndView tzKb(Task u) throws Exception {
        ModelAndView mav = new ModelAndView();
        List<Task> list = commService.select(u);

        mav.addObject("list", list);
        mav.setViewName("yh/kcb/add");
        return mav;
    }



    /**
     * 
     * @描述: 跳转到添加任务页面
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/tz")
    public ModelAndView tz(User u) throws Exception {
        ModelAndView mav = new ModelAndView();
        u.setRole("1");
        List<User> list = userService.selectUserList(u);

        mav.addObject("list", list);
        mav.setViewName("yh/task/addCou");
        return mav;
    }

    /**
     * 
     * @描述:添加任务信息
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/addCou")
    public String addCou(Task cou,
            HttpServletRequest request) throws Exception {
        cou.setState("未接取");
        cou.setaId(Comm.getUserInfoId(request));
        commService.insertSelectiveCou(cou);
        return "redirect:/comm/selCou";
    }

    /**
     * 
     * @描述:查看任务信息
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/selCou")
    public ModelAndView selCou(Integer page, Task cou,
            HttpServletRequest request) throws Exception {
        String role = Comm.getUserRole(request);
        ModelAndView mav = new ModelAndView();
        if ("1".equals(role)) {
            cou.setaId(Comm.getUserInfoId(request));
        }
        //分页
        if (page == null) {
            page = 1;
        }

        //分页        
        PageHelper.startPage(page, 10, true);
        List<Task> list = commService.select(cou);

        PageInfo<Task> pageInfo = new PageInfo<Task>(list);

        mav.addObject("courseList", list);
        mav.addObject("page", new PageInfo(list));
        mav.addObject("count", pageInfo.getTotal());
        mav.addObject("countPage", pageInfo.getPages());
        mav.addObject("pageInfo", pageInfo);
        if ("0".equals(role)) {
            mav.setViewName("yh/task/selCou");
        } else if ("1".equals(role)) {
            mav.setViewName("yh/task/selCou1");
        } else {
            mav.setViewName("yh/task/selCou2");
        }
        return mav;
    }

    /**
     * 
     * @描述: 根据id查询
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/showCou")
    public ModelAndView showCou(Integer cid, User u) throws Exception {
        ModelAndView mav = new ModelAndView();
        Task c = commService.selectByCou(cid);
        u.setRole("1");
        List<User> list = userService.selectUserList(u);

        mav.addObject("list", list);
        mav.addObject("c", c);
        mav.setViewName("yh/task/updateCou");
        return mav;
    }

    /**
     * 
     * @描述:保存更新任务信息
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/updateCou")
    public String updateCou(Task record) throws Exception {

        commService.updateByPrimaryKeySelectiveCou(record);

        return "redirect:/comm/selCou";
    }

    /**
     * 
     * @描述:删除任务信息
     * @作者:
     * @时间 2022年4月20日 下午2:39:37
     * @获取一个: 
     * @返回值:
     */
    @RequestMapping(value = "/deleteCou")
    public String deleteCou(Integer id) throws Exception {

        commService.deleteByCou(id);

        return "redirect:/comm/selCou";
    }

}
