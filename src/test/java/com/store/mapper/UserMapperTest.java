package com.store.mapper;

import com.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void insert(){
        User user = new User();
        user.setUsername("connery");
        user.setPassword("123456");
        System.out.println(userMapper.insert(user));
    }

    @Test
    public void findByUsername(){
        System.out.println(userMapper.findByUsername("connery"));
    }

    @Test
    public void updatePasswordByUidInteger(){
        System.out.println(userMapper.updatePasswordByUidInteger(4, "123321", "yjb", new Date()));
    }

    @Test
    public void findByUid(){
        System.out.println(userMapper.findByUid(10));
    }

    @Test
    public void updateInfoByUid(){
        User user = new User();
        user.setUid(10);
//        user.setPhone("15270112260");
        user.setEmail("1514845104@qq.com");
        user.setGender(1);
        user.setModifiedUser("connery");
        user.setModifiedTime(new Date());
        System.out.println(userMapper.updateInfoByUid(user));
    }

    @Test
    public void updateAvatarByUid(){
        System.out.println(userMapper.updateAvatarByUid(10, "00000", "connery", new Date()));
    }
}
