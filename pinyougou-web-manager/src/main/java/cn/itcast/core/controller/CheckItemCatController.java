package cn.itcast.core.controller;

import cn.itcast.core.pojo.item.ItemCat;
import cn.itcast.core.service.CheckItemCatService;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品分类管理
 */
@RestController
@RequestMapping("/checkItemCat")
public class CheckItemCatController {

    @Reference
    private CheckItemCatService checkItemCatService;

    @RequestMapping("/search")
    public PageResult search(Integer page, Integer rows, @RequestBody ItemCat itemCat){
        //去Service实现类查询
        return checkItemCatService.search(page,rows,itemCat);
    }

    //查询所有
    @RequestMapping("/findAll")
    public List<ItemCat> findAll(){
        return checkItemCatService.findAll();
    }


    //删除品牌
    @RequestMapping("/delete")
    public Result delete(Long[] ids){
        try {
            checkItemCatService.delete(ids);
            return new Result(true,"删除成功");
        } catch (Exception e) {
            //e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }



    //更新品牌的状态
    //开始审核 (1:通过 2:驳回)
    @RequestMapping("/updateStatus")
    public Result updateStatus(Long[] ids ,String status){
        try {
            checkItemCatService.updateStatus(ids,status);
            return new Result(true,"成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"失败");
        }
    }

}

