package cn.itcast.core.controller;

import cn.itcast.core.pojo.template.TypeTemplate;
import cn.itcast.core.service.TypeTemplateService;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 模板管理
 */
@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController {


    @Reference
    private TypeTemplateService typeTemplateService;

    @RequestMapping("/search")
    public PageResult search(Integer page, Integer rows, @RequestBody TypeTemplate tt){
        //获取商家名称
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return typeTemplateService.search(page,rows,tt);
    }
    @RequestMapping("/searchByName")
    public PageResult searchByName(Integer page, Integer rows, @RequestBody TypeTemplate tt){
        //获取商家名称
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return typeTemplateService.search(page,rows,tt,name);
    }

    //添加
    @RequestMapping("/add")
    public Result add(@RequestBody TypeTemplate tt){
        try {
            //获取商家名称
            typeTemplateService.add(tt);
            return new Result(true,"成功");
        } catch (Exception e) {
            //e.printStackTrace();
            return new Result(false,"失败");
        }
    }

    //添加模板申请
    @RequestMapping("/addApply")
    public Result addApply(@RequestBody TypeTemplate tt){
        try {
            //获取商家名称
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            typeTemplateService.add(tt,name);
            return new Result(true,"成功");
        } catch (Exception e) {
            //e.printStackTrace();
            return new Result(false,"失败");
        }
    }
    //修改
    @RequestMapping("/update")
    public Result update(@RequestBody TypeTemplate tt){
        try {
            typeTemplateService.update(tt);
            return new Result(true,"成功");
        } catch (Exception e) {
            //e.printStackTrace();
            return new Result(false,"失败");
        }
    }
    //查询一个模板
    @RequestMapping("/findOne")
    public TypeTemplate findOne(Long id){
        return typeTemplateService.findOne(id);
    }
    //查询规格及规格选项结果集  List<Map>
    @RequestMapping("/findBySpecList")
    public List<Map> findBySpecList(Long id){
        return typeTemplateService.findBySpecList(id);
    }

    /**
     * 修改模板的状态为3
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Long[] ids){
        try {
            typeTemplateService.delete(ids);
            return new Result(true,"成功");
        } catch (Exception e) {
            //e.printStackTrace();
            return new Result(false,"失败");
        }
    }

  /*
    @RequestMapping("/delete")
    public Result updateStatus(Long id){
        try {
            typeTemplateService.updateStatus(id);
            return new Result(true,"成功");
        } catch (Exception e) {
            //e.printStackTrace();
            return new Result(false,"失败");
        }
    }*/
}
