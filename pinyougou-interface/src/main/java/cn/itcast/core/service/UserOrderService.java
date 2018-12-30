package cn.itcast.core.service;

import cn.itcast.core.pojo.UserDesc;
import cn.itcast.core.pojo.UserOrder;
import entity.PageResult;

import java.util.List;

public interface UserOrderService {
    List<UserOrder> findUserOrderList(String name);

    void save(UserDesc userDesc);
}
