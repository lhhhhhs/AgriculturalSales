package com.AgriculturalSales.service;

import com.AgriculturalSales.bean.Evaluation;
import com.AgriculturalSales.bean.Reply;
import com.AgriculturalSales.bean.ReplyExample;
import com.AgriculturalSales.dao.ReplyMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {

    @Autowired
    ReplyMapper replyMapper;

    public void save(Reply reply) {
        replyMapper.insert(reply);
    }

    public List<Reply> getAll(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Reply> replyList = replyMapper.selectByExample(null);
        return replyList;
    }

    public int getCount() {
        return (int)replyMapper.countByExample(null);
    }

    public List<Reply> getAllSearch(Integer page, Integer limit, String str) {
        ReplyExample example = new ReplyExample();
        ReplyExample.Criteria criteria = example.createCriteria();
        criteria.andEvaluationTextLike("%"+str+"%");
        PageHelper.startPage(page,limit);
        List<Reply> replyList = replyMapper.selectByExample(example);
        return replyList;
    }

    public int getCountSearch(String str) {
        ReplyExample example = new ReplyExample();
        ReplyExample.Criteria criteria = example.createCriteria();
        criteria.andEvaluationTextLike("%"+str+"%");
        return (int)replyMapper.countByExample(example);
    }

    public void delById(Integer id) {
        replyMapper.deleteByPrimaryKey(id);
    }
}
