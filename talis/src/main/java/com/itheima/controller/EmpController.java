package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/emps")
@Slf4j
public class EmpController {
    @Autowired
    private EmpService empService;
/*

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("分页查询员工：{},{}" ,page,pageSize);
        PageBean pageBean = empService.page(page, pageSize);
        return Result.success(pageBean);
    }
*/

    /*条件分页查询*/
    //    条件分页查询
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1")Integer page,
                       @RequestParam(defaultValue = "10")Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
//        记录日志
        log.info("分页查询，参数：{},{},{},{},{},{}", page, pageSize,name, gender, begin, end);
//        调用业务层分页查询功能
        PageBean pageBean= empService.page(page,pageSize,name, gender, begin, end);
//       响应数据
        return Result.success(pageBean);
    }


    /*根据id删除员工(支持批量删除)*/
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        empService.delete(ids);
        return Result.success();
    }

    /*新增员工*/
    @PostMapping
    public Result insert(@RequestBody Emp emp){
        empService.insert(emp);
        return Result.success();
    }

    /*根据id查询员工*/
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){
        Emp emp = empService.selectById(id);
        return Result.success(emp);
    }

    /*修改员工*/
    @PutMapping
    public Result update(@RequestBody Emp emp){
        empService.update(emp);
        return Result.success();
    }
}
