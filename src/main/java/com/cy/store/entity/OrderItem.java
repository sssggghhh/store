package com.cy.store.entity;

import java.io.Serializable;

/**
 * @Auther: su_gh
 * @Date: 2022 - 10 - 19 - 15:37
 * @Description:
 * @version: 1.0
 */
public class OrderItem extends BaseEntity implements Serializable {
    //订单中的商品记录的id
    private Integer id;
    //所归属的订单的id
    private Integer oid;
    //商品的id
    private Integer pid;
    //商品标题
    private String title;
    //商品图片
    private String image;
    //商品价格
    private Long price;
    //购买数量
    private Integer num;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        OrderItem orderItem = (OrderItem) o;

        if (id != null ? !id.equals(orderItem.id) : orderItem.id != null) return false;
        if (oid != null ? !oid.equals(orderItem.oid) : orderItem.oid != null) return false;
        if (pid != null ? !pid.equals(orderItem.pid) : orderItem.pid != null) return false;
        if (title != null ? !title.equals(orderItem.title) : orderItem.title != null) return false;
        if (image != null ? !image.equals(orderItem.image) : orderItem.image != null) return false;
        if (price != null ? !price.equals(orderItem.price) : orderItem.price != null) return false;
        return num != null ? num.equals(orderItem.num) : orderItem.num == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (oid != null ? oid.hashCode() : 0);
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (num != null ? num.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", oid=" + oid +
                ", pid=" + pid +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", num=" + num +
                '}';
    }
}