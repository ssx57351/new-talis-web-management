package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
public interface EmpService {
/*

    */
/*分页查询员工*//*

    PageBean page(Integer page, Integer pageSize);
*/

    /*条件分页查询*/
    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    /*删除员工（支持批量删除）*/
    void delete(List<Integer> ids);

    /*新增员工*/
    void insert(Emp emp);

    /*根据id修改员工*/
    void update(Emp emp);

    /*根据id查询员工*/
    Emp selectById(Integer id);

    /*登录*/
    Emp login(Emp emp);

    /*根据部门id删除员工*/
    void deleteByDeptId(Integer id);
}
