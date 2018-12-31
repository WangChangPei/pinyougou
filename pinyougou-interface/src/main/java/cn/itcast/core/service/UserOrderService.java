package cn.itcast.core.service;

import cn.itcast.core.pojo.UserDesc;
import cn.itcast.core.pojo.UserOrder;
import cn.itcast.core.pojo.address.Address;
import entity.PageResult;

import java.util.List;
import java.util.Map;

public interface UserOrderService {
    List<UserOrder> findUserOrderList(String name);

    void save(UserDesc userDesc);

    List<Address> findUserAddressList(String name);

    void setDefaultAddress(Long id,String name);

    void deleteAddress(Long id);

    void addUserAddress(Address address);

    PageResult findPage(Integer pageNum, Integer pageSize);

    Map<String,Object> findUserCollectList(String name);

    Address findAddressById(Long id);

    void updateUserAddress(Address address);

    List<UserOrder> findAloneOrderList(String name, String status);
}
