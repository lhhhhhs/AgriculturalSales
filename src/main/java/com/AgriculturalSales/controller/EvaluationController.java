package com.AgriculturalSales.controller;

import com.AgriculturalSales.bean.Evaluation;
import com.AgriculturalSales.bean.Reply;
import com.AgriculturalSales.bean.User;
import com.AgriculturalSales.service.EvaluationService;
import com.AgriculturalSales.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
public class EvaluationController {

    @Autowired
    EvaluationService evaluationService;

    @Autowired
    ReplyService replyService;

    /**
     * 评论详情
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("/evaluationDetails")
    public String evaluationDetails(Integer id, Map<String, Object> map) {
        List<Reply> replyList = evaluationService.getReply(id);
        Evaluation evaluation = evaluationService.getById(id);
        map.put("replyList", replyList);
        map.put("evaluation", evaluation);
        map.put("id", id);
        return "evaluationDetails";
    }


    /**
     * 回复评论
     * @param replyText
     * @param id
     * @param map
     * @param session
     * @return
     */
    @RequestMapping("/sendReply")
    public String sendReply(String  replyText, Integer id, Map<String, Object> map, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Evaluation evaluation = evaluationService.getById(id);
        replyService.save(new Reply(null, replyText, LocalDate.now(), evaluation.getOid(),user.getPhone(),evaluation.getPid(),id ));
        List<Reply> replyList = evaluationService.getReply(id);
        map.put("replyList", replyList);
        map.put("evaluation", evaluation);
        return "evaluationDetails";
    }
}
