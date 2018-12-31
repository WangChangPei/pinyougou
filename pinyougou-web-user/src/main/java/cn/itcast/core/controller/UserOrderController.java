package cn.itcast.core.controller;

import cn.itcast.core.pojo.UserDesc;
import cn.itcast.core.pojo.UserOrder;
import cn.itcast.core.pojo.address.Address;
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
import java.util.Map;

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
        // 查询当前登录人姓名
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return userOrderService.findUserOrderList(name);
    }

    // 分页
    @RequestMapping("findPage")
    public PageResult findPage(Integer pageNum, Integer pageSize) {
        return userOrderService.findPage(pageNum,pageSize);
    }

    // 查询用户中心订单列表
    @RequestMapping("findNotPayOrderList")
    public List<UserOrder> findNotPayOrderList(){
        // 查询当前登录人姓名
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return userOrderService.findNotPayOrderList(name);
    }

    // 保存个人中心新增个人信息
    @RequestMapping("save")
    public Result seve(@RequestBody UserDesc userDesc) {
        try {
            // 查询当前登录人姓名
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            userDesc.setUsername(name);
            userOrderService.save(userDesc);
            return new Result(true,"保存成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"保存失败!");
        }
    }

    // 查询用户地址列表集合
    @RequestMapping("findUserAddressList")
    public List<Address> findUserAddressList() {
        // 查询当前登录人姓名
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return userOrderService.findUserAddressList(name);
    }

    // 修改默认地址
    @RequestMapping("setDefaultAddress")
    public Result setDefaultAddress(Long id) {
        try {
            // 查询当前登录人姓名
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            userOrderService.setDefaultAddress(id,name);
            return new Result(true,"修改成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"修改失败!");
        }
    }

    // 删除地址
    @RequestMapping("deleteAddress")
    public Result deleteAddress(Long id) {
        try {
            userOrderService.deleteAddress(id);
            return new Result(true,"删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败!");
        }
    }

    // 编辑地址之回显
    @RequestMapping("findAddressById")
    public Address findAddressById(Long id) {
        return userOrderService.findAddressById(id);
    }

    // 编辑收货地址
    @RequestMapping("updateUserAddress")
    public Result updateUserAddress(@RequestBody Address address) {
        try {
            userOrderService.updateUserAddress(address);
            return new Result(true,"编辑成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"编辑失败!");
        }
    }

    // 新增收货地址
    @RequestMapping("addUserAddress")
    public Result addUserAddress(@RequestBody Address address) {
        try {
            // 查询当前登录人姓名
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            address.setUserId(name);

            userOrderService.addUserAddress(address);
            return new Result(true,"新增成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"新增失败!");
        }
    }

    // 查询收藏列表
    @RequestMapping("findUserCollectList")
    public Map<String,Object> findUserCollectList() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return userOrderService.findUserCollectList(name);
    }
}
