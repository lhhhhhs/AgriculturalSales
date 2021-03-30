package com.AgriculturalSales.service;

import com.AgriculturalSales.bean.Admin;
import com.AgriculturalSales.bean.AdminExample;
import com.AgriculturalSales.bean.Order;
import com.AgriculturalSales.bean.OrderExample;
import com.AgriculturalSales.dao.AdminMapper;
import com.github.pagehelper.PageHelper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    AdminMapper adminMapper;

    /**
     * 判断是不是管理员登录
     * @param loginPhone
     * @param loginPwd
     * @return
     */
    public boolean check(String loginPhone, String loginPwd, HttpSession session) {
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andAdminNameEqualTo(loginPhone);
        List<Admin> admins = adminMapper.selectByExample(example);
        if(!admins.isEmpty()){
            Admin admin = admins.get(0);
            if (loginPwd.equals(admin.getAdminPs())) {
                session.setAttribute("user",admin);
                return true;
            }
        }
        return false;
    }

    public List<Admin> getAll(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Admin> admins = adminMapper.selectByExample(null);
        return admins;
    }

    public int getCount() {
        return (int)adminMapper.countByExample(null);
    }

    public List<Admin> getAllByName(Integer page, Integer limit, String name) {
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andAdminNameLike("%"+name+"%");
        PageHelper.startPage(page,limit);
        List<Admin> admins = adminMapper.selectByExample(example);
        return admins;
    }

    public int getCountByName(String name) {
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andAdminNameLike("%"+name+"%");
        return (int)adminMapper.countByExample(example);
    }

    public void updatePwd(String p2, Integer id) {
        Admin admin = new Admin(id, null, p2);
        adminMapper.updateByPrimaryKeySelective(admin);
    }

    public Boolean checkPwd(Integer id, String p1) {
        Admin admin = adminMapper.selectByPrimaryKey(id);
        if (admin.getAdminPs().equals(p1)) {
            return true;
        }else {
            return false;
        }
    }
}
