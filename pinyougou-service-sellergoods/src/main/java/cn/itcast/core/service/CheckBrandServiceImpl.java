package cn.itcast.core.service;

import cn.itcast.core.mapper.good.BrandDao;
import cn.itcast.core.pojo.good.Brand;
import cn.itcast.core.pojo.good.BrandQuery;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


/**
 * 品牌管理
 */
@Service
@Transactional
public class CheckBrandServiceImpl implements CheckBrandService {

    @Autowired
    private BrandDao brandDao;


    //查询分页对象
    public PageResult search(Integer pageNum, Integer pageSize, Brand brand) {
        //分页插件
        PageHelper.startPage(pageNum, pageSize);
        //创建品牌条件对象
        BrandQuery brandQuery = new BrandQuery();

        BrandQuery.Criteria criteria = brandQuery.createCriteria();

        //判断名称
        if (null != brand.getName() && !"".equals(brand.getName().trim())) {
            criteria.andNameLike("%" + brand.getName().trim() + "%");
        }
        //判断首字母
        if (null != brand.getFirstChar() && !"".equals(brand.getFirstChar().trim())) {
            criteria.andFirstCharEqualTo(brand.getFirstChar().trim());
        }
        //条件
        if(null != brand.getStatus() &&  !"".equals(brand.getStatus())){
            criteria.andStatusEqualTo(brand.getStatus());
        }
        //查询
        Page<Brand> p = (Page<Brand>) brandDao.selectByExample(brandQuery);

        return new PageResult(p.getTotal(), p.getResult());

    }

    @Override
    public void delete(Long[] ids) {
        Brand brand = new Brand();
        brand.setStatus("3");
        for (Long id : ids) {
            brand.setId(id);
            brandDao.updateByPrimaryKeySelective(brand);
        }

    }

    @Override
    public void updateStatus(Long[] ids, String status) {
        Brand brand = new Brand();
        brand.setStatus(status);

        for (Long id : ids) {
            brand.setId(id);
            //更新品牌状态
            brandDao.updateByPrimaryKeySelective(brand);
        }
    }
}
