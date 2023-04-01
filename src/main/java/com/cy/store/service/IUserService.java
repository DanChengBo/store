package com.cy.store.service;

import com.cy.store.entity.User;

/**
 * Created by Intellij IDEA.
 * User: 17129
 * Date: 2023/2/13
 * 用户模块业务层接口
 */
public interface IUserService {
    /**
     * 用户注册方法
     * @param user 用户的数据
     */
    void reg(User user);

    /**
     *  用户登录功能
     * @param username
     * @param password
     * @return 当前用户的数据，如果没有则返回null值
     */
    User login(String username,String password);
}

























































