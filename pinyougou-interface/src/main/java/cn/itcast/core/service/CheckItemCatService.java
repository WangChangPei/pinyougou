package cn.itcast.core.service;

import cn.itcast.core.pojo.item.ItemCat;
import entity.PageResult;

import java.util.List;

public interface CheckItemCatService {

    ItemCat findOne(Long id);

    List<ItemCat> findAll();

    void delete(Long[] ids);

    void updateStatus(Long[] ids, String status);

    PageResult search(Integer pageNum, Integer pageSize, ItemCat itemCat);
}
