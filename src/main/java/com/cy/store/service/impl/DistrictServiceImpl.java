package com.cy.store.service.impl;

import com.cy.store.entity.District;
import com.cy.store.mapper.DistrictMapper;
import com.cy.store.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: su_gh
 * @Date: 2022 - 08 - 21 - 11:13
 * @Description: com.cy.store.service.impl
 * @Vsersion: 1.0
 */
@Service
public class DistrictServiceImpl implements IDistrictService {
    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public List<District> getByParent(String parent) {
        List<District> list = districtMapper.findByParent(parent);

        //在进行网络数据传输时，为了尽量避免无效的数据传输，可以将不需要的数据设置为null
        for (District district : list) {
            district.setId(null);
            district.setParent(null);
        }
        return list;
    }

    @Override
    public String getNameByCode(String code) {

        String name = districtMapper.findNameByCode(code);
        return name;
    }

}
