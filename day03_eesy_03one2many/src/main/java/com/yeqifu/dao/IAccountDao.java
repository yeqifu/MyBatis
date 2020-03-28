package com.yeqifu.dao;

import com.yeqifu.domain.Account;
import com.yeqifu.domain.AccountUser;

import java.util.List;

public interface IAccountDao {

    /**
     * 查询所有账号，同时还要获取到当前账户的所属用户信息
     * @return
     */
    List<Account> findAll();

    /**
     * 查询所有用户，包含地址和用户名信息
     * @return
     */
    List<AccountUser> findAllAccount();
}
