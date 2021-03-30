package com.AgriculturalSales.service;

import com.AgriculturalSales.bean.Product;
import com.AgriculturalSales.bean.ProductExample;
import com.AgriculturalSales.dao.ProductMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductMapper productMapper;


    /**
     * 根据条件查询商品
     *
     * @param keyWord
     * @param k1
     * @param k2
     * @param k3
     * @return
     */
    public List<Product> getProductSelect(String keyWord, Integer k1, Integer k2, Integer k3, Integer money, Integer sales) {
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
                if(0 != money){
                    example.setOrderByClause("price");
                }
                if(0 != sales){
                    example.setOrderByClause("sales desc");
                }
                if ("0".equals(keyWord)) {
                    //是否为种类1
                    if (0 == k1) {
                        //是否为种类2
                        if (0 == k2) {

                            if (0 == k3) {
                                return productMapper.selectByExample(example);
                            } else {

                                criteria.andK3EqualTo(k3);
                                List<Product> products = productMapper.selectByExample(example);
                                if (products.isEmpty()) {
                                    return null;
                                } else {
                                    return products;
                                }

                            }

                        } else {

                            criteria.andK2EqualTo(k2);
                            List<Product> products = productMapper.selectByExample(example);
                            if (products.isEmpty()) {
                                return null;
                            } else {
                                return products;
                            }

                        }

                    } else {

                        criteria.andK1EqualTo(k1);
                        List<Product> products = productMapper.selectByExample(example);
                        if (products.isEmpty()) {
                            return null;
                        } else {
                            return products;
                        }

                    }

                } else {
                    criteria.andNameLike("%" + keyWord + "%");
                    List<Product> products = productMapper.selectByExample(example);
                    if (products.isEmpty()) {
                        return null;
                    } else {
                        return products;
                    }
                }

    }

    /**
     * 根据id获得商品
     * @param id
     * @return
     */
    public Product getById(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取销量前6的商品
     * @return
     */
    public List<Product> getproductBySales() {
        ProductExample example = new ProductExample();
        example.setOrderByClause("sales desc");
        List<Product> products = productMapper.selectByExample(example);
        List<Product> products1 = products.subList(0, 5);
        return products1;
    }

    /**
     * 保存商品
     * @param product
     */
    public void saveProduct(Product product) {
        productMapper.insert(product);
    }


    /**
     * 获得所有商品
     * @param page
     * @param limit
     * @return
     */
    public List<Product> getAll(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Product> products = productMapper.selectByExample(null);
        return products;
    }

    /**
     * 获得商品数量
     * @return
     */
    public int getCount() {
        return (int)productMapper.countByExample(null);
    }

    /**
     * 删除商品
     * @param id
     */
    public void del(Integer id) {
        productMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新商品
     * @param product
     */
    public void update(Product product) {
        productMapper.updateByPrimaryKeySelective(product);
    }

    /**
     * 搜索商品
     * @param page
     * @param limit
     * @param name
     * @return
     */
    public List<Product> getAllLikeName(Integer page, Integer limit, String name) {
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%"+name+"%");
        PageHelper.startPage(page,limit);
        List<Product> products = productMapper.selectByExample(example);
        return products;
    }


    /**
     * 获取搜索到的数量
     * @param name
     * @return
     */
    public int getCountLikeName(String name) {
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%"+name+"%");
        long l = productMapper.countByExample(example);
        return (int)l;
    }

    /**
     * kind1搜索
     * @param page
     * @param limit
     * @param k1
     * @return
     */
    public List<Product> getAllK1(Integer page, Integer limit, Integer k1) {
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andK1EqualTo(k1);
        PageHelper.startPage(page,limit);
        List<Product> products = productMapper.selectByExample(example);
        return products;
    }

    /**
     * kind1数量
     * @param k1
     * @return
     */
    public int getCountK1(Integer k1) {
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andK1EqualTo(k1);
        long l = productMapper.countByExample(example);
        return (int)l;
    }

    /**
     * k2搜索
     * @param page
     * @param limit
     * @param k2
     * @return
     */
    public List<Product> getAllK2(Integer page, Integer limit, Integer k2) {
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andK2EqualTo(k2);
        PageHelper.startPage(page,limit);
        List<Product> products = productMapper.selectByExample(example);
        return products;
    }

    /**
     * k2数量
     * @param k2
     * @return
     */
    public int getCountK2(Integer k2) {
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andK2EqualTo(k2);
        long l = productMapper.countByExample(example);
        return (int)l;
    }

    /**
     * k3搜索
     * @param page
     * @param limit
     * @param k3
     * @return
     */
    public List<Product> getAllK3(Integer page, Integer limit, Integer k3) {
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andK3EqualTo(k3);
        PageHelper.startPage(page,limit);
        List<Product> products = productMapper.selectByExample(example);
        return products;
    }

    /**
     * k3数量
     * @param k3
     * @return
     */
    public int getCountK3(Integer k3) {
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andK3EqualTo(k3);
        long l = productMapper.countByExample(example);
        return (int)l;
    }

    public List<Product> getproductBySalesAll() {
        ProductExample example = new ProductExample();
        example.setOrderByClause("sales desc");
        List<Product> products = productMapper.selectByExample(example);
        return products;
    }
}
