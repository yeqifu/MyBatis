package com.yeqifu.test;

import com.yeqifu.dao.IRoleDao;
import com.yeqifu.domain.Role;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class RoleTest {

    private InputStream in;
    private SqlSession sqlsession;
    private IRoleDao roleDao;

    @Before
    public void init()  throws Exception {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产SqlSession对象
        sqlsession = factory.openSession(true);
        //4.使用SqlSession创建Dao接口的代理对象
        roleDao = sqlsession.getMapper(IRoleDao.class);
    }

    @After
    public void destory() throws Exception{

        //6.释放资源
        sqlsession.close();
        in.close();
    }

    /**
     * 查询所有
     */
    @Test
    public void testFindAll(){
        List<Role> roles = roleDao.findAll();
        for (Role role : roles){
            System.out.println("------每个角色的信息--------");
            System.out.println(role);
            System.out.println(role.getUsers());
        }
    }

}
