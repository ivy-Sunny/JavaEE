package com.ivy.springcloud.service;

import com.ivy.springcloud.pojo.Dept;

import java.util.List;


public interface DeptService {
    public boolean addDept(Dept dept);

    public Dept queryById(Integer id);

    public List<Dept> queryAll();
}
