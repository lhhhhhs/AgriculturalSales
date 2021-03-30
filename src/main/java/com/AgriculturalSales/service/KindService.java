package com.AgriculturalSales.service;

import com.AgriculturalSales.bean.*;
import com.AgriculturalSales.dao.Kind1Mapper;
import com.AgriculturalSales.dao.Kind2Mapper;
import com.AgriculturalSales.dao.Kind3Mapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KindService {

    @Autowired
    Kind1Mapper kind1Mapper;

    @Autowired
    Kind2Mapper kind2Mapper;

    @Autowired
    Kind3Mapper kind3Mapper;


    public List<Kind1> getK1All() {
        return kind1Mapper.selectByExample(null);
    }

    public List<Kind2> getK2All() {
        return kind2Mapper.selectByExample(null);
    }

    public List<Kind3> getK3All() {
        return kind3Mapper.selectByExample(null);
    }

    public List<Kind1> getAllkind1(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Kind1> kind1s = kind1Mapper.selectByExample(null);
        return kind1s;
    }

    public int getCountkind1() {
        return (int)kind1Mapper.countByExample(null);
    }

    public List<Kind1> getAllkind1Search(Integer page, Integer limit, String name) {
        Kind1Example example = new Kind1Example();
        Kind1Example.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%"+name+"%");
        PageHelper.startPage(page,limit);
        List<Kind1> kind1s = kind1Mapper.selectByExample(example);
        return kind1s;
    }

    public int getCountkind1Search(String name) {
        Kind1Example example = new Kind1Example();
        Kind1Example.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%"+name+"%");
        return (int)kind1Mapper.countByExample(example);
    }

    /**
     * 检查该种类有无下属种类 有返回true
     * @param kind1
     * @return
     */
    public boolean checkDelKind1(Kind1 kind1) {
        Kind2Example example = new Kind2Example();
        Kind2Example.Criteria criteria = example.createCriteria();
        criteria.andK1EqualTo(kind1.getId());
        List<Kind2> kind2s = kind2Mapper.selectByExample(example);
        if (kind2s.isEmpty()) {
            return false;
        }
        return true;
    }

    public void delKind1(Kind1 kind1) {
        kind1Mapper.deleteByPrimaryKey(kind1.getId());
    }

    public void updateKind1(Kind1 kind1) {
        kind1Mapper.updateByPrimaryKeySelective(kind1);
    }

    public void saveKind1(Kind1 kind1) {
        kind1Mapper.insert(kind1);
    }

    public List<Kind2> getAllkind2(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Kind2> kind2s = kind2Mapper.selectByExample(null);
        return kind2s;
    }

    public int getCountkind2() {
        return (int)kind2Mapper.countByExample(null);
    }

    public List<Kind2> getAllkind2Search(Integer page, Integer limit, String name) {
        Kind2Example example = new Kind2Example();
        Kind2Example.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%"+name+"%");
        PageHelper.startPage(page,limit);
        List<Kind2> kind2s = kind2Mapper.selectByExample(example);
        return kind2s;
    }

    public int getCountkind2Search(String name) {
        Kind2Example example = new Kind2Example();
        Kind2Example.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%"+name+"%");
        return (int)kind2Mapper.countByExample(example);
    }

    public boolean checkDelKind2(Kind2 kind2) {
        Kind3Example example = new Kind3Example();
        Kind3Example.Criteria criteria = example.createCriteria();
        criteria.andK2EqualTo(kind2.getId());
        List<Kind3> kind3s = kind3Mapper.selectByExample(example);
        if (kind3s.isEmpty()) {
            return false;
        }

        return true;
    }

    public void delKind2(Kind2 kind2) {
        kind2Mapper.deleteByPrimaryKey(kind2.getId());
    }

    public void updateKind2(Kind2 kind2) {
        kind2Mapper.updateByPrimaryKeySelective(kind2);
    }

    public void saveKind2(Kind2 kind2) {
        kind2Mapper.insert(kind2);
    }

    public List<Kind3> getAllkind3(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Kind3> kind3s = kind3Mapper.selectByExample(null);
        return kind3s;
    }

    public int getCountkind3() {
        return (int)kind3Mapper.countByExample(null);
    }

    public List<Kind3> getAllkind3Search(Integer page, Integer limit, String name) {
        Kind3Example example = new Kind3Example();
        Kind3Example.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%"+name+"%");
        PageHelper.startPage(page,limit);
        List<Kind3> kind3s = kind3Mapper.selectByExample(example);
        return kind3s;
    }

    public int getCountkind3Search(String name) {
        Kind3Example example = new Kind3Example();
        Kind3Example.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%"+name+"%");
        return (int)kind3Mapper.countByExample(example);
    }

    public void delKind3(Kind3 kind3) {
        kind3Mapper.deleteByPrimaryKey(kind3.getId());
    }

    public void updateKind3(Kind3 kind3) {
        kind3Mapper.updateByPrimaryKeySelective(kind3);
    }

    public void saveKind3(Kind3 kind3) {
        kind3Mapper.insert(kind3);
    }
}
