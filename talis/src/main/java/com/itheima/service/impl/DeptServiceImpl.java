package com.itheima.service.impl;

import com.itheima.mapper.DeptLogMapper;
import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.DeptLog;
import com.itheima.pojo.Result;
import com.itheima.service.DeptLogService;
import com.itheima.service.DeptService;
import com.itheima.service.EmpService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpService empService;

    @Autowired
    private DeptLogService deptLogService;

    /*查询所有部门信息*/
    public List<Dept> selectAll() {
        List<Dept> deptList = deptMapper.selectAll();
        return deptList;
    }

    /*根据id删除部门*/
    @Transactional(rollbackFor = Exception.class)
//    事务的传播行为属性propagation=required(默认的)/required_new/support/not_support...
    public void deleteById(Integer id) throws Exception{

        try {
            deptMapper.deleteById(id);
            //并删除该部门下的员工
            empService.deleteByDeptId(id);
        } finally {
            //调用其他业务类中的方法，无论是否有异常，最终都要执行以下代码往数据库中记录日志
            //解决方案：加事务（传播行为为无论当前是否有事务，都创建一个新事务）
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作，此时解散的是"+id+"号部门");

            deptLogService.insert(deptLog );
        }

    }

    /*新增部门*/
    public void insert(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);

    }

    /*根据id查询*/
    public Dept selectById(Integer id) {
        Dept dept = deptMapper.selectById(id);
        return dept;
    }

    /*更新部门*/
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }

}
