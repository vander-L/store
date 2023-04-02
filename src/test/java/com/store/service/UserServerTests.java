package com.store.service;

import com.store.entity.User;
import com.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServerTests {

    @Autowired
    private IUserService userService;

    @Test
    public void register(){
        try {
            User user = new User();
            user.setUsername("connery");
            user.setPassword("123456");
            userService.register(user);
            System.out.println("OK");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login(){
        User user = userService.login("connery", "123456");
        System.out.println(user);
    }

    @Test
    public void changePassword(){
        userService.changePassword(10, "connery", "123123", "123456");
    }

    @Test
    public void findByUid(){
        System.out.println(userService.findByUid(10));
    }

    @Test
    public void changeInfo(){
        User user = new User();
        user.setPhone("17267610752");
        user.setGender(0);
        user.setModifiedUser("connery");
        user.setModifiedTime(new Date());
        userService.changeInfo(10, "connery", user);
    }

    @Test
    public void changeAvatar(){
        userService.changeAvatar(10, "connery", "11111");
    }
}
