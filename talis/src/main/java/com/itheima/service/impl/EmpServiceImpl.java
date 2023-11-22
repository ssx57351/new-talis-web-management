package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j

public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    /*条件分页查询*/
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
//        设置分页参数 page:第几页  pageSize:每页记录数
        PageHelper.startPage(page,pageSize);
//        执行分页查询
        List<Emp> empList = empMapper.list(page,pageSize,name, gender, begin, end);
//       获取分页结果
        Page<Emp> p = (Page<Emp>) empList;
//        封装PageBean对象
        PageBean pageBean=new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }

    /*删除员工（支持批量删除）*/
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    /*新增员工*/
    public void insert(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    /*修改员工*/
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    /*根据id查询员工*/
    public Emp selectById(Integer id) {
        Emp emp = empMapper.selectById(id);
        return emp;
    }

    /*登录*/
    public Emp login(Emp emp) {
        empMapper.getByUsernameAndPassword(emp);
        return emp;
    }

    /*根据部门id删除员工*/
    public void deleteByDeptId(Integer id) {
        empMapper.deleteByDeptId(id);
    }
}
