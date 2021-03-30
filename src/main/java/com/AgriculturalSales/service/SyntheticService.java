package com.AgriculturalSales.service;

import com.AgriculturalSales.bean.*;
import com.AgriculturalSales.dao.Kind1Mapper;
import com.AgriculturalSales.dao.Kind2Mapper;
import com.AgriculturalSales.dao.Kind3Mapper;
import com.AgriculturalSales.dao.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SyntheticService {

    @Autowired
    Kind1Mapper kind1Mapper;

    @Autowired
    Kind2Mapper kind2Mapper;

    @Autowired
    Kind3Mapper kind3Mapper;

    @Autowired
    ProductMapper productMapper;


    /**
     * 获得首页合成展示的集合
     * @return
     */
    public List<Synthetic> getAllProduct(){
        ArrayList<Synthetic> syntheticList = new ArrayList<>();
        List<Kind1> kind1s = kind1Mapper.selectByExample(null);
        for (Kind1 kind1 : kind1s) {
            Synthetic synthetic = new Synthetic();
            synthetic.setKind1(kind1);
            Kind2Example example = new Kind2Example();
            Kind2Example.Criteria criteria = example.createCriteria();
            criteria.andK1EqualTo(kind1.getId());
            List<Kind2> kind2s = kind2Mapper.selectByExample(example);
            synthetic.setKind2List(kind2s);
            ProductExample exampleByProduct = new ProductExample();
            exampleByProduct.setOrderByClause("sales DESC");
            ProductExample.Criteria criteria1 = exampleByProduct.createCriteria();
            criteria1.andK1EqualTo(kind1.getId());
            List<Product> products = productMapper.selectByExample(exampleByProduct);
            List<Product> products1 = null;
            if(products.size()>=8){
                products1 = products.subList(0, 8);
            }else {
                products1 = products;
            }
            synthetic.setProductList(products1);
            syntheticList.add(synthetic);
        }
        for (Synthetic synthetic : syntheticList) {
            System.out.println(synthetic);
        }
        return syntheticList;
    }


    /**
     * 获取首页商品类型展示item
     * @return
     */
    public List<Kind> getAllKind(){
        List<Kind> kindList = new ArrayList<>();
        List<Kind1> kind1s = kind1Mapper.selectByExample(null);
        for (Kind1 kind1 : kind1s) {
            Kind kind = new Kind();
            kind.setKind1(kind1);
            Kind2Example example = new Kind2Example();
            Kind2Example.Criteria criteria = example.createCriteria();
            criteria.andK1EqualTo(kind1.getId());
            List<Kind2> kind2s = kind2Mapper.selectByExample(example);
            kind.setKind2(kind2s);
            Map<Integer,List<Kind3>> map = new HashMap<>();
            Kind3Example example3 = new Kind3Example();
            for (Kind2 kind2 : kind2s) {
                Kind3Example.Criteria criteria1 = example3.createCriteria();
                criteria1.andK2EqualTo(kind2.getId());
                List<Kind3> kind3s = kind3Mapper.selectByExample(example3);
                map.put(kind2.getId(), kind3s);
                example3.clear();
            }
            kind.setMap(map);
            kindList.add(kind);
        }
        for (Kind kind : kindList) {
            System.out.println(kind);
        }
        return kindList;
    }


}
