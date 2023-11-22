package com.itheima.service;

import com.itheima.pojo.Dept;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(rollbackFor = Exception.class)
public interface DeptService {

    /*查询所有部门信息*/
    List<Dept> selectAll();

    /*根据id删除部门*/
    void deleteById(Integer id) throws Exception;

    /*新增部门*/
    void insert(Dept dept);

    /*根据id查询部门*/
    Dept selectById(Integer id);

    /*修改部门*/
     void update(Dept dept);
}
