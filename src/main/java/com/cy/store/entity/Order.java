package com.cy.store.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: su_gh
 * @Date: 2022 - 10 - 19 - 15:03
 * @Description:
 * @version: 1.0
 */
public class Order extends BaseEntity implements Serializable {
    //订单id
    private Integer oid;
    //用户id
    private Integer uid;
    //收货人姓名
    private String recvName;
    //收货人电话
    private String recvPhone;
    //收货人所在省
    private String recvProvince;
    //收货人所在城市
    private String recvCity;
    //收货人所在区
    private String recvArea;
    //收货人详细地址
    private String recvAddress;
    //订单总价
    private Long totalPrice;
    //订单状态
    private Integer status;
    //下单时间
    private Date orderTime;
    //支付时间
    private Date payTime;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getRecvName() {
        return recvName;
    }

    public void setRecvName(String recvName) {
        this.recvName = recvName;
    }

    public String getRecvPhone() {
        return recvPhone;
    }

    public void setRecvPhone(String recvPhone) {
        this.recvPhone = recvPhone;
    }

    public String getRecvProvince() {
        return recvProvince;
    }

    public void setRecvProvince(String recvProvince) {
        this.recvProvince = recvProvince;
    }

    public String getRecvCity() {
        return recvCity;
    }

    public void setRecvCity(String recvCity) {
        this.recvCity = recvCity;
    }

    public String getRecvArea() {
        return recvArea;
    }

    public void setRecvArea(String recvArea) {
        this.recvArea = recvArea;
    }

    public String getRecvAddress() {
        return recvAddress;
    }

    public void setRecvAddress(String recvAddress) {
        this.recvAddress = recvAddress;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Order order = (Order) o;

        if (oid != null ? !oid.equals(order.oid) : order.oid != null) return false;
        if (uid != null ? !uid.equals(order.uid) : order.uid != null) return false;
        if (recvName != null ? !recvName.equals(order.recvName) : order.recvName != null) return false;
        if (recvPhone != null ? !recvPhone.equals(order.recvPhone) : order.recvPhone != null) return false;
        if (recvProvince != null ? !recvProvince.equals(order.recvProvince) : order.recvProvince != null) return false;
        if (recvCity != null ? !recvCity.equals(order.recvCity) : order.recvCity != null) return false;
        if (recvArea != null ? !recvArea.equals(order.recvArea) : order.recvArea != null) return false;
        if (recvAddress != null ? !recvAddress.equals(order.recvAddress) : order.recvAddress != null) return false;
        if (totalPrice != null ? !totalPrice.equals(order.totalPrice) : order.totalPrice != null) return false;
        if (status != null ? !status.equals(order.status) : order.status != null) return false;
        if (orderTime != null ? !orderTime.equals(order.orderTime) : order.orderTime != null) return false;
        return payTime != null ? payTime.equals(order.payTime) : order.payTime == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (oid != null ? oid.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (recvName != null ? recvName.hashCode() : 0);
        result = 31 * result + (recvPhone != null ? recvPhone.hashCode() : 0);
        result = 31 * result + (recvProvince != null ? recvProvince.hashCode() : 0);
        result = 31 * result + (recvCity != null ? recvCity.hashCode() : 0);
        result = 31 * result + (recvArea != null ? recvArea.hashCode() : 0);
        result = 31 * result + (recvAddress != null ? recvAddress.hashCode() : 0);
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (orderTime != null ? orderTime.hashCode() : 0);
        result = 31 * result + (payTime != null ? payTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid=" + oid +
                ", uid=" + uid +
                ", recvName='" + recvName + '\'' +
                ", recvPhone='" + recvPhone + '\'' +
                ", recvProvince='" + recvProvince + '\'' +
                ", recvCity='" + recvCity + '\'' +
                ", recvArea='" + recvArea + '\'' +
                ", recvAddress='" + recvAddress + '\'' +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", orderTime=" + orderTime +
                ", payTime=" + payTime +
                '}';
    }
}
