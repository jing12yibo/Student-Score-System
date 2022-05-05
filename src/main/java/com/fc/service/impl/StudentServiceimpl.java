package com.fc.service.impl;

import com.fc.dao.TStudentMapper;
import com.fc.entity.TStudent;
import com.fc.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceimpl implements StudentService {
    @Autowired
    private TStudentMapper studentMapper;
    @Override
    public PageInfo<TStudent> getAllStudent(TStudent student, int page, int size) {
        PageHelper.startPage(page,size);
        List<TStudent> list = studentMapper.selectAll(student);
        PageInfo<TStudent> pageInfo = new PageInfo<>();
        return pageInfo;
    }

    @Override
    public TStudent selectByNo(Integer studentNo) {
        return studentMapper.selectByPrimaryKey(studentNo);

    }

    @Override
    public Integer addStudent(TStudent student) {
        return  studentMapper.insert(student);

    }

    @Override
    public Integer updateStudent(TStudent student) {
        return studentMapper.updateByPrimaryKey(student);
    }

    @Override
    public Integer deleteStudent(int studentNO) {
        return studentMapper.deleteByPrimaryKey(studentNO);

    }

    @Override
    public List<TStudent> selectAllStudent() {
        return studentMapper.selectAll(null);
    }

    public List<TStudent> selectloginStudent(TStudent student){
        return studentMapper.selectloginStudent(student);
    }

}
