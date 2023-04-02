package com.store.service;

import com.store.entity.User;

public interface IUserService {
    /**
     * 用户注册方法
     * @param user 用户数据
     */
    void register(User user);

    /**
     * 用户登录方法
     * @param username 用户名
     * @param password 密码
     * @return 返回查询到的用户数据
     */
    User login(String username, String password);

    /**
     * 修改用户密码
     * @param uid 用户的uid
     * @param username 用户名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    void changePassword(Integer uid, String username, String oldPassword, String newPassword);

    /**
     * 根据用户uid查询用户数据
     * @param uid 用户uid
     * @return 查询到的用户数据
     */
    User findByUid(Integer uid);

    /**
     * 更新用户信息
     * @param uid session中存的uid
     * @param username session中存的username
     * @param user 用户的数据
     */
    void changeInfo(Integer uid, String username, User user);

    /**
     * 修改用户头像
     * @param uid 用户uid
     * @param username 用户名
     * @param avatar 用户头像路径
     */
    void changeAvatar(Integer uid, String username, String avatar);
}
