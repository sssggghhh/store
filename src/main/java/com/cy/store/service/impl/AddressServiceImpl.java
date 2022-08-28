package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IDistrictService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Auther: su_gh
 * @Date: 2022 - 08 - 20 - 17:27
 * @Description: com.cy.store.service.impl
 * @Vsersion: 1.0
 */
@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private IDistrictService districtService;
    @Value("${user.address.max-count}")
    private int maxCount;
    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        //判断地址列表数量是否达到上限,是：抛出AddressCountLimitException
        Integer count = addressMapper.countByUid(uid);
        if (count > maxCount){
            throw new AddressCountLimitException("收货地址已经达到上限（"+maxCount+")!");
        }
        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);
        //补全数据：将参数uid封装到address中
        address.setUid(uid);
        //补全数据：根据上面统计的地址数量，判断idDefault的值 1为默认  0不默认
        Integer isDefault = count == 0 ? 1 : 0;
        //补全4项日志
        address.setCreatedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());

        //调用addressMapper的insert方法插入数据并获取返回影响的行数
        Integer integer = addressMapper.insertAddress(address);
        //判断受影响的行数是否不为1 是：抛出InsertException
        if (integer != 1){
            throw new InsertException("插入收货地址发生异常，请联系管理员！");
        }
    }

    @Override
    public List<Address> getAddressByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        for (Address address : list) {
            address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setCreatedUser(null);
            address.setCreatedTime(null);
            address.setModifiedUser(null);
            address.setModifiedTime(null);
        }
        return list;
    }

    @Transactional
    @Override
    public void setDefaultAddress(Integer aid, Integer uid, String username) {
        // 根据参数aid，调用addressMapper中的findByAid()查询收货地址数据
        Address byAid = addressMapper.findByAid(aid);
        // 判断查询结果是否为null
        if (byAid == null) {
            // 是：抛出AddressNotFoundException
            throw new AddressNotFoundException("查询地址不存在");
        }
        // 判断查询结果中的uid与参数uid是否不一致(使用equals()判断)
        if (!byAid.getUid().equals(uid)) {
            // 是：抛出AccessDeniedException：非法访问
            throw new AddressDeniedException("非法访问地址异常");
        }
        // 调用addressMapepr的updateNonDefaultByUid()将该用户的所有收货地址全部设置为非默认，并获取返回的受影响的行数
        Integer integer = addressMapper.updateAddressNotDefault(uid);
        // 判断受影响的行数是否小于1(不大于0)
        if (integer < 1) {
            // 是：抛出UpdateException
            throw new UpdateException("设置默认收货地址时出现错误！");
        }
        // 调用addressMapepr的updateDefaultByAid()将指定aid的收货地址设置为默认，并获取返回的受影响的行数
        Integer rows = addressMapper.updateAddressIsDefault(aid, username, new Date());
        // 判断受影响的行数是否不为1
        if (rows != 1) {
            // 是：抛出UpdateException
            throw new UpdateException("设置默认收货地址时出现错误！");
        }

    }

    @Transactional
    @Override
    public void deleteAddressByAid(Integer aid, Integer uid, String username) {
        //判断收货地址是否存在
        Address byAid = addressMapper.findByAid(aid);
        if (byAid == null){
            throw new AddressNotFoundException("收货地址不存在");
        }
        //判断当前收货地址是否是该用户的地址
        if (!byAid.getUid().equals(uid)){
            throw new AddressDeniedException("非法的访问！");
        }

        Integer integer = addressMapper.deleteAddress(aid);
        if (integer != 1){
            throw new DeleteException("删除收货地址时发生异常！");
        }

        //判断要删除的地址是否为默认地址，如果不是，就直接返回
        if (byAid.getIsDefault() == 0){
            return;
        }

        //判断当前用户的收货地址个数是否为0，如果是，就直接返回，后续不做操作
        Integer count = addressMapper.countByUid(uid);
        if (count == 0){
            return;
        }

        //查询最后一次的收货地址，获取id
        Address lastModified = addressMapper.findLastModified(uid);
        Integer aid1 = lastModified.getAid();
        //将这个收货地址设置为默认收货地址
        Integer integer1 = addressMapper.updateAddressIsDefault(aid1,username,new Date());
        if (integer1 != 1){
            throw new UpdateException("更新收货地址时出现错误！");
        }

    }
}
