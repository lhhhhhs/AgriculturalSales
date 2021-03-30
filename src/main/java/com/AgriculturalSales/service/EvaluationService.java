package com.AgriculturalSales.service;

import com.AgriculturalSales.bean.*;
import com.AgriculturalSales.dao.EvaluationMapper;
import com.AgriculturalSales.dao.OrderItemMapper;
import com.AgriculturalSales.dao.ReplyMapper;
import com.github.pagehelper.PageHelper;
import jdk.nashorn.internal.ir.IfNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EvaluationService {

    @Autowired
    EvaluationMapper evaluationMapper;

    @Autowired
    OrderItemMapper orderItemMapper;

    @Autowired
    ReplyMapper replyMapper;


    /**
     * 保存评论，显示在该订单每个商品下面
     * @param evaluationObj
     * @param orderId
     */
    public void saveEvaluation(Evaluation evaluationObj, String orderId) {
        OrderItemExample example = new OrderItemExample();
        OrderItemExample.Criteria criteria = example.createCriteria();
        criteria.andOidEqualTo(orderId);
        List<OrderItem> orderItemList = orderItemMapper.selectByExample(example);
        for (OrderItem orderItem : orderItemList) {
            evaluationObj.setPid(orderItem.getPid());
            evaluationMapper.insert(evaluationObj);
        }
    }

    public List<Evaluation> getByPid(Integer id) {
        EvaluationExample example = new EvaluationExample();
        EvaluationExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(id);
        List<Evaluation> evaluationList = evaluationMapper.selectByExample(example);
        if (evaluationList.isEmpty()) {
            return null;
        }else {
            return evaluationList;
        }
    }


    public List<Reply> getReply(Integer id) {
        ReplyExample example = new ReplyExample();
        ReplyExample.Criteria criteria = example.createCriteria();
        criteria.andEidEqualTo(id);
        List<Reply> replyList = replyMapper.selectByExample(example);
        if (replyList.isEmpty()) {
            return null;
        }else {
            return replyList;
        }
    }

    public Evaluation getById(Integer id) {
        return evaluationMapper.selectByPrimaryKey(id);
    }

    public List<Evaluation> getAll(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Evaluation> evaluationList = evaluationMapper.selectByExample(null);
        return evaluationList;
    }

    public int getCount() {
        return (int)evaluationMapper.countByExample(null);
    }

    public List<Evaluation> getAllSearch(Integer page, Integer limit, String str) {
        EvaluationExample example = new EvaluationExample();
        EvaluationExample.Criteria criteria = example.createCriteria();
        criteria.andEvaluationTextLike("%"+str+"%");
        PageHelper.startPage(page,limit);
        List<Evaluation> evaluationList = evaluationMapper.selectByExample(example);
        return evaluationList;
    }

    public int getCountSearch(String str) {
        EvaluationExample example = new EvaluationExample();
        EvaluationExample.Criteria criteria = example.createCriteria();
        criteria.andEvaluationTextLike("%"+str+"%");
        return (int)evaluationMapper.countByExample(example);
    }
}
