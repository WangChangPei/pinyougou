package cn.itcast.core.service;

import cn.itcast.core.pojo.item.ItemCat;
import entity.PageResult;

import java.util.List;

public interface ItemCatService {
    List<ItemCat> findByParentId(Long parentId);

    ItemCat findOne(Long id);

    List<ItemCat> findAll();

    void add(ItemCat itemCatm,String name);

    PageResult searchByName(Integer page, Integer rows, String name);

    void delete(Long[] ids);

    List<ItemCat> findByParentIdWithSellerId(Long parentId,String name);

}
