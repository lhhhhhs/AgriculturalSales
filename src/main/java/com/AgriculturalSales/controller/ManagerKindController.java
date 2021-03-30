package com.AgriculturalSales.controller;

import com.AgriculturalSales.bean.*;
import com.AgriculturalSales.service.KindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ManagerKindController {

    @Autowired
    KindService kindService;


    /**
     * 跳转到kind1Manager页面
     *
     * @return
     */
    @RequestMapping("/kind1Manager")
    public String kind1Manager() {
        return "manager/kind1Manager";
    }

    /**
     * 跳转到kind2Manager页面
     *
     * @return
     */
    @RequestMapping("/kind2Manager")
    public String kind2Manager(Map<String, Object> map) {
        List<Kind1> k1All = kindService.getK1All();
        List<Kind2> k2All = kindService.getK2All();
        List<Kind3> k3All = kindService.getK3All();
        map.put("k1List", k1All);
        map.put("k2List", k2All);
        map.put("k3List", k3All);
        return "manager/kind2Manager";
    }

    /**
     * 跳转到kind1Manager页面
     *
     * @return
     */
    @RequestMapping("/kind3Manager")
    public String kind3Manager(Map<String, Object> map) {
        List<Kind1> k1All = kindService.getK1All();
        List<Kind2> k2All = kindService.getK2All();
        List<Kind3> k3All = kindService.getK3All();
        map.put("k1List", k1All);
        map.put("k2List", k2All);
        map.put("k3List", k3All);
        return "manager/kind3Manager";
    }


    /**
     * 数据表格接口
     *
     * @param page
     * @param limit
     * @param session
     * @return
     */
    @RequestMapping("/kind1Table")
    @ResponseBody
    public Map kind1Table(Integer page, Integer limit, HttpSession session) {
        List<Kind1> kind1s = kindService.getAllkind1(page, limit);
        int count = kindService.getCountkind1();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", kind1s);
        return map;
    }

    @RequestMapping("/kind2Table")
    @ResponseBody
    public Map kind2Table(Integer page, Integer limit, HttpSession session) {
        List<Kind2> kind2s = kindService.getAllkind2(page, limit);
        int count = kindService.getCountkind2();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", kind2s);
        return map;
    }

    @RequestMapping("/kind3Table")
    @ResponseBody
    public Map kind3Table(Integer page, Integer limit, HttpSession session) {
        List<Kind3> kind3s = kindService.getAllkind3(page, limit);
        int count = kindService.getCountkind3();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", kind3s);
        return map;
    }


    /**
     * 数据表格接口
     *
     * @param page
     * @param limit
     * @param session
     * @return
     */
    @RequestMapping("/kind1TableSearch")
    @ResponseBody
    public Map kind1TableSearch(Integer page, Integer limit, HttpSession session, String name) {
        List<Kind1> kind1s = kindService.getAllkind1Search(page, limit, name);
        int count = kindService.getCountkind1Search(name);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", kind1s);
        return map;
    }

    @RequestMapping("/kind2TableSearch")
    @ResponseBody
    public Map kind2TableSearch(Integer page, Integer limit, HttpSession session, String name) {
        List<Kind2> kind2s = kindService.getAllkind2Search(page, limit, name);
        int count = kindService.getCountkind2Search(name);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", kind2s);
        return map;
    }

    @RequestMapping("/kind3TableSearch")
    @ResponseBody
    public Map kind3TableSearch(Integer page, Integer limit, HttpSession session, String name) {
        List<Kind3> kind3s = kindService.getAllkind3Search(page, limit, name);
        int count = kindService.getCountkind3Search(name);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", kind3s);
        return map;
    }


    @RequestMapping("/delKind1")
    @ResponseBody
    public Msg delKind1(Kind1 kind1) {
        System.out.println("kind1 = " + kind1);
        if (kindService.checkDelKind1(kind1)) {
            return Msg.fail().add("msg", "有下属，无法删除！");
        }
        kindService.delKind1(kind1);
        return Msg.success();
    }

    @RequestMapping("/delKind2")
    @ResponseBody
    public Msg delKind2(Kind2 kind2) {
        System.out.println("kind2 = " + kind2);
        if (kindService.checkDelKind2(kind2)) {
            return Msg.fail().add("msg", "有下属，无法删除！");
        }
        kindService.delKind2(kind2);
        return Msg.success();
    }

    @RequestMapping("/delKind3")
    @ResponseBody
    public Msg delKind3(Kind3 kind3) {
        System.out.println("kind3 = " + kind3);
        kindService.delKind3(kind3);
        return Msg.success();
    }

    @RequestMapping("/updateKind1")
    @ResponseBody
    public Msg updateKind1(Kind1 kind1) {
        System.out.println("kind1 = " + kind1);
        kindService.updateKind1(kind1);
        return Msg.success();
    }

    @RequestMapping("/updateKind2")
    @ResponseBody
    public Msg updateKind2(Kind2 kind2) {
        System.out.println("kind2 = " + kind2);
        kindService.updateKind2(kind2);
        return Msg.success();
    }
    @RequestMapping("/updateKind3")
    @ResponseBody
    public Msg updateKind3(Kind3 kind3) {
        System.out.println("kind3 = " + kind3);
        kindService.updateKind3(kind3);
        return Msg.success();
    }


    @RequestMapping("/addKind1")
    @ResponseBody
    public Msg addKind1(Kind1 kind1) {
        System.out.println("kind1 = " + kind1);
        kindService.saveKind1(kind1);
        return Msg.success();
    }

    @RequestMapping("/addKind2")
    @ResponseBody
    public Msg addKind2(Kind2 kind2) {
        System.out.println("kind2 = " + kind2);
        kindService.saveKind2(kind2);
        return Msg.success();
    }

    @RequestMapping("/addKind3")
    @ResponseBody
    public Msg addKind3(Kind3 kind3) {
        System.out.println("kind3 = " + kind3);
        kindService.saveKind3(kind3);
        return Msg.success();
    }

}
