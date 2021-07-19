package org.example.service;

import com.github.pagehelper.PageInfo;
import goods.pojo.Brand;

import java.util.List;

public interface BrandService {
    //查询所有品牌
    List<Brand> findAll();
    //根据id查询
    Brand findById(Integer id);
    //新增品牌
    void add(Brand brand);
    //修改品牌
    void update(Brand brand);
    //delete brand
    void delete(Integer id);
    //根据条件搜索品牌
    List<Brand> findList(Brand brand);
    //品牌列表分页查询
    PageInfo<Brand> findPage(int page, int size);
    //品牌列表条件查询+分页查询
    PageInfo<Brand> findPage(Brand brand, int page, int size);
}
