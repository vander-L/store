package com.store.mapper;

import com.store.entity.User;

import java.util.Date;

public interface UserMapper {
    /**
     * 插入用户数据
     * @param user 用户的数据
     * @return 受影响的行数
     */
    Integer insert(User user);

    /**
     * 根据用户名来查询用户数据
     * @param username 用户名
     * @return 所查询到的用户数据
     */
    User findByUsername(String username);

    /**
     * 根据用户的uid来修改密码
     * @param uid 用户的uid
     * @param password 用户修改后的密码
     * @param modifiedUser 数据修改人
     * @param modifiedTime 数据修改时间
     * @return 修改的行数
     */
    Integer updatePasswordByUidInteger(Integer uid, String password, String modifiedUser, Date modifiedTime);

    /**
     * 根据用户uid来查询用户数据
     * @param uid 用户的uid
     * @return 所查询到的用户数据
     */
    User findByUid(Integer uid);

    /**
     * 通过uid更新用户基本信息
     * @param user 用户对象
     * @return 修改的行数
     */
    Integer updateInfoByUid(User user);

    /**
     * 根据用户uid修改用户头像
     * @param uid 用户uid
     * @param avatar 图片文件位置
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @return 修改的行数
     */
    Integer updateAvatarByUid(Integer uid, String avatar, String modifiedUser, Date modifiedTime);
}
