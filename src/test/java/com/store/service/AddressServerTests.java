package com.store.service;

import com.store.entity.Address;
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
public class AddressServerTests {

    @Autowired
    private IAddressService addressService;

    @Test
    public void addNewAddress(){
        Address address = new Address();
        address.setPhone("15266553322");
        address.setName("mike");
        addressService.addNewAddress(23, "connery", address);
    }

    @Test
    public void setAddressDefault(){
        addressService.setAddressDefault(5, 10, "yq");
    }

    @Test
    public void delete(){
        addressService.deleteAddress(3, 10, "connery");
    }
}
