package com.store.mapper;

import com.store.entity.Address;

import java.util.Date;
import java.util.List;

public interface AddressMapper {
    /**
     * 插入用户的收货地址数据
     * @param address 收获地址的数据
     * @return 修改的行数
     */
    Integer insert(Address address);

    /**
     * 根据用户uid统计收货地址数量
     * @param uid 用户uid
     * @return 当前用户的收货地址数量
     */
    Integer getCountByUid(Integer uid);

    /**
     * 根据uid查询收货地址
     * @param uid 用户uid
     * @return 收货地址列表
     */
    List<Address> findAddressByUid(Integer uid);

    /**
     * 根据aid查询收货地址
     * @param aid 收货地址id
     * @return 查询到的收货地址数据
     */
    Address findAddressByAid(Integer aid);

    /**
     * 根据用户的uid值设置用户收货地址为非默认
     * @param uid 用户的id
     * @return 受影响的行数
     */
    Integer updateNoDefault(Integer uid);

    /**
     * 根据aid值修改收货地址为默认
     * @param aid 收货地址id
     * @param modifiedUser 修改者
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    Integer updateDefaultByAid(Integer aid, String modifiedUser, Date modifiedTime);

    /**
     * 根据收货地址id删除收货地址数据
     * @param aid 收货地址id
     * @return 受影响的行数
     */
    Integer deleteAddressByAid(Integer aid);

    /**
     * 根据uid来查找最后一条修改的记录
     * @param uid 用户id
     * @return 最后一条记录
     */
    Address findLastModifiedByUid(Integer uid);

    /**
     * 根据aid查询收货地址
     * @param aid 收货地址id
     * @return 收货地址
     */
    Address findByAid(Integer aid);
}
