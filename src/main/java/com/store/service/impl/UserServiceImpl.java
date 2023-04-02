package com.store.service.impl;

import com.store.entity.User;
import com.store.mapper.UserMapper;
import com.store.service.IUserService;
import com.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(User user) {
        User res = userMapper.findByUsername(user.getUsername());
        if (res != null) throw new UsernameDuplicatedException("该用户名已存在");

        //md5算法加密
        String salt = UUID.randomUUID().toString().toUpperCase();
        user.setPassword(getMD5Password(user.getPassword(), salt));
        user.setSalt(salt);

        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        Integer rows = userMapper.insert(user);
        if (rows != 1) throw new InsertException("注册过程中产生了未知的异常");
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);

        if (user == null || user.getIsDelete() == 1) throw new UsernameNotFoundException("用户数据不存在");

        password = getMD5Password(password, user.getSalt());
        if (!password.equals(user.getPassword())) throw new PasswordNotMatchException("用户密码错误");

        User res = new User();
        res.setUid(user.getUid());
        res.setUsername(user.getUsername());
        res.setAvatar(user.getAvatar());

        return res;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User res = userMapper.findByUid(uid);
        if (res == null || res.getIsDelete() == 1) throw new UsernameNotFoundException("用户数据不存在");
        if (!res.getPassword().equals(getMD5Password(oldPassword, res.getSalt()))) throw new PasswordNotMatchException("密码错误");
        Integer rows = userMapper.updatePasswordByUidInteger(uid, getMD5Password(newPassword, res.getSalt()), username, new Date());
        if (rows != 1) throw new UpdateException("更新数据时发生未知的异常");
    }

    @Override
    public User findByUid(Integer uid) {
        User user = userMapper.findByUid(uid);
        if (user == null || user.getIsDelete() == 1) throw new UsernameNotFoundException("用户不存在");
        User res = new User();
        res.setUsername(user.getUsername());
        res.setPhone(user.getPhone());
        res.setEmail(user.getEmail());
        res.setGender(user.getGender());
        return res;
    }

    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User res = userMapper.findByUid(uid);
        if (res == null || res.getIsDelete() == 1) throw new UsernameNotFoundException("用户不存在");
        user.setUid(uid);
        user.setUsername(username);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateInfoByUid(user);
        if (rows != 1) throw new UpdateException("更新数据时发生未知的异常");
    }

    @Override
    public void changeAvatar(Integer uid, String username, String avatar) {
        User res = userMapper.findByUid(uid);
        if (res == null || res.getIsDelete() == 1) throw new UsernameNotFoundException("用户不存在");
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
        if (rows != 1) throw new UpdateException("更新用户头像时发生未知异常");
    }

    /**
     * 加密密码
     * @param password 用户密码
     * @param salt 盐值
     * @return 加密后的密码
     */
    private String getMD5Password(String password, String salt){
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
