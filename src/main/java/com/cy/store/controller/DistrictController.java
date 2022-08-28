package com.cy.store.controller;

import com.cy.store.entity.District;
import com.cy.store.service.IDistrictService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: su_gh
 * @Date: 2022 - 08 - 21 - 11:27
 * @Description: com.cy.store.controller
 * @Vsersion: 1.0
 */
@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController{
    @Autowired
    private IDistrictService districtService;

    //“/”表示方法将处理所有传入的URI请求  表示所有districts下的请求都被拦截到这个方法下
    @RequestMapping({"","/"})
    public JsonResult<List<District>> getDistrictByParent(String parent){
        List<District> list = districtService.getByParent(parent);
        return new JsonResult<>(OK,list);
    }


}
