package com.itheima;

import ch.qos.logback.classic.Logger;
import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TalisApplicationTests {


    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptMapper deptMapper;


    /*根据id删除员工
     * */
   @Test
    public void testDelete(){
       List list=new ArrayList();
       list.add(17);
       list.add(18);
       empMapper.delete(list);
    }


    /*新增员工
     * */
    @Test
    public void testInsert(){
        Emp emp=new Emp();
        emp.setUsername("tom");
        emp.setName("汤姆");
        emp.setImage("1.jpg");
        emp.setGender((short)1);
        emp.setJob((short)1);
        emp.setEntrydate(LocalDate.of(2000,1,1));
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        emp.setDeptId(1);

        int i= empMapper.insert(emp);
        System.out.println("主键返回"+emp.getId());
        System.out.println("新增员工,影响数据"+i+"行");
    }


    /*修改员工
    * */

    @Test
    public void testUpdate(){
        Emp emp=new Emp(18,"jurry","123456","杰瑞",Short.valueOf("1"),"2.jpg",Short.valueOf("3"),LocalDate.now(),2,null,null);
        int i = empMapper.update(emp);
        System.out.println("修改员工,影响数据"+i+"行");

    }

    /*根据id查询员工
     * */
    @Test
    public void testSelectById(){
        Emp emp = empMapper.selectById(17);
        System.out.println(emp);
    }

    /*条件查询
     * */
/*
    @Test
    public void testSelect(){
        List<Emp> emp = empMapper.select("张",(short)1,LocalDate.of(2000,1,1),LocalDate.of(3000,1,1));

    }
*/

    /*-----------------------------部门管理相关功能测试---------------------------*/

    /*查询所有部门信息*/
/*    @Test
    public void testList(){
        List<Dept> deptList = deptMapper.list();
    }*/
}
