package com.AgriculturalSales.controller;

import com.AgriculturalSales.bean.Kind1;
import com.AgriculturalSales.bean.KindSales;
import com.AgriculturalSales.bean.KindSalesExample;
import com.AgriculturalSales.dao.Kind1Mapper;
import com.AgriculturalSales.dao.KindSalesMapper;
import com.AgriculturalSales.service.SalesRallyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.*;

@Controller
public class AdminController {


    @Autowired
    SalesRallyService salesRallyService;

    @Autowired
    KindSalesMapper kindSalesMapper;

    @Autowired
    Kind1Mapper kind1Mapper;

    @RequestMapping("/exit")
    public String exit(HttpSession session){
        session.invalidate();
        return "redirect:/goLogin";
    }


    @RequestMapping("/goAdmin")
    public String goAdmin(HttpSession session,Map<String,Object> map){
        //饼状图数据
        Map<String,Integer> salesRallyMap = salesRallyService.getMap();
        map.put("salesRallyMap",salesRallyMap);
        //折线图数据
        //kind集合
        List<Kind1> kind1s = kind1Mapper.selectByExample(null);
        //时间集合
        List<LocalDate> timeList = new ArrayList<>();
        List<LocalDate> timeList1 = new ArrayList<>();
        //数据map
        Map<String,List<Integer>> map2 = new HashMap<>();
        for (Kind1 kind1 : kind1s) {
            KindSalesExample example = new KindSalesExample();
            example.setOrderByClause("creat_time");
            KindSalesExample.Criteria criteria = example.createCriteria();
            criteria.andK1EqualTo(kind1.getId());
            List<KindSales> kindSales = kindSalesMapper.selectByExample(example);
            List<KindSales> kindSales1 = null;
            if(kindSales.size()>=7) {
                 kindSales1 = kindSales.subList(kindSales.size() - 7, kindSales.size());
            }else {
                kindSales1 = kindSales;
            }
            List<Integer> kindSalesList = new ArrayList<>();
            if(!timeList.isEmpty()){
                for (LocalDate localDate : timeList) {
                    timeList1.add(localDate);
                }
            }
            timeList.clear();
            for (KindSales kindSale : kindSales1) {
                timeList.add(kindSale.getCreatTime());
                kindSalesList.add(kindSale.getSales());
            }
            map2.put(kind1.getName(), kindSalesList);
        }
        List<LocalDate> localDates = timeList1.subList(timeList1.size() - 7, timeList1.size());
        map.put("kind1s",kind1s);
        map.put("timeList",localDates);
        map.put("kindSalesMap",map2);
        System.out.println("timeList = " + timeList);
        System.out.println("kind1s = " + kind1s);
        System.out.println("timeList1 = " + timeList1.subList(timeList1.size()-7, timeList1.size()));
        System.out.println("map2 = " + map2);
        return "manager/admin";
    }


    @RequestMapping("/exitManager")
    public String exitManager(HttpSession session){
        session.invalidate();
        return "redirect:/goLogin";
    }
}
