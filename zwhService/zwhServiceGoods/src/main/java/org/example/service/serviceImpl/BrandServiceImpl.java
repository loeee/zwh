package org.example.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import goods.pojo.Brand;
import org.apache.commons.lang.StringUtils;
import org.example.Dao.BrandMapper;
import org.example.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;


import java.util.List;
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired(required = false)
    private BrandMapper brandMapper;

//查询所有品牌
    public List<Brand> findAll(){
        return brandMapper.selectAll();
    }
    //根据id查询
    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }
    //新增品牌
    @Override
    public void add(Brand brand) {
        brandMapper.insertSelective(brand);
    }

      //修改品牌
    public void update(Brand brand){
        brandMapper.updateByPrimaryKey(brand);
    }
    //delete brand
    @Override
    public void delete(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }
//    //根据条件搜索品牌
    @Override
    public List<Brand> findList(Brand brand){
        //构建查询条件
        Example example = createExample(brand);
        //根据构建的条件查询数据
        return brandMapper.selectByExample(example);
    }

    //构建查询对象
    public Example createExample(Brand brand){
        Example example=new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if(brand!=null){
            // 品牌名称
            if(!StringUtils.isEmpty(brand.getName())){
                criteria.andLike("name","%"+brand.getName()+"%");
            }
            // 品牌图片地址
            if(!StringUtils.isEmpty(brand.getImage())){
                criteria.andLike("image","%"+brand.getImage()+"%");
           }
            // 品牌的首字母
            if(!StringUtils.isEmpty(brand.getLetter())){
                criteria.andLike("letter","%"+brand.getLetter()+"%");
            }
            // 品牌id
            if(!StringUtils.isEmpty(brand.getLetter())){
                criteria.andEqualTo("id",brand.getId());
            }
          // 排序
            if(!StringUtils.isEmpty(brand.getSeq())){
                criteria.andEqualTo("seq",brand.getSeq());
            }
       }
        return example;
    }






    //根据品牌列表分页查询
    @Override
    public PageInfo<Brand> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Brand>(brandMapper.selectAll());
    }

//品牌列表条件查询+分页查询
@Override
public PageInfo<Brand> findPage(Brand brand, int page, int size){
    //分页
    PageHelper.startPage(page,size);
    //搜索条件构建
    Example example = createExample(brand);
    //执行搜索
    return new PageInfo<Brand>(brandMapper.selectByExample(example));
}



}
