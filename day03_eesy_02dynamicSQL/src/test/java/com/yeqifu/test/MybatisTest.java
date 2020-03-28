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
import java.util.ArrayList;
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

    /**
     * 测试if,where标签的使用
     */
    @Test
    public void testFindByCondition(){
        User u = new User();
        u.setUserName("老王");
        //u.setUserSex("女");
        //5.执行查询一个的方法
        List<User> users = userDao.findUserByCondition(u);
        for (User user: users){
            System.out.println(user);
        }
    }


    @Test
    public void testFindInIds(){
        QueryVo vo = new QueryVo();
        List<Integer> list = new ArrayList<Integer>();
        list.add(41);
        list.add(42);
        list.add(45);
        vo.setIds(list);
        //5.执行查询一个的方法
        List<User> users = userDao.findUserInIds(vo);
        for (User user: users){
            System.out.println(user);
        }
    }

}
