package com.yeqifu.test;

import com.yeqifu.dao.IUserDao;
import com.yeqifu.dao.impl.UserDaoImpl;
import com.yeqifu.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {

    private InputStream in;
    private SqlSession sqlsession;
    private IUserDao userDao;

    @Before
    public void init()  throws Exception {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂对象创建dao对象
        userDao = new UserDaoImpl(factory);
    }

    @After
    public void destory() throws Exception{
        //6.释放资源
        in.close();
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll(){
        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user: users) {
            System.out.println(user);
        }
    }

    /**
     * 测试插入
     */
    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("幼稚园杀手");
        user.setAddress("上海");
        user.setSex("男");
        user.setBirthday(new Date());
        System.out.println("保存之前："+user);
        //5.执行保存方法
        userDao.saveUser(user);
        System.out.println("保存操作之后："+user);
    }

    /**
     * 测试更新
     */
    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(42);
        user.setUsername("小二王");
        user.setAddress("上海");
        user.setSex("女");
        user.setBirthday(new Date());
        //5.执行保存方法
        userDao.updateUser(user);
    }

    /**
     * 测试删除
     */
    @Test
    public void testDelete(){
        //5.执行删除方法
        userDao.deleteUser(48);
    }

    /**
     * 测试查询一个
     */
    @Test
    public void testFindOne(){
        //5.执行查询一个
        User user = userDao.findById(51);
        System.out.println(user);
    }

    /**
     * 测试根据username模糊查询
     */
    @Test
    public void testFindByName(){
        List<User> users = userDao.findByName("%王%");
        for (User user:users){
            System.out.println(user);
        }
    }

    @Test
    public void testFindTotal(){
        int count = userDao.findTotal();
        System.out.println(count);
    }

}
