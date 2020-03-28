package com.yeqifu.dao;

import com.yeqifu.entity.User;

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
    List<User> findAll();

}
