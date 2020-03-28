package com.yeqifu.dao.impl;

import com.yeqifu.dao.IUserDao;
import com.yeqifu.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author luoyi-
 */
public class UserDaoImpl implements IUserDao{

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory){
        this.factory=factory;
    }

    @Override
    public List<User> findAll() {
        //1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession中的方法，实现查询列表     参数就是能获取配置信息的key
        List<User> users = session.selectList("com.yeqifu.dao.IUserDao.findAll");
        //3.释放资源
        session.close();
        return users;
    }

    @Override
    public void saveUser(User user) {
        //1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用方法实现保存
        session.insert("com.yeqifu.dao.IUserDao.saveUser",user);
        //3.提交事务
        session.commit();
        //4.释放资源
        session.close();
    }

    @Override
    public void updateUser(User user) {
        //1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用方法实现保存
        session.update("com.yeqifu.dao.IUserDao.updateUser",user);
        //3.提交事务
        session.commit();
        //4.释放资源
        session.close();
    }

    @Override
    public void deleteUser(Integer userId) {
        //1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用方法实现保存
        session.delete("com.yeqifu.dao.IUserDao.deleteUser",userId);
        //3.提交事务
        session.commit();
        //4.释放资源
        session.close();
    }

    @Override
    public User findById(Integer userId) {
        //1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession中的方法，实现查询列表     参数就是能获取配置信息的key
        User user = session.selectOne("com.yeqifu.dao.IUserDao.findById",userId);
        //3.释放资源
        session.close();
        return user;
    }

    @Override
    public List<User> findByName(String username) {
        //1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession中的方法，实现查询列表     参数就是能获取配置信息的key
        List<User> users = session.selectList("com.yeqifu.dao.IUserDao.findByName",username);
        //3.释放资源
        session.close();
        return users;
    }

    @Override
    public int findTotal() {
        //1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession中的方法，实现查询列表     参数就是能获取配置信息的key
        Integer count = session.selectOne("com.yeqifu.dao.IUserDao.findTotal");
        //3.释放资源
        session.close();
        return count;
    }

}
