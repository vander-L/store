package com.store.service;

import com.store.entity.District;

import java.util.List;

public interface IDistrictService {

    /**
     * 根据父代号查询区域信息
     * @param parent 父代号
     * @return 所查询到的区域列表
     */
    List<District> getByParent(String parent);

    /**
     * 根据code来获取地区名
     * @param code code
     * @return 地区名
     */
    String getNameByCode(String code);
}
