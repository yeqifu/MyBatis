package com.yeqifu.dao;

import com.yeqifu.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: 落亦-
 * @Date: 2020/3/28 17:48
 */
public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user")
    List<User> findAll();

}
