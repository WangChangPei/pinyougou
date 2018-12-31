package cn.itcast.core.service;

import cn.itcast.core.mapper.address.AddressDao;
import cn.itcast.core.mapper.order.OrderDao;
import cn.itcast.core.mapper.order.OrderItemDao;
import cn.itcast.core.mapper.seller.SellerDao;
import cn.itcast.core.mapper.user.UserDescDao;
import cn.itcast.core.pojo.UserDesc;
import cn.itcast.core.pojo.UserOrder;
import cn.itcast.core.pojo.address.Address;
import cn.itcast.core.pojo.address.AddressQuery;
import cn.itcast.core.pojo.order.Order;
import cn.itcast.core.pojo.order.OrderItem;
import cn.itcast.core.pojo.order.OrderItemQuery;
import cn.itcast.core.pojo.order.OrderQuery;
import cn.itcast.core.pojo.seller.Seller;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.xml.soap.SAAJResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserOrderServiceImpl implements UserOrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private SellerDao sellerDao;
    @Autowired
    private UserDescDao userDescDao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public List<UserOrder> findUserOrderList(String name) {

        List<UserOrder> userOrderList = new ArrayList<>();

        // 查询该登录用户下的所有订单
        OrderQuery orderQuery = new OrderQuery();
        orderQuery.createCriteria().andUserIdEqualTo(name);
        List<Order> orderList = orderDao.selectByExample(orderQuery);
        System.out.println("orderList:" + orderList);

        for (Order order : orderList) {
            UserOrder userOrder = new UserOrder();
            Long orderId = order.getOrderId();
            // 查询每个订单内的所有订单项集合
            OrderItemQuery orderItemQuery = new OrderItemQuery();
            orderItemQuery.createCriteria().andOrderIdEqualTo(orderId);
            List<OrderItem> orderItemList = orderItemDao.selectByExample(orderItemQuery);
            System.out.println("orderItemList:" + orderItemList);

            userOrder.setCreateTime(order.getCreateTime());
            userOrder.setStatus(order.getStatus());
            userOrder.setOrderId(order.getOrderId());

            Seller seller = sellerDao.selectByPrimaryKey(order.getSellerId());
            userOrder.setSellerName(seller.getName());

            userOrder.setOrderItemList(orderItemList);
            userOrderList.add(userOrder);
        }
        System.out.println("userOrderList:" + userOrderList);
        return userOrderList;
    }

    @Override
    public void save(UserDesc userDesc) {
        userDescDao.save(userDesc);
    }

    @Override
    public List<Address> findUserAddressList(String name) {
        AddressQuery addressQuery = new AddressQuery();
        addressQuery.createCriteria().andUserIdEqualTo(name);
        return addressDao.selectByExample(addressQuery);
    }

    @Override
    public void setDefaultAddress(Long id,String name) {
        // 先把地址均设置为非默认

        AddressQuery addressQuery = new AddressQuery();
        addressQuery.createCriteria().andUserIdEqualTo(name);
        List<Address> addressList = addressDao.selectByExample(addressQuery);
        for (Address address : addressList) {
            address.setIsDefault("0");
            addressDao.updateByPrimaryKeySelective(address);
        }


        Address address = new Address();

//        address.setIsDefault("0");
//        addressDao.updateByPrimaryKeySelective(address);

        // 再把当前地址设为默认
        address.setId(id);
        address.setIsDefault("1");
        addressDao.updateByPrimaryKeySelective(address);
    }

    @Override
    public void deleteAddress(Long id) {
        addressDao.deleteByPrimaryKey(id);
    }

    @Override
    public void addUserAddress(Address address) {
        address.setIsDefault("0");
        addressDao.insertSelective(address);
    }

    @Override
    public List<UserOrder> findNotPayOrderList(String name) {
        List<UserOrder> userOrderList = new ArrayList<>();

        // 查询该登录用户下的所有订单
        OrderQuery orderQuery = new OrderQuery();
        orderQuery.createCriteria().andUserIdEqualTo(name).andStatusEqualTo("1");
        List<Order> orderList = orderDao.selectByExample(orderQuery);

        for (Order order : orderList) {
            UserOrder userOrder = new UserOrder();
            Long orderId = order.getOrderId();
            // 查询每个订单内的所有订单项集合
            OrderItemQuery orderItemQuery = new OrderItemQuery();
            orderItemQuery.createCriteria().andOrderIdEqualTo(orderId);
            List<OrderItem> orderItemList = orderItemDao.selectByExample(orderItemQuery);

            userOrder.setCreateTime(order.getCreateTime());
            userOrder.setStatus(order.getStatus());
            userOrder.setOrderId(order.getOrderId());

            Seller seller = sellerDao.selectByPrimaryKey(order.getSellerId());
            userOrder.setSellerName(seller.getName());

            userOrder.setOrderItemList(orderItemList);
            userOrderList.add(userOrder);
        }
        return userOrderList;
    }

    @Override
    public PageResult findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        return null;
    }

    @Override
    public Map<String, Object> findUserCollectList(String name) {
        Map<String, Object> map = new HashMap<>();
        Object o = redisTemplate.boundHashOps("").get(name);

        return null;
    }

    @Override
    public Address findAddressById(Long id) {
        return addressDao.selectByPrimaryKey(id);
    }

    @Override
    public void updateUserAddress(Address address) {
        addressDao.updateByPrimaryKeySelective(address);
    }

}
