package com.AgriculturalSales.controller;

import com.AgriculturalSales.bean.Evaluation;
import com.AgriculturalSales.bean.Msg;
import com.AgriculturalSales.bean.Order;
import com.AgriculturalSales.bean.Reply;
import com.AgriculturalSales.service.EvaluationService;
import com.AgriculturalSales.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ManagerEvaluationController {

    @Autowired
    EvaluationService evaluationService;

    @Autowired
    ReplyService replyService;

    /**
     * 跳转到评论管理
     * @return
     */
    @RequestMapping("/evaluationManager")
    public String evaluationManager(){
        return "manager/evaluationManager";
    }


    /**
     * 跳转到评论回复管理
     * @return
     */
    @RequestMapping("/replyManager")
    public String replyManager(){
        return "manager/replyManager";
    }

    /**
     * 评论数据表格接口
     * @param page
     * @param limit
     * @param session
     * @return
     */
    @RequestMapping("/evaluationTable")
    @ResponseBody
    public Map evaluationTable(Integer page, Integer limit, HttpSession session){
        List<Evaluation> evaluationList = evaluationService.getAll(page,limit);
        int count = evaluationService.getCount();
        Map<String,Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", evaluationList);
        return map;
    }


    /**
     * 评论数据表格搜索接口
     * @param page
     * @param limit
     * @param session
     * @return
     */
    @RequestMapping("/evaluationSearch")
    @ResponseBody
    public Map evaluationSearch(Integer page, Integer limit, HttpSession session,String str){
        List<Evaluation> evaluationList = evaluationService.getAllSearch(page,limit,str);
        int count = evaluationService.getCountSearch(str);
        Map<String,Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", evaluationList);
        return map;
    }


    /**
     * 保存回复
     * @param text
     * @param eid
     * @return
     */
    @RequestMapping("/saveReply")
    @ResponseBody
    public Msg saveReply(String text,Integer eid){
        Evaluation evaluation = evaluationService.getById(eid);
        Reply reply = new Reply(null, text, LocalDate.now(), evaluation.getOid(), evaluation.getuName(), evaluation.getPid(), eid);
        replyService.save(reply);
        return Msg.success();
    }


    /**
     * 评论回复数据表格接口
     * @param page
     * @param limit
     * @param session
     * @return
     */
    @RequestMapping("/replyTable")
    @ResponseBody
    public Map replyTable(Integer page, Integer limit, HttpSession session){
        List<Reply> replyList = replyService.getAll(page,limit);
        int count = replyService.getCount();
        Map<String,Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", replyList);
        return map;
    }


    /**
     * 评论回复数据表格搜索接口
     * @param page
     * @param limit
     * @param session
     * @return
     */
    @RequestMapping("/replyTableSearch")
    @ResponseBody
    public Map replyTableSearch(Integer page, Integer limit, HttpSession session,String str){
        List<Reply> replyList = replyService.getAllSearch(page,limit,str);
        int count = replyService.getCountSearch(str);
        Map<String,Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", replyList);
        return map;
    }

    @RequestMapping("/delReply")
    @ResponseBody
    public Msg delReply(Integer id){
        replyService.delById(id);
        return Msg.success();
    }

}
