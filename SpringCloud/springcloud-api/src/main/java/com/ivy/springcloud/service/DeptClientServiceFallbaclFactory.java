package com.ivy.springcloud.service;

import com.ivy.springcloud.pojo.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 服务降级
 */
@Component
public class DeptClientServiceFallbaclFactory implements FallbackFactory {

    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public Dept queryById(int id) {
                return new Dept()
                        .setDeptno(id)
                        .setDname("id => " + id + "没有对应的信息，客户端提供了服务降级的操作，当前服务也被关闭")
                        .setDb_source("没有数据～");

            }

            @Override
            public List<Dept> queryAll() {
                return null;
            }

            @Override
            public boolean addDept(Dept dept) {
                return false;
            }
        };
    }
}
