package com.store.service.impl;

import com.store.entity.Address;
import com.store.mapper.AddressMapper;
import com.store.mapper.DistrictMapper;
import com.store.service.IAddressService;
import com.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.store.util.Const.ADDRESS_MAX_COUNT;

@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        Integer count = addressMapper.getCountByUid(uid);
        if (count > ADDRESS_MAX_COUNT) throw new AddressCountLimitException("用户收货地址超出上限");
        address.setUid(uid);
        address.setIsDefault(count == 0 ? 1 : 0);
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setProvinceName(districtMapper.findNameByCode(address.getProvinceCode()));
        address.setAreaName(districtMapper.findNameByCode(address.getAreaCode()));
        address.setCityName(districtMapper.findNameByCode(address.getCityCode()));
        Date date = new Date();
        address.setCreatedTime(date);
        address.setModifiedTime(date);
        Integer rows = addressMapper.insert(address);
        if (rows != 1) throw new InsertException("插入数据时发生未知的异常");
    }

    @Override
    public List<Address> getAddressByUid(Integer uid) {
        return addressMapper.findAddressByUid(uid);
    }

    @Override
    public void setAddressDefault(Integer aid, Integer uid, String username) {
        Address address = addressMapper.findAddressByAid(aid);
        if (address == null) throw new AddressNotFoundException("收货地址不存在");
        if (!address.getUid().equals(uid)) throw new AccessDeniedException("非法访问");
        Integer rows = addressMapper.updateNoDefault(uid);
        if (rows == 0) throw new UpdateException("更新数据产生未知的异常");
        rows = addressMapper.updateDefaultByAid(aid, username, new Date());
        if (rows != 1) throw new UpdateException("更新数据产生未知的异常");
    }

    @Override
    public void deleteAddress(Integer aid, Integer uid, String username) {
        Address address = addressMapper.findAddressByAid(aid);
        if (address == null) throw new AddressNotFoundException("收货地址不存在");
        if (!address.getUid().equals(uid)) throw new AccessDeniedException("非法访问");
        Integer rows = addressMapper.deleteAddressByAid(aid);
        if (rows != 1) throw new DeleteException("删除数据时发生未知的异常");
        Integer count = addressMapper.getCountByUid(uid);
        if (count == 0) return;
        if (address.getIsDefault() == 1){
            rows = addressMapper.updateDefaultByAid(addressMapper.findLastModifiedByUid(uid).getAid(), username, new Date());
            if (rows != 1) throw new UpdateException("更新数据产生未知的异常");
        }
    }

    @Override
    public Address getByAid(Integer aid, Integer uid) {
        // 根据收货地址数据id，查询收货地址详情
        Address address = addressMapper.findByAid(aid);

        if (address == null) {
            throw new AddressNotFoundException("尝试访问的收货地址数据不存在");
        }
        if (!address.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }
        address.setProvinceCode(null);
        address.setCityCode(null);
        address.setAreaCode(null);
        address.setCreatedUser(null);
        address.setCreatedTime(null);
        address.setModifiedUser(null);
        address.setModifiedTime(null);
        return address;
    }
}
