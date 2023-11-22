package com.itheima.service;

import com.itheima.pojo.DeptLog;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface DeptLogService {
    void insert(DeptLog deptLog);
}
