package cn.itcast.core.service;

import cn.itcast.core.mapper.item.ItemCatDao;
import cn.itcast.core.pojo.good.BrandQuery;
import cn.itcast.core.pojo.item.ItemCat;
import cn.itcast.core.pojo.item.ItemCatQuery;
import cn.itcast.core.pojo.item.ItemQuery;
import cn.itcast.core.pojo.specification.Specification;
import cn.itcast.core.pojo.specification.SpecificationQuery;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 商品分类管理
 */
@Service
@Transactional
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private ItemCatDao itemCatDao;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<ItemCat> findByParentId(Long parentId) {
        //1:从Mysql查询所有分类结果集
        List<ItemCat> itemCats = findAll();//1000+
        //2:保存缓存一份
        for (ItemCat itemCat : itemCats) {
            redisTemplate.boundHashOps("itemCat").put(itemCat.getName(), itemCat.getTypeId());
        }
        ItemCatQuery query = new ItemCatQuery();
        query.createCriteria().andParentIdEqualTo(parentId);
        return itemCatDao.selectByExample(query);
    }


    @Override
    public ItemCat findOne(Long id) {
        return itemCatDao.selectByPrimaryKey(id);
    }

    @Override
    public List<ItemCat> findAll() {
        return itemCatDao.selectByExample(null);
    }

    /**
     * 添加到该商家的分类列表中
     * @param itemCat
     * @param name
     */
    @Override
    public void add(ItemCat itemCat,String name) {
        itemCat.setSellerId(name);
        itemCatDao.insertSelective(itemCat);
    }

    /**
     * 查询该商家的分类申请
     *
     * @param page
     * @param rows
     * @param name
     * @return
     */
    @Override
    public PageResult searchByName(Integer page, Integer rows, String name) {
        PageHelper.startPage(page, rows);
        //1.查询本商家未申请的规格 未申请状态 0  审核通过1 驳回2 关闭 3
        ItemCatQuery itemCatQuery = new ItemCatQuery();
        ItemCatQuery.Criteria criteria = itemCatQuery.createCriteria();

        //商家名称
        criteria.andSellerIdEqualTo(name);
        //条件
        /*if (null != itemCat.getStatus() && !"".equals(itemCat.getStatus())) {
            criteria.andStatusEqualTo(itemCat.getStatus());
        }*/
      /*  if (null != itemCat.getSpecName() && !"".equals(itemCat.getSpecName().trim())) {
//            System.out.println(specification.getSpecName().trim());
            criteria.andSpecNameLike("%" + itemCat.getSpecName().trim() + "%");
        }*/
        Page<ItemCat> p = (Page<ItemCat>) itemCatDao.selectByExample(itemCatQuery);
        return new PageResult(p.getTotal(), p.getResult());
    }

    @Override
    public void delete(Long[] ids) {

        for (Long id : ids) {
            ItemCat itemCat = new ItemCat();
            itemCat.setId(id);
            itemCat.setStatus("3");
            itemCatDao.updateByPrimaryKeySelective(itemCat);
        }
    }

    @Override
    public List<ItemCat> findByParentIdWithSellerId(Long parentId,String name) {
       //1:从Mysql查询所有分类结果集
        List<ItemCat> itemCats = findByParentId(parentId);//1000+
         /*//2:保存缓存一份
        for (ItemCat itemCat : itemCats) {
            redisTemplate.boundHashOps("itemCat").put(itemCat.getName(), itemCat.getTypeId());
        }*/
        ItemCatQuery query = new ItemCatQuery();
        for (ItemCat itemCat : itemCats) {

            ItemCatQuery.Criteria criteria = query.createCriteria();
            if (null != itemCat.getSellerId() && !"".equals(itemCat.getSellerId())) {
                criteria.andSellerIdEqualTo(name);
            }
            query.createCriteria().andParentIdEqualTo(parentId);
        }
        return itemCatDao.selectByExample(query);
    }

}
