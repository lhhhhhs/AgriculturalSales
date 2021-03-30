package com.AgriculturalSales.bean;

import com.AgriculturalSales.dao.Kind1Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 该类是综合每一大类的下一级类的销量前12个商品的类
 */
public class Synthetic {

    private Kind1 kind1;

    private List<Kind2> kind2List;

    private List<Product> productList;

    public Synthetic() {
    }

    public Synthetic(Kind1 kind1, List<Kind2> kind2List, List<Product> productList) {
        this.kind1 = kind1;
        this.kind2List = kind2List;
        this.productList = productList;
    }

    public Kind1 getKind1() {
        return kind1;
    }

    public void setKind1(Kind1 kind1) {
        this.kind1 = kind1;
    }

    public List<Kind2> getKind2List() {
        return kind2List;
    }

    public void setKind2List(List<Kind2> kind2List) {
        this.kind2List = kind2List;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "Synthetic{" +
                "kind1=" + kind1 +
                ", kind2List=" + kind2List +
                ", productList=" + productList +
                '}';
    }
}
