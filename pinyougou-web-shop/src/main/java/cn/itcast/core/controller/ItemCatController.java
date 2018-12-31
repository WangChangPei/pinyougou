package cn.itcast.core.controller;

import cn.itcast.core.pojo.item.ItemCat;
import cn.itcast.core.pojo.template.TypeTemplate;
import cn.itcast.core.service.ItemCatService;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品分类管理
 */
@RestController
@RequestMapping("/itemCat")
public class ItemCatController {

    @Reference
    private ItemCatService itemCatService;

    //查询分类结果集
    @RequestMapping("/findByParentId")
    public List<ItemCat> findByParentId(Long parentId) {
        return itemCatService.findByParentId(parentId);
    }
    //查询分类结果集
    @RequestMapping("/findByParentIdWithSellerId")
    public List<ItemCat> findByParentIdWithSellerId(Long parentId) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return itemCatService.findByParentIdWithSellerId(parentId,name);
    }

    //查询一个商品分类
    @RequestMapping("/findOne")
    public ItemCat findOne(Long id) {
        return itemCatService.findOne(id);
    }

    //查询所有
    @RequestMapping("/findAll")
    public List<ItemCat> findAll() {
        return itemCatService.findAll();
    }

    //添加分类申请
    @RequestMapping("/addApply")
    public Result add(@RequestBody ItemCat itemCat) {
        try {
            String name = SecurityContextHolder.getContext().getAuthentication().getName();

            itemCatService.add(itemCat,name);

            return new Result(true, "成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "失败");
        }
    }

    //带条件(本商家)的分页查询
    @RequestMapping("/search")
    public PageResult searchByName(Integer page, Integer rows) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return itemCatService.searchByName(page, rows, name);
    }

    //添加分类申请
    @RequestMapping("/delete")
    public Result delete(Long[] ids){
        try {
            itemCatService.delete(ids);
            return new Result(true,"删除成功");
        } catch (Exception e) {
            //e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }

}

