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
public class DistrictMapperTest {
    @Autowired
    private DistrictMapper districtMapper;
    @Test
    public void findByParent(){
        districtMapper.findByParent("110100").forEach(System.out::println);
    }

    @Test
    public void findNameByCode(){
        System.out.println(districtMapper.findNameByCode("110103"));
    }
}
