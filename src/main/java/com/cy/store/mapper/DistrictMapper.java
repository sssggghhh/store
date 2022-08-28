package com.cy.store.mapper;

import com.cy.store.entity.District;

import java.util.List;

/**
 * @Auther: su_gh
 * @Date: 2022 - 08 - 21 - 0:04
 * @Description: com.cy.store.mapper
 * @Vsersion: 1.0
 */
public interface DistrictMapper {
    /**
     * 获取全国所有省/某省所有市/某市所有区县
     * @param parent 父级代号，当获取某市所有区时，使用市的代号；当获取省所有市时，使用省的代号；当获取全国所有省时，使用"86"作为父级代号
     * @return 全国所有省/某省所有市/某市所有区的列表
     */
    List<District> findByParent(String parent);

    /**
     * 根据code查询省/市/县的名称
     * @param code
     * @return
     */
    String findNameByCode(String code);


}
