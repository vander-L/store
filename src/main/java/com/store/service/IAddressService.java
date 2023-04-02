package com.store.service;

import com.store.entity.Address;

import java.util.List;

public interface IAddressService {
    /**
     * 添加收货地址
     * @param uid 用户uid
     * @param username 用户名
     * @param address 收货地址信息
     */
    void addNewAddress(Integer uid, String username, Address address);

    /**
     * 通过uid获取收货地址
     * @param uid 用户uid
     * @return 收货地址列表
     */
    List<Address> getAddressByUid(Integer uid);

    /**
     * 修改用户的一个收货地址为默认收货地址
     * @param aid 收货地址id
     * @param uid 用户id
     * @param username 用户名
     */
    void setAddressDefault(Integer aid, Integer uid, String username);

    /**
     * 删除收货地址数据
     * @param aid 收货地址id
     * @param uid 用户id
     * @param username 用户名
     */
    void deleteAddress(Integer aid, Integer uid, String username);

    /**
     * 根据收货地址数据的id，查询收货地址详情
     * @param aid 收货地址id
     * @param uid 归属的用户id
     * @return 匹配的收货地址详情
     */
    Address getByAid(Integer aid, Integer uid);
}
