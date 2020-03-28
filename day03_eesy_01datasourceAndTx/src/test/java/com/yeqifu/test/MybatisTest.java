package com.yeqifu.test;

import com.yeqifu.dao.IUserDao;
import com.yeqifu.domain.QueryVo;
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
        //3.使用工厂生产SqlSession对象
        sqlsession = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        userDao = sqlsession.getMapper(IUserDao.class);
    }

    @After
    public void destory() throws Exception{
        //提交事务
        sqlsession.commit();
        //6.释放资源
        sqlsession.close();
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
     * 测试保存用户
     */
    @Test
    public void testSave(){
        User user = new User();
        user.setUserName("佳上");
        user.setUserAddress("九江市濂溪区");
        user.setUserSex("男");
        user.setUserBirthday(new Date());
        System.out.println("保存操作之前："+user);
        //5.执行保存方法
        userDao.saveUser(user);
        System.out.println("保存操作之后："+user);
    }

    /**
     * 测试更新用户
     */
    @Test
    public void testUpdate(){
        User user = new User();
        user.setUserId(49);
        user.setUserName("落亦-旧城");
        user.setUserAddress("九江市濂溪区");
        user.setUserSex("男");
        user.setUserBirthday(new Date());
        //5.执行保存方法
        userDao.updateUser(user);
    }

    /**
     * 测试删除方法
     */
    @Test
    public void testDelete(){
        //5.执行删除方法
        userDao.deleteUser(43);
    }

    /**
     * 测试查询一个的方法
     */
    @Test
    public void testFindOne(){
        //5.执行查询一个的方法
        User user = userDao.findById(49);
        System.out.println(user);
    }

    /**
     * 测试根据名称模糊查询的方法
     */
    @Test
    public void testFindByName(){
        //5.执行根据名称模糊查询的方法
        List<User> users = userDao.findByName("%王%");
        for (User user:users){
            System.out.println(user);
        }
    }

    /**
     * 测试查询总记录条数
     */
    @Test
    public void testFindTotal(){
        //5.执行查询总记录条数
        int count=userDao.findTotal();
        System.out.println(count);
    }

    /**
     * 测试使用QueryVo作为查询条件
     */
    @Test
    public void testFindUserByVo(){
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUserName("%王%");
        vo.setUser(user);
        //5.执行根据名称模糊查询的方法
        List<User> users = userDao.findUserByVo(vo);
        for (User u:users){
            System.out.println(u);
        }
    }
}
