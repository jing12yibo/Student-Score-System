package com.fc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fc.entity.TUser;
import com.fc.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fc.entity.ResultObject;
import com.fc.entity.TStudent;
import com.fc.entity.TUser;
import com.fc.service.TUserService;
import com.fc.service.TStudentService;
import com.fc.util.Constant;

/**
 * @author Linco
 *
 */
@RequestMapping("/user")
@RestController //标识为返回类型为Json的控制器
public class UserController {

    //自动注入服务类
    @Autowired
    private TUserService userService;

    @Autowired
    private TStudentService studentService;


    //标识请求地址
    @RequestMapping("/login")
    public ResultObject<List<TUser>> getUsers(TUser user, HttpServletRequest request) {
        //查询用户列表
        List<TUser> list= userService.getUser(user);
        ResultObject<List<TUser>> rs=new ResultObject<List<TUser>>();
        if(list.isEmpty()) {
            //状态码
            rs.setCode(Constant.FAILURE_RETUEN_CODE);
            //提示
            rs.setMsg("登录失败");
        }else {
            //状态码
            rs.setCode(Constant.SUCCESS_RETUEN_CODE);
            request.getSession().setAttribute("user", list.get(0));
            //提示
            rs.setMsg("登录成功");
        }
        //数据
        rs.setData(list);
        return rs;
    }
    @RequestMapping("/loginOut")
    public ResultObject<Object> loginOut(HttpServletRequest request) {
        //查询用户列表
        ResultObject<Object> rs=new ResultObject<Object>();
        request.getSession().removeAttribute("user");
        //数据
        rs.setCode(Constant.SUCCESS_RETUEN_CODE);
        rs.setMsg("退出成功");
        return rs;
    }

    //标识请求地址
    @RequestMapping("/studentLogin")
    public ResultObject<List<TStudent>> studentLogin(TUser user,HttpServletRequest request) {
        //查询用户列表
        TStudent student=new TStudent();
        student.setStudentNo(Integer.parseInt(user.getUserName()));
        student.setStuPass(user.getPassword());
        List<TStudent> list= studentService.selectloginStudent(student);
        ResultObject<List<TStudent>> rs=new ResultObject<List<TStudent>>();
        if(list.isEmpty()) {
            //状态码
            rs.setCode(Constant.FAILURE_RETUEN_CODE);
            //提示
            rs.setMsg("登录失败");
        }else {
            //状态码
            rs.setCode(Constant.SUCCESS_RETUEN_CODE);
            request.getSession().setAttribute("student", list.get(0));
            //提示
            rs.setMsg("登录成功");
        }
        //数据
        rs.setData(list);
        return rs;
    }
    @RequestMapping("/studentloginOut")
    public ResultObject<Object> studentloginOut(HttpServletRequest request) {
        //查询用户列表
        ResultObject<Object> rs=new ResultObject<Object>();
        request.getSession().removeAttribute("student");
        //数据
        rs.setCode(Constant.SUCCESS_RETUEN_CODE);
        rs.setMsg("退出成功");
        return rs;
    }
}