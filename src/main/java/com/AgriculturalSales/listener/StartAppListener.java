package com.AgriculturalSales.listener;


import com.AgriculturalSales.bean.*;
import com.AgriculturalSales.dao.EditMapper;
import com.AgriculturalSales.service.NewProductService;
import com.AgriculturalSales.service.ProductService;
import com.AgriculturalSales.service.SyntheticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

@Component
public class StartAppListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    SyntheticService syntheticService;

    @Autowired
    ProductService productService;

    @Autowired
    EditMapper editMapper;

    @Autowired
    NewProductService newProductService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 将 ApplicationContext 转化为 WebApplicationContext
        WebApplicationContext webApplicationContext =
                (WebApplicationContext)event.getApplicationContext();
        // 从 webApplicationContext 中获取  servletContext
        ServletContext servletContext = webApplicationContext.getServletContext();

        // servletContext设置值
        if (event.getApplicationContext().getParent() == null) {//保证只执行一次
            List<Kind> allKind = syntheticService.getAllKind();
            servletContext.setAttribute("kind",allKind);
            List<Synthetic> allProduct = syntheticService.getAllProduct();
            servletContext.setAttribute("product", allProduct);
            List<Product> products = productService.getproductBySalesAll();
            List<Product> productsSales = new ArrayList<>();
            if (products.size()>12){
                productsSales = products.subList(0, 12);
            }
            System.out.println("productsSales = " + productsSales);
            servletContext.setAttribute("productsSales", productsSales);
            List<NewProduct> newProductList =  newProductService.getAllByCreateTime();
            List<NewProduct> productsNew = new ArrayList<>();
            if (newProductList.size()>12){
                productsNew = newProductList.subList(0,12);
            }
            servletContext.setAttribute("productsNew", productsNew);
            List<Edit> edits = editMapper.selectByExample(null);
            int i = 1;
            for (Edit edit : edits) {
                servletContext.setAttribute("img" + (i++), edit.getPath());
            }
            System.out.println("数据初始化完成！");
        }
    }
}
