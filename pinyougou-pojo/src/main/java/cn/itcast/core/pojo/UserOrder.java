package cn.itcast.core.pojo;

import cn.itcast.core.pojo.order.OrderItem;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserOrder implements Serializable {
    //商家的ID
    private String sellerId;
    //商家的名称
    private String sellerName;
    // 订单创建时间
    private Date createTime;
    // 订单id
    private Long orderId;
    // 订单状态
    private String status;

    //商品结果集
    private List<OrderItem> orderItemList;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }



    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
