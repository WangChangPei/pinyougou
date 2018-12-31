package cn.itcast.core.service;

import cn.itcast.core.pojo.specification.Specification;
import entity.PageResult;
import pojogroup.SpecificationVo;

import java.util.List;
import java.util.Map;

public interface SpecificationService {

    void add(SpecificationVo vo);

    SpecificationVo findOne(Long id);

    void update(SpecificationVo vo);

    List<Map> selectOptionList();

    void specificationApply(SpecificationVo vo, String name);

    void delete(Long[] ids);

    public PageResult search(Integer page, Integer rows, Specification specification);

    PageResult search(Integer page, Integer rows, Specification specification, String name);
}
