package cn.itcast.core.controller;

import cn.itcast.core.pojo.good.Goods;
import cn.itcast.core.pojo.item.ItemCat;
import cn.itcast.core.service.GoodsService;
import cn.itcast.core.service.ItemCatService;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojogroup.GoodsVo;

import java.util.List;

/**
 * 商品管理
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {



    @Reference
    private GoodsService goodsService;
    @Reference
    private ItemCatService itemCatService;
    //商品添加
    @RequestMapping("/add")
    public Result add(@RequestBody GoodsVo vo){
        try {
            //商家ID 主键
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            vo.getGoods().setSellerId(name);
            goodsService.add(vo);
            return new Result(true,"成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"失败");
        }
    }
    //商品添加
    @RequestMapping("/update")
    public Result update(@RequestBody GoodsVo vo){
        try {


            goodsService.update(vo);
            return new Result(true,"成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"失败");
        }
    }

    //商品分页查询 条件
    @RequestMapping("/search")
    public PageResult search(Integer page, Integer rows, @RequestBody Goods goods){
        //商家ID 主键
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        goods.setSellerId(name);
        return goodsService.search(page,rows,goods);
    }
    //查询一个包装对象
    @RequestMapping("/findOne")
    public GoodsVo findOne(Long id){
        return goodsService.findOne(id);
    }

    /**
     * 查询所有的分类
     * @return
     */
    @RequestMapping("/findAll")
    public List<ItemCat> findAll(){
        try {
            return itemCatService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //开始审核 (1:通过 2:驳回)
    @RequestMapping("/updateStatus")
    public Result updateStatus(Long[] ids , String status){
        try {
            goodsService.updateStatus(ids,status);
            return new Result(true,"审核成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"审核失败");
        }
    }
}
