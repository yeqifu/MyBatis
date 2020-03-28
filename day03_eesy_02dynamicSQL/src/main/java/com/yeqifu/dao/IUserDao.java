package com.yeqifu.dao;

import com.yeqifu.domain.QueryVo;
import com.yeqifu.domain.User;

import java.util.List;

/**
 * 用户持久层接口
 */
public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 根据id查询用户信息
     * @param userId
     * @return
     */
    User findById(Integer userId);

    /**
     * 根据名称模糊查询用户信息
     * @param username
     * @return
     */
    List<User> findByName(String username);


    /**
     * 根据queryVo中的条件查询用户
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);

    /**
     * 根据传入的参数条件
     * @param user 查询的条件：用户名，性别，地址，也可能都有
     * @return
     */
    List<User> findUserByCondition(User user);

    /**
     * 根据QueryVo中提供的id集合，查询用户的信息
     * @param vo
     * @return
     */
    List<User> findUserInIds(QueryVo vo);
}
