package com.yeqifu;

import com.yeqifu.dao.IUserDao;
import com.yeqifu.dao.impl.UserDaoImpl;
import com.yeqifu.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @Author: 落亦-
 * @Date: 2020/3/28 17:54
 */
public class MyBatisTest {
    /**
     * 入门案例
     * @param args
     */
    public static void main(String[] args) throws Exception {
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂创建Dao对象
        IUserDao userDao = new UserDaoImpl(factory);
        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for(User user : users){
            System.out.println(user);
        }
        //6.释放资源
        in.close();
    }
}
