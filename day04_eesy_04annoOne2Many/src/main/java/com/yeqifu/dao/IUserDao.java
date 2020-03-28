package com.yeqifu.dao;

import com.yeqifu.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user")
    @Results(id="userMap", value = {
            @Result(id=true,column = "id",property = "userId"),
            @Result(column = "username",property = "userName"),
            @Result(column = "address",property = "userAddress"),
            @Result(column = "sex",property = "userSex"),
            @Result(column = "birthday",property = "userBirthday"),
    })
    List<User> findAll();


    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    @Select("select * from user where id = #{id}")
    @ResultMap(value = {"userMap"})
    User findById(Integer userId);


    /**
     * 根据用户名字模糊查询用户
     * @param username
     * @return
     */
    @Select("select * from user where username like #{username} ")
    @ResultMap(value = {"userMap"})
    List<User> findUserByName(String username);

}
