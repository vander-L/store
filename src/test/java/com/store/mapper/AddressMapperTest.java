package com.store.mapper;

import com.store.entity.Address;
import com.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressMapperTest {
    @Autowired
    private AddressMapper addressMapper;
    @Test
    public void insert(){
        Address address = new Address();
        address.setUid(23);
        address.setPhone("13544778899");
        address.setName("keven");
        System.out.println(addressMapper.insert(address));
    }

    @Test
    public void getCountByUid(){
        System.out.println(addressMapper.getCountByUid(23));
    }

    @Test
    public void findAddressByUid(){
        addressMapper.findAddressByUid(10).forEach(System.out::println);
    }
}
