package cn.itcast.core.controller;

import cn.itcast.core.pojo.template.TypeTemplate;
import cn.itcast.core.service.CheckTypeTemplateService;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模板管理
 */
@RestController
@RequestMapping("/checkTypeTemplate")
public class CheckTypeTemplateController {


    @Reference
    private CheckTypeTemplateService checkTypeTemplateService;

    @RequestMapping("/search")
    public PageResult search(Integer page, Integer rows, @RequestBody TypeTemplate tt){
        return checkTypeTemplateService.search(page,rows,tt);
    }

    @RequestMapping("/updateStatus")
    public Result updateStatus(Long[] ids ,String status){
        try {
            checkTypeTemplateService.updateStatus(ids,status);
            return new Result(true,"成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"失败");
        }
    }

    //删除品牌
    @RequestMapping("/delete")
    public Result delete(Long[] ids){
        try {
            checkTypeTemplateService.delete(ids);
            return new Result(true,"删除成功");
        } catch (Exception e) {
            //e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }
}
