package com.ivy.springcloud.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
//链式写法
@Accessors(chain = true)
public class Dept implements Serializable {
    //主键
    private int deptno;
    private String dname;
    //数据存在哪个数据库
    private String db_source;

    public Dept(String dname) {
        this.dname = dname;
    }
}
