package com.ivy.springcloud.service;

import com.ivy.springcloud.pojo.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Component
/**
 * value : 微服务的Application Name
 * 客户端（80端口）需要调用的 微服务
 */
@FeignClient(value = "SPRINGCLOUD-PROVIDER-DEPT", fallbackFactory = DeptClientServiceFallbaclFactory.class)
public interface DeptClientService {
    @GetMapping("/dept/get/{id}")
    public Dept queryById(@PathVariable("id") int id);

    @GetMapping("/dept/list")
    public List<Dept> queryAll();

    @PostMapping("dept/add")
    public boolean addDept(Dept dept);
}
