package com.AgriculturalSales.controller;

import com.AgriculturalSales.bean.*;
import com.AgriculturalSales.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ManagerAddController {

    @Autowired
    AddressService addressService;


    @RequestMapping("/provinceManager")
    public String provinceManager(){
        return "manager/provinceManager";
    }

    @RequestMapping("/cityManager")
    public String cityManager(){
        return "manager/cityManager";
    }
    @RequestMapping("/areaManager")
    public String areaManager(){
        return "manager/areaManager";
    }


    /**
     * 数据表格接口
     * @param page
     * @param limit
     * @param session
     * @return
     */
    @RequestMapping("/provinceTable")
    @ResponseBody
    public Map provinceTable(Integer page, Integer limit, HttpSession session){
        List<Provinces> provinces = addressService.getAllProvince(page,limit);
        int count = addressService.getCountProvince();
        Map<String,Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", provinces);
        return map;
    }


    @RequestMapping("/areaTable")
    @ResponseBody
    public Map areaTable(Integer page, Integer limit, HttpSession session){
        List<Areas> areas = addressService.getAllArea(page,limit);
        int count = addressService.getCountAreae();
        Map<String,Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", areas);
        return map;
    }

    @RequestMapping("/areaTableSearch")
    @ResponseBody
    public Map areaTableSearch(Integer page, Integer limit, HttpSession session,String name){
        List<Areas> areas = addressService.getAllAreaSearch(page,limit,name);
        int count = addressService.getCountAreaeSearch(name);
        Map<String,Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", areas);
        return map;
    }

    /**
     * 数据表格接口
     * @param page
     * @param limit
     * @param session
     * @return
     */
    @RequestMapping("/cityTable")
    @ResponseBody
    public Map cityTable(Integer page, Integer limit, HttpSession session){
        List<Cities> cities = addressService.getAllCity(page,limit);
        int count = addressService.getCountCity();
        Map<String,Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", cities);
        return map;
    }


    /**
     * 数据表格搜索接口
     * @param page
     * @param limit
     * @param session
     * @return
     */
    @RequestMapping("/provinceTableSearch")
    @ResponseBody
    public Map provinceTableSearch(Integer page, Integer limit, HttpSession session,String name){
        List<Provinces> provinces = addressService.getAllProvinceSearch(page,limit,name);
        int count = addressService.getCountProvinceSearch(name);
        Map<String,Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", provinces);
        return map;
    }


    /**
     * 数据表格接口
     * @param page
     * @param limit
     * @param session
     * @return
     */
    @RequestMapping("/cityTableSearch")
    @ResponseBody
    public Map cityTableSearch(Integer page, Integer limit, HttpSession session,String name){
        List<Cities> cities = addressService.getAllCitySearch(page,limit,name);
        int count = addressService.getCountCitySearch(name);
        Map<String,Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", cities);
        return map;
    }




    @RequestMapping("/updateProvince")
    @ResponseBody
    public Msg updateProvince(Provinces provinces){
        addressService.updateProvince(provinces);
        return Msg.success();
    }
    @RequestMapping("/updateCity")
    @ResponseBody
    public Msg updateCity(Cities cities){
        addressService.updateCity(cities);
        return Msg.success();
    }
    @RequestMapping("/updateArea")
    @ResponseBody
    public Msg updateArea(Areas areas){
        addressService.updateAreas(areas);
        return Msg.success();
    }

    @RequestMapping("/addProvince")
    @ResponseBody
    public Msg addProvince(Provinces provinces){
        addressService.insertProvince(provinces);
        return Msg.success();
    }


    @RequestMapping("/addCity")
    @ResponseBody
    public Msg addCity(Cities cities){
        addressService.addCity(cities);
        return Msg.success();
    }


    @RequestMapping("/addArea")
    @ResponseBody
    public Msg addArea(Areas areas){
        addressService.addAreas(areas);
        return Msg.success();
    }
}
