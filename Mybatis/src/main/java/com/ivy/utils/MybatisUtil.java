package com.ivy.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * MybatisUtil
 * SqlSessionFactory --> SqlSession
 *
 * @Author: ivy
 * @CreateTime: 2021-08-13
 */
public class MybatisUtil {
     static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            //获取SQlSessionFactory
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取SqlSession
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
