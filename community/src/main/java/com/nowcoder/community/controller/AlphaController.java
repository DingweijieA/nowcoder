package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import com.nowcoder.community.util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.logging.Logger;

@Controller
@RequestMapping("/alpha")
public class AlphaController {
    @RequestMapping("/hello")
    @ResponseBody
    public  String sayHello(){
        return  "hello world";

    }

    //GET请求中 ？方式传参
    //students?current=1&limit=20
    @RequestMapping(path ="/students",method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(@RequestParam(name ="current",required = false,defaultValue = "1")int current,
                              @RequestParam(name ="limit",required = false,defaultValue = "10")int limit){
        System.out.println(current+" "+limit);
        return "some students";

    }
    //从路径中传参
    //students/18
    @RequestMapping(path ="/students/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(@PathVariable("id")int id){
        System.out.println(id);
        return "a students";

    }

    //post请求,通过html对应传参，查看student.html，处理参数并
    // 用静态模板返回给网页
    @RequestMapping(path = "/student",method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name,int age){
        System.out.println(name);
        System.out.println(age);
        return "success";

    }
    //响应HTML数据,用动态模板返回给网页
    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.addObject("name","zhangsan");
        modelAndView.addObject("age","21");
        modelAndView.setViewName("/demo/view");
        return  modelAndView;


    }
    //返回view，在内部设置model,常用这种方法
    @RequestMapping(path = "/school",method = RequestMethod.GET)
    public String getTeacher(Model model) {
        model.addAttribute("name","北大");
        model.addAttribute("age","89");
        return "/demo/view";
    }
    //响应JSON数据（异步请求）
    //Java对象-JSON字符串-JS对象
    @RequestMapping(path =  "/emp",method = RequestMethod.GET)
    @ResponseBody
    public Map<String ,Object> getEmp(){
        Map<String ,Object> map =new HashMap<>();
        map.put("name","zhangsan");
        map.put("age","18");
        map.put("salary","10k");
        return map;

    }
    
    @RequestMapping(path =  "/emps",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String ,Object>> getEmps(){
        List<Map<String ,Object>> list =new ArrayList<>();
        Map<String ,Object> map =new HashMap<>();
        map.put("name","zhangsan");
        map.put("age","18");
        map.put("salary","10k");
        list.add(map);

        map =new HashMap<>();
        map.put("name","lisi");
        map.put("age","25");
        map.put("salary","13k");
        list.add(map);

        map =new HashMap<>();
        map.put("name","wangba");
        map.put("age","19");
        map.put("salary","102k");
        list.add(map);

        return list;

    }
    // cookie示例

    @RequestMapping(path = "/cookie/set", method = RequestMethod.GET)
    @ResponseBody
    public String setCookie(HttpServletResponse response) {
        // 创建cookie
        Cookie cookie = new Cookie("code", CommunityUtil.generateUUID());
        // 设置cookie生效的范围
        cookie.setPath("/community/alpha");
        // 设置cookie的生存时间
        cookie.setMaxAge(60 * 10);
        // 发送cookie
        response.addCookie(cookie);

        return "set cookie";
    }

    @RequestMapping(path = "/cookie/get", method = RequestMethod.GET)
    @ResponseBody
    public String getCookie(@CookieValue("code") String code) {
        System.out.println(code);
        return "get cookie";
    }

    // session示例

    @RequestMapping(path = "/session/set", method = RequestMethod.GET)
    @ResponseBody
    public String setSession(HttpSession session) {
        session.setAttribute("id", 1);
        session.setAttribute("name", "Test");
        return "set session";
    }

    @RequestMapping(path = "/session/get", method = RequestMethod.GET)
    @ResponseBody
    public String getSession(HttpSession session) {
        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        return "get session";
    }
    //为什么不用session常常用cookie？
//    1、减少服务端压力
//    2、一般服务器是用分布式部署
//    3、代理nginx解决方案有粘性session（负载不均衡）、同步session（占用内存，耦合度高）、session库（访问速度慢）、redis
//

}
