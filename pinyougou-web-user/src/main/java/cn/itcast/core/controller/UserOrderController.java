package cn.itcast.core.controller;

import cn.itcast.core.pojo.UserDesc;
import cn.itcast.core.pojo.UserOrder;
import cn.itcast.core.service.UserOrderService;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户中心管理
 */
@RestController
@RequestMapping("userOrder")
public class UserOrderController {

    @Reference
    private UserOrderService userOrderService;

    // 查询用户中心订单列表
    @RequestMapping("findUserOrderList")
    public List<UserOrder> findUserOrderList(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return userOrderService.findUserOrderList(name);
    }


    // 保存个人中心新增个人信息
    @RequestMapping("save")
    public Result seve(@RequestBody UserDesc userDesc) {
        try {
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            userDesc.setUsername(name);
            userOrderService.save(userDesc);
            return new Result(true,"保存成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"保存失败!");
        }
    }
}
