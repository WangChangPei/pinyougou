package cn.itcast.core.service;

import cn.itcast.core.mapper.item.ItemCatDao;
import cn.itcast.core.pojo.item.ItemCat;
import cn.itcast.core.pojo.item.ItemCatQuery;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品分类管理
 */
@Service
@Transactional
public class CheckItemCatServiceImpl implements  CheckItemCatService {
    @Autowired
    private ItemCatDao itemCatDao;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ItemCat findOne(Long id) {
        return itemCatDao.selectByPrimaryKey(id);
    }

    @Override
    public List<ItemCat> findAll() {
        return itemCatDao.selectByExample(null);
    }

    @Override
    public void delete(Long[] ids) {
        ItemCat itemCat = new ItemCat();
        itemCat.setStatus("3");

        for (Long id : ids) {
            itemCat.setId(id);
            //更新品牌状态
            itemCatDao.updateByPrimaryKeySelective(itemCat);
        }

    }

    @Override
    public void updateStatus(Long[] ids, String status) {
        ItemCat itemCat = new ItemCat();
        itemCat.setStatus(status);

        for (Long id : ids) {
            itemCat.setId(id);
            //更新品牌状态
            itemCatDao.updateByPrimaryKeySelective(itemCat);
        }
    }

    @Override
    public PageResult search(Integer pageNum, Integer pageSize, ItemCat itemCat) {
        //分页插件
        PageHelper.startPage(pageNum, pageSize);
        //创建条件对象
        ItemCatQuery itemCatQuery = new ItemCatQuery();
        ItemCatQuery.Criteria criteria = itemCatQuery.createCriteria();

        //判断名称
        if (null != itemCat.getName() && !"".equals(itemCat.getName().trim())) {
            criteria.andNameLike("%" + itemCat.getName().trim() + "%");
        }

        //条件
        if(null != itemCat.getStatus() &&  !"".equals(itemCat.getStatus())){
            criteria.andStatusEqualTo(itemCat.getStatus());
        }
        //查询
        Page<ItemCat> p = (Page<ItemCat>) itemCatDao.selectByExample(itemCatQuery);

        return new PageResult(p.getTotal(), p.getResult());
    }

}
