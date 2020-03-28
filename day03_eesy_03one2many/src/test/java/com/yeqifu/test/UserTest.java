package com.yeqifu.test;

import com.yeqifu.dao.IAccountDao;
import com.yeqifu.dao.IUserDao;
import com.yeqifu.domain.Account;
import com.yeqifu.domain.AccountUser;
import com.yeqifu.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class UserTest {

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
     * 查询所有
     */
    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println("-------每个用户的信息--------");
            System.out.println(user);
            System.out.println(user.getAccounts());;
        }
    }


}
