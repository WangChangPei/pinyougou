package cn.itcast.core.service;

import cn.itcast.core.mapper.order.OrderDao;
import cn.itcast.core.mapper.order.OrderItemDao;
import cn.itcast.core.mapper.seller.SellerDao;
import cn.itcast.core.mapper.user.UserDescDao;
import cn.itcast.core.pojo.UserDesc;
import cn.itcast.core.pojo.UserOrder;
import cn.itcast.core.pojo.order.Order;
import cn.itcast.core.pojo.order.OrderItem;
import cn.itcast.core.pojo.order.OrderItemQuery;
import cn.itcast.core.pojo.order.OrderQuery;
import cn.itcast.core.pojo.seller.Seller;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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
}
