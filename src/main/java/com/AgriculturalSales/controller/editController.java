package com.AgriculturalSales.controller;

import com.AgriculturalSales.bean.Edit;
import com.AgriculturalSales.bean.Msg;
import com.AgriculturalSales.dao.EditMapper;
import com.AgriculturalSales.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class editController {
    @Autowired
    EditMapper editMapper;

    @Autowired
    BaseService baseService;

    @RequestMapping("/edit")
    public String edit(Map<String, Object> map) {
        List<Edit> edits = editMapper.selectByExample(null);
        int i = 1;
        for (Edit edit : edits) {
            map.put("img" + (i++), edit.getPath());
        }
        return "manager/edit";
    }


    @RequestMapping("/editImg1")
    @ResponseBody
    public Msg editImg1(MultipartFile file, HttpServletRequest request) {
        String path = baseService.saveFile(file, request);
        Edit edit = new Edit();
        edit.setId(1);
        edit.setPath(path);
        editMapper.updateByPrimaryKeySelective(edit);
        return Msg.success();
    }

    @RequestMapping("/editImg2")
    @ResponseBody
    public Msg editImg2(MultipartFile file, HttpServletRequest request) {
        String path = baseService.saveFile(file, request);
        Edit edit = new Edit();
        edit.setId(2);
        edit.setPath(path);
        editMapper.updateByPrimaryKeySelective(edit);
        return Msg.success();
    }

    @RequestMapping("/editImg3")
    @ResponseBody
    public Msg editImg3(MultipartFile file, HttpServletRequest request) {
        String path = baseService.saveFile(file, request);
        Edit edit = new Edit();
        edit.setId(3);
        edit.setPath(path);
        editMapper.updateByPrimaryKeySelective(edit);
        return Msg.success();
    }

    @RequestMapping("/editImg4")
    @ResponseBody
    public Msg editImg4(MultipartFile file, HttpServletRequest request) {
        String path = baseService.saveFile(file, request);
        Edit edit = new Edit();
        edit.setId(4);
        edit.setPath(path);
        editMapper.updateByPrimaryKeySelective(edit);
        return Msg.success();
    }

}
