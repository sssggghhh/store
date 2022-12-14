package com.cy.store.service;

import com.cy.store.entity.District;

import java.util.List;

/**
 * @Auther: su_gh
 * @Date: 2022 - 08 - 21 - 11:06
 * @Description: com.cy.store.service
 * @Vsersion: 1.0
 * 处理省/市/区数据的业务层接口
 */
public interface IDistrictService {
    /**
     * 获取全国所有省/某省所有市/某市所有区
     * @param parent parent 父级代号，当获取某市所有区时，使用市的代号；
     * 当获取某省所有市时，使用省的代号；当获取全国所有省时，使用"86"作为父级代号
     * @return 全国所有省/某省所有市/某市所有区的列表
     */
    List<District> getByParent(String parent);

    /**
     * 根据code获取省/市/县名称
     * @param code 代码
     * @return 名称
     */
    String getNameByCode(String code);
}
