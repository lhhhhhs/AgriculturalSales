package com.AgriculturalSales.controller;

import com.AgriculturalSales.bean.Evaluation;
import com.AgriculturalSales.bean.Product;
import com.AgriculturalSales.service.EvaluationService;
import com.AgriculturalSales.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    EvaluationService evaluationService;

    /**
     * 跳转到商品界面
     * @return
     */
    @RequestMapping("/product")
    public String product(@RequestParam(value="KeyWord",defaultValue="0")String KeyWord
            , @RequestParam(value="k1",defaultValue="0")Integer k1
            , @RequestParam(value="k2",defaultValue="0")Integer k2
            , @RequestParam(value="k3",defaultValue="0")Integer k3
            , @RequestParam(value="pn",defaultValue="1")Integer pn
            , @RequestParam(value="money",defaultValue="0")Integer money
            , @RequestParam(value="sales",defaultValue="0")Integer sales
            , Map<String,Object> map){
        System.out.println("KeyWord = " + KeyWord);
        System.out.println("k1 = " + k1);
        System.out.println("k2 = " + k2);
        System.out.println("k3 = " + k3);
        System.out.println("pn = " + pn);
        System.out.println("money = " + money);
        System.out.println("sales = " + sales);
        //开始创建分页
        PageHelper.startPage(pn, 12);
        //查出所有的商品
        List<Product> productsList = productService.getProductSelect(KeyWord,k1,k2,k3,money,sales);
        //把结果集封装进pageInfo
        PageInfo<Product> pageInfo = new PageInfo<Product>(productsList,5);
        map.put("pageInfo", pageInfo);
        map.put("KeyWord", KeyWord);
        map.put("k1", k1);
        map.put("k2", k2);
        map.put("k3", k3);
        map.put("money", money);
        map.put("sales", sales);
        System.out.println("pageInfo = " + pageInfo);
        return "products";
    }


    /**
     * 查看商品详情
     * @param id
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/productDetails")
    public String productDetails(Integer id, Map<String,Object> map, HttpServletRequest request){
        Product product = productService.getById(id);
        List<Evaluation> evaluationList = evaluationService.getByPid(id);
        map.put("product", product);
        if (evaluationList != null){
            map.put("evaluationList",evaluationList);
            map.put("count", evaluationList.size());
        }
        return "productDetails";
    }


    /**
     * 查看全部评论
     * @param id
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/allEvaluation")
    public String allEvaluation(Integer id, Map<String,Object> map, HttpServletRequest request){
        Product product = productService.getById(id);
        List<Evaluation> evaluationList = evaluationService.getByPid(id);
        map.put("product", product);
        if (evaluationList != null){
            map.put("evaluationList",evaluationList);
            map.put("count", evaluationList.size());
        }
        return "allEvaluation";
    }




}
