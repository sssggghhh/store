package com.cy.store.mapper;

import com.cy.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Auther: su_gh
 * @Date: 2022 - 08 - 21 - 0:11
 * @Description: com.cy.store.mapper
 * @Vsersion: 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictMapperTest {
    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void findByParent(){
        String parent = "110100";
        List<District> list = districtMapper.findByParent(parent);
        System.out.println("count="+list.size());
        for (District district : list) {
            System.out.println(district);
        }
    }
}
