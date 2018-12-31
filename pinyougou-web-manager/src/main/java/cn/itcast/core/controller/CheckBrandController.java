package cn.itcast.core.controller;

import cn.itcast.core.pojo.good.Brand;
import cn.itcast.core.service.CheckBrandService;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 品牌管理
 */
@RestController
@RequestMapping("/checkBrand")
public class CheckBrandController {

    @Reference
    private CheckBrandService checkBrandService;


    //搜索分页对象  当前页 每页数 条件
    @RequestMapping("/search")
    public PageResult search(Integer pageNum, Integer pageSize, @RequestBody Brand brand){
        //去Service实现类查询
        return checkBrandService.search(pageNum,pageSize,brand);
    }

    //删除品牌
    @RequestMapping("/delete")
    public Result delete(Long[] ids){
        try {
            checkBrandService.delete(ids);
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
            checkBrandService.updateStatus(ids,status);
            return new Result(true,"成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"失败");
        }
    }
}
