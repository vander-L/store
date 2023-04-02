package com.store.controller;

import com.store.entity.Address;
import com.store.service.IAddressService;
import com.store.service.IDistrictService;
import com.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.store.util.Const.*;

@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController{
    @Autowired
    private IAddressService addressService;

    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session){
        addressService.addNewAddress(getUidFromSession(session), getUsernameFromSession(session), address);
        return new JsonResult<>(OK);
    }

    @RequestMapping({"", "/"})
    public JsonResult<List<Address>> getAddressByUid(HttpSession session){
        return new JsonResult<>(OK, addressService.getAddressByUid(getUidFromSession(session)));
    }

    @RequestMapping("set_default/{aid}")
    public JsonResult<Void> setAddressDefault(@PathVariable("aid") Integer aid, HttpSession session){
        addressService.setAddressDefault(aid, getUidFromSession(session), getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }

    @RequestMapping("delete/{aid}")
    public JsonResult<Void> deleteAddress(@PathVariable("aid") Integer aid, HttpSession session){
        addressService.deleteAddress(aid, getUidFromSession(session), getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }
 }
