package com.AgriculturalSales.service;

import com.AgriculturalSales.bean.NewProduct;
import com.AgriculturalSales.bean.NewProductExample;
import com.AgriculturalSales.bean.Product;
import com.AgriculturalSales.dao.NewProductMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewProductService {


    @Autowired
    NewProductMapper newProductMapper;

    public List<NewProduct> getAll(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<NewProduct> newProducts = newProductMapper.selectByExample(null);
        return newProducts;
    }

    public int getCount() {
        return (int)newProductMapper.countByExample(null);
    }

    public List<NewProduct> getAllSearch(Integer page, Integer limit, String name) {
        NewProductExample example = new NewProductExample();
        NewProductExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%"+name+"%");
        PageHelper.startPage(page,limit);
        List<NewProduct> newProducts = newProductMapper.selectByExample(example);
        return newProducts;
    }

    public int getCountSearch(String name) {
        NewProductExample example = new NewProductExample();
        NewProductExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%"+name+"%");
        return (int)newProductMapper.countByExample(example);
    }

    public List<NewProduct> getAllByCreateTime() {
        NewProductExample example = new NewProductExample();
        example.setOrderByClause("create_time");
        List<NewProduct> newProducts = newProductMapper.selectByExample(example);
        return newProducts;
    }
}
