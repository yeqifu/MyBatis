package com.yeqifu.dao.impl;

import com.yeqifu.dao.IUserDao;
import com.yeqifu.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @Author: 落亦-
 * @Date: 2020/3/28 21:19
 */
public class UserDaoImpl implements IUserDao {

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory){
        this.factory = factory;
    }

    @Override
    public List<User> findAll() {
        //1、使用工厂创建SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //2、使用session执行查询所有方法
        List<User> users = sqlSession.selectList("com.yeqifu.dao.IUserDao.findAll");
        sqlSession.close();
        //3、返回查询结果
        return users;
    }
}
