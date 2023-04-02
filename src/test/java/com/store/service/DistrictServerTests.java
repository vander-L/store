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
public class DistrictServerTests {

    @Autowired
    private IDistrictService districtService;

    @Test
    public void getByParent(){
        districtService.getByParent("120100").forEach(System.out::println);
    }
}
