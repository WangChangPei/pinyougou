package cn.itcast.core.service;

import cn.itcast.core.pojo.specification.Specification;
import entity.PageResult;
import pojogroup.SpecificationVo;

import java.util.List;
import java.util.Map;

public interface CheckSpecificationService {
    PageResult search(Integer page, Integer rows, Specification specification);

    void updateStatus(Long[] ids, String status);

    void delete(Long[] ids);
}
