package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /*查询部门*/
    @GetMapping
    public Result selectAll(){
        log.info("查询所有部门数据");
        List<Dept> deptList = deptService.selectAll();

        return Result.success(deptList);
    }

    /*删除部门*/
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) throws Exception {
        deptService.deleteById(id);
        return Result.success();
    }

    /*新增部门*/
    @PostMapping
    public Result insert(@RequestBody Dept dept){
        deptService.insert(dept);
        return Result.success();
    }

    /*根据id查询部门*/
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){
        log.info("根据id查询部门：{}",id);
        Dept dept = deptService.selectById(id);

        if (dept==null){
            return Result.error("查询失败，请重试");
        }else {
            return Result.success(dept);

        }
    }

    /*修改部门*/
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("更新部门：{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
