package com.fc.controller;


import com.fc.entity.ResultVo;
import com.fc.entity.TStudent;
import com.fc.service.StudentService;
import com.fc.util.Constant;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jyb
 *
 */

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/getAllStudent")
    public ResultVo<List<TStudent>> getAllStudent(TStudent student,
                                                  @RequestParam("limit") int limit,
                                                  @RequestParam("page") int page) {
        System.out.println(student);
        PageInfo<TStudent> pageInfo = studentService.getAllStudent(student,page,limit);
        ResultVo<List<TStudent>> rs = new ResultVo<>();
        rs.setCode(Constant.SUCCESS_RETUEN_CODE);
        rs.setMsg("查询成功");
        rs.setData(pageInfo.getList());
        rs.setCount(pageInfo.getTotal());

        return rs;
    }

    @RequestMapping("/addStudent")
    public ResultVo<Object> addStudent(TStudent student) {
        Integer studentNo = student.getStudentNo();
        student.setStuPass(studentNo.toString());
        TStudent result = studentService.selectByNo(studentNo);
        ResultVo<Object> rs = new ResultVo<>();
        if(result == null){
            studentService.addStudent(student);
            rs.setCode(Constant.SUCCESS_RETUEN_CODE);
            rs.setMsg("增加学生信息成功");
        }else {
            rs.setCode(Constant.HASE_RETUEN_CODE);
            rs.setMsg("学号已存在");
        }
        return rs;
    }

    @RequestMapping("/updateStudent")
    public ResultVo<Object> updateStudent(TStudent student){
        Integer total = studentService.updateStudent(student);
        ResultVo<Object> rs = new ResultVo<>();
        if(total == null || total == 0){
            rs.setCode(Constant.FAILURE_RETUEN_CODE);
            rs.setMsg("修改学生信息失败");
        }else{
            rs.setCode(Constant.SUCCESS_RETUEN_CODE);
            rs.setMsg("修改学生信息成功");
        }
        return rs;
    }

    @RequestMapping("/deleteStudent")
    public ResultVo<Object> deleteStudent(@RequestParam("studentNo") int studentNO){
        Integer total = studentService.deleteStudent(studentNO);

        ResultVo<Object> rs = new ResultVo<>();
        if(null==total||0==total) {
            rs.setCode(Constant.FAILURE_RETUEN_CODE);
            rs.setMsg("修改学生信息失败");
        }else {
            rs.setCode(Constant.SUCCESS_RETUEN_CODE);
            rs.setMsg("修改学生信息成功");
        }
        return rs;
    }

    @RequestMapping("/studentSelect")
    public ResultVo<List<TStudent>> studentSelect(){
        ResultVo<List<TStudent>> rs = new ResultVo<>();
        List<TStudent> list = studentService.selectAllStudent();
        rs.setCode(Constant.SUCCESS_RETUEN_CODE);
        rs.setMsg("查询成功");
        rs.setData(list);
        long total = list.size();
        rs.setCount(total);

        return rs;
    }
}
