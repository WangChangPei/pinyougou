package cn.itcast.core.service;

import cn.itcast.core.pojo.good.Brand;
import entity.PageResult;

import java.util.List;
import java.util.Map;

public interface CheckBrandService {


    void delete(Long[] ids);

    PageResult search(Integer pageNum, Integer pageSize, Brand brand);


    void updateStatus(Long[] ids, String status);
}
