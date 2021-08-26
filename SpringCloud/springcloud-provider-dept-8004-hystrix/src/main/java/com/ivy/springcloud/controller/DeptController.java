package com.ivy.springcloud.controller;

import com.ivy.springcloud.pojo.Dept;
import com.ivy.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @HystrixCommand(fallbackMethod = "hystrixGet")
    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id") int id) {
        Dept dept = deptService.queryById(id);
        if (dept == null) {
            throw new RuntimeException("id => " + id + "，不存在该用户或者信息无法找到……");
        }
        return dept;
    }

    @PostMapping("/dept/add")
    public boolean addDept(Dept dept) {
        return deptService.addDept(dept);
    }

    @GetMapping("/dept/list")
    public List<Dept> queryAll() {
        return deptService.queryAll();
    }

    /**
     * 备选方案
     */
    public Dept hystrixGet(@PathVariable("id") int id) {
        return new Dept()
                .setDeptno(id)
                .setDname("id => " + id + "，不存在该用户或者信息无法找到……")
                .setDb_source("no this database in MySql");
    }

}
