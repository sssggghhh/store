package com.cy.store.mapper;

import com.cy.store.entity.Address;

import java.util.Date;
import java.util.List;

/**
 *
 */
public interface AddressMapper {

    /**
     * 新增收货地址
     * @param address
     * @return
     */
    Integer insertAddress(Address address);

    /**
     * 统计用户收货地址个数
     * @param uid
     * @return
     */
    Integer countByUid(Integer uid);

    /**
     * 根据用户id查询对应的收货地址
     * @param uid 用户id
     * @return 地址列表
     */
    List<Address> findByUid(Integer uid);

    /**
     * 根据用户uid将用户的所有收货地址设置为非默认
     * @param uid
     * @return 返回影响的行数
     */
    Integer updateAddressNotDefault(Integer uid);

    /**
     * 根据用户选择的地址id将此地址设置为默认收货地址
     * @param aid
     * @return 返回受影响的行数
     */
    Integer updateAddressIsDefault(Integer aid, String modifiedUser, Date modifiedTime);

    /**
     * 根据地址aid查询地址详细信息
     * @param aid
     * @return 返回收货地址的详细信息
     */
    Address findByAid(Integer aid);

    /**
     * 删除收货地址
     * @param aid
     * @return
     */
    Integer deleteAddress(Integer aid);

    /**
     * 查询最近一次修改的收货地址
     * @param uid
     * @return
     */
    Address findLastModified(Integer uid);

}
