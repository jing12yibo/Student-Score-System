package com.fc.service;

import com.fc.entity.TStudent;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TStudentService {
    PageInfo<TStudent> getAll(TStudent student, int page, int limit);

    TStudent selectByNo(Integer studentNo);


    void addStudent(TStudent student);


    Integer updateStudent(TStudent student);

    Integer deleteStudent(int studentNo);

    List<TStudent> selectAllStudent();


    List<TStudent> selectloginStudent(TStudent student);

}
