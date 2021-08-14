package com.ivy.test;

import com.ivy.dao.UserDao;
import com.ivy.pojo.User;
import com.ivy.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * UserDaoTest
 *
 * @Author: ivy
 * @CreateTime: 2021-08-13
 */
public class UserDaoTest {
    @Test
    public void test() {
        //第一步：获取SqlSession对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();

        //方式一：getMapper
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> userList1 = userDao.getUserList( );
        System.out.println(userList1);

        //方式二：
        List<User> userList2 = sqlSession.selectList("com.ivy.dao.UserDao.getUserList");
        System.out.println(userList2 );

        sqlSession.close();
    }
}
