package com.AgriculturalSales.controller;

import com.AgriculturalSales.bean.*;
import com.AgriculturalSales.dao.Kind1Mapper;
import com.AgriculturalSales.dao.NewProductMapper;
import com.AgriculturalSales.service.BaseService;
import com.AgriculturalSales.service.KindService;
import com.AgriculturalSales.service.NewProductService;
import com.AgriculturalSales.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ManagerProductController {

    @Autowired
    ProductService productService;

    @Autowired
    BaseService baseService;

    @Autowired
    KindService kindService;

    @Autowired
    NewProductMapper newProductMapper;

    @Autowired
    NewProductService newProductService;


    @RequestMapping("/newProductManager")
    public String newProductManager(Map<String,Object> map){
        List<Kind1> k1All = kindService.getK1All();
        List<Kind2> k2All = kindService.getK2All();
        List<Kind3> k3All = kindService.getK3All();
        map.put("k1List", k1All);
        map.put("k2List", k2All);
        map.put("k3List", k3All);
        return "manager/newProductManager";
    }


    /**
     * 跳转到商品添加页面
     * @return
     */
    @RequestMapping("/createProduct")
    public String createProduct(Map<String,Object> map){
        List<Kind1> k1All = kindService.getK1All();
        List<Kind2> k2All = kindService.getK2All();
        List<Kind3> k3All = kindService.getK3All();
        map.put("k1List", k1All);
        map.put("k2List", k2All);
        map.put("k3List", k3All);
        return "manager/productAddManager";
    }


    /**
     * 简介图片的上传
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/uploadImg1")
    @ResponseBody
    public Msg uploadImg1(MultipartFile file, HttpServletRequest request){
        String path = baseService.saveFile(file, request);
        return Msg.success().add("path", path);
    }


    /**
     * 详情图片的上传
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/uploadImg2")
    @ResponseBody
    public Msg uploadImg2(MultipartFile file, HttpServletRequest request){
        String path = baseService.saveFile(file, request);
        return Msg.success().add("path", path);
    }


    /**
     * 保存商品
     * @param product
     * @return
     */
    @RequestMapping("/saveProduct")
    public String saveProduct(Product product){
        product.setSales(0);
        NewProduct newProduct = new NewProduct(null, product.getId(), product.getName(), product.getPrice(), product.getSales(), product.getStock(), product.getImgPath(), product.getInfo(), product.getK1(), product.getK2(), product.getK3(), product.getDetailsImgPath(), LocalDate.now());
        newProductMapper.insert(newProduct);
        productService.saveProduct(product);
        return "redirect:/createProduct";
    }


    /**
     * 商品管理页面
     * @return
     */
    @RequestMapping("/productManager")
    public String productManager(Map<String,Object> map){
        List<Kind1> k1All = kindService.getK1All();
        List<Kind2> k2All = kindService.getK2All();
        List<Kind3> k3All = kindService.getK3All();
        map.put("k1List", k1All);
        map.put("k2List", k2All);
        map.put("k3List", k3All);
        return "manager/productManager";
    }


    /**
     * 商品数据表格接口
     * @param page
     * @param limit
     * @param session
     * @return
     */
    @RequestMapping("/productTable")
    @ResponseBody
    public Map productTable(Integer page, Integer limit, HttpSession session){
        List<Product> products = productService.getAll(page,limit);
        int count = productService.getCount();
        Map<String,Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", products);
        return map;
    }


    /**
     * 新品数据表格接口
     * @param page
     * @param limit
     * @param session
     * @return
     */
    @RequestMapping("/newProductTable")
    @ResponseBody
    public Map newProductTable(Integer page, Integer limit, HttpSession session){
        List<NewProduct> newProducts = newProductService.getAll(page,limit);
        int count = newProductService.getCount();
        Map<String,Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", newProducts);
        return map;
    }

    /**
     * 新品数据表格接口
     * @param page
     * @param limit
     * @param session
     * @return
     */
    @RequestMapping("/newProductTableSearch")
    @ResponseBody
    public Map newProductTableSearch(Integer page, Integer limit, HttpSession session,String name){
        List<NewProduct> newProducts = newProductService.getAllSearch(page,limit,name);
        int count = newProductService.getCountSearch(name);
        Map<String,Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", newProducts);
        return map;
    }


    /**
     * 删除商品
     * @param product
     * @return
     */
    @RequestMapping("/delProduct")
    @ResponseBody
    public Msg delProduct(Product product){
        productService.del(product.getId());
        return Msg.success();
    }


    /**
     * 更新商品
     * @param product
     * @return
     */
    @RequestMapping("/updateProduct")
    @ResponseBody
    public Msg updateProduct(Product product){
        productService.update(product);
        return Msg.success();
    }


    /**
     * 搜索请求数据
     * @return
     */
    @RequestMapping("/productSearch")
    @ResponseBody
    public Map productSearch(Integer page, Integer limit,String name) {
        List<Product> products = productService.getAllLikeName(page,limit,name);
        int count = productService.getCountLikeName(name);
        Map<String,Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", products);
        return map;
    }


    /**
     * kind1搜索请求数据
     * @return
     */
    @RequestMapping("/productKind1Search")
    @ResponseBody
    public Map productKind1Search(Integer page, Integer limit,Integer k1) {
        List<Product> products = productService.getAllK1(page,limit,k1);
        int count = productService.getCountK1(k1);
        Map<String,Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", products);
        return map;
    }


    /**
     * kind2搜索请求数据
     * @return
     */
    @RequestMapping("/productKind2Search")
    @ResponseBody
    public Map productKind2Search(Integer page, Integer limit,Integer k2) {
        List<Product> products = productService.getAllK2(page,limit,k2);
        int count = productService.getCountK2(k2);
        Map<String,Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", products);
        return map;
    }


    /**
     * kind3搜索请求数据
     * @return
     */
    @RequestMapping("/productKind3Search")
    @ResponseBody
    public Map productKind3Search(Integer page, Integer limit,Integer k3) {
        List<Product> products = productService.getAllK3(page,limit,k3);
        int count = productService.getCountK3(k3);
        Map<String,Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", products);
        return map;
    }


    @RequestMapping("/delNewProduct")
    @ResponseBody
    public Msg delNewProduct(Integer id){
        newProductMapper.deleteByPrimaryKey(id);
        return Msg.success();
    }


}
