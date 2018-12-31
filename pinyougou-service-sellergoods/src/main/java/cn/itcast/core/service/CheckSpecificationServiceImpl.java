package cn.itcast.core.service;

import cn.itcast.core.mapper.specification.SpecificationDao;
import cn.itcast.core.mapper.specification.SpecificationOptionDao;
import cn.itcast.core.pojo.specification.Specification;
import cn.itcast.core.pojo.specification.SpecificationQuery;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 规格管理
 */
@Service
@Transactional
public class CheckSpecificationServiceImpl implements  CheckSpecificationService {

    @Autowired
    private SpecificationDao specificationDao;
    @Autowired
    private SpecificationOptionDao specificationOptionDao;

    //查询分页
    @Override
    public PageResult search(Integer page, Integer rows, Specification specification) {
        //分页插件
        PageHelper.startPage(page,rows);
        //判断是否有值 (不写了)
        SpecificationQuery specificationQuery = new SpecificationQuery();
        SpecificationQuery.Criteria criteria = specificationQuery.createCriteria();
        if (null != specification.getSpecName() && !"".equals(specification.getSpecName().trim())) {
            System.out.println(specification.getSpecName().trim());
            criteria.andSpecNameLike("%" + specification.getSpecName().trim() + "%");
        }
        //条件
        if(null != specification.getStatus() &&  !"".equals(specification.getStatus())){
            criteria.andStatusEqualTo(specification.getStatus());
        }
        //查询
        Page<Specification> p = (Page<Specification>) specificationDao.selectByExample(specificationQuery);
        return new PageResult(p.getTotal(),p.getResult());
    }

    @Override
    public void updateStatus(Long[] ids, String status) {
        Specification specification = new Specification();
        specification.setStatus(status);

        for (Long id : ids) {
            specification.setId(id);
            //更新品牌状态
            specificationDao.updateByPrimaryKeySelective(specification);
        }
    }

    @Override
    public void delete(Long[] ids) {
        Specification specification = new Specification();
        specification.setStatus("3");

        for (Long id : ids) {
            specification.setId(id);
            //更新品牌状态
            specificationDao.updateByPrimaryKeySelective(specification);
        }
    }
}
