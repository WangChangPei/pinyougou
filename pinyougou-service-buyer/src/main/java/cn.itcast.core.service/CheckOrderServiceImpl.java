package cn.itcast.core.service;

import cn.itcast.core.mapper.order.OrderDao;
import cn.itcast.core.pojo.order.Order;
import cn.itcast.core.pojo.order.OrderQuery;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CheckOrderServiceImpl implements CheckOrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public PageResult search(Integer page, Integer rows, Order order) {
        PageHelper.startPage(page,rows);
        OrderQuery orderQuery = new OrderQuery();
        OrderQuery.Criteria criteria = orderQuery.createCriteria();
        //判断名称
        if(null != order.getSellerId() && !"".equals(order.getSellerId().trim())){
            criteria.andSellerIdLike("%" + order.getSellerId().trim() +"%");
        }
        Page<Order> p = (Page<Order>) orderDao.selectByExample(orderQuery);
        return new PageResult(p.getTotal(),p.getResult());
    }
}
