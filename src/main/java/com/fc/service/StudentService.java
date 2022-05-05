package com.fc.service;

import com.fc.entity.TStudent;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface StudentService {

    PageInfo<TStudent> getAllStudent(TStudent student, int page, int limit);

    TStudent selectByNo(Integer studentNo);

    Integer addStudent(TStudent student);

    Integer updateStudent(TStudent student);

    Integer deleteStudent(int studentNO);

    List<TStudent> selectAllStudent();

    List<TStudent> selectloginStudent(TStudent student);
}
