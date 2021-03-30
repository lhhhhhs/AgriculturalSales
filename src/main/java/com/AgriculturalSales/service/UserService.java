package com.AgriculturalSales.service;

import com.AgriculturalSales.bean.Order;
import com.AgriculturalSales.bean.OrderExample;
import com.AgriculturalSales.bean.User;
import com.AgriculturalSales.bean.UserExample;
import com.AgriculturalSales.dao.UserMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;


    /**
     * 检查号码是否重复注册
     * @param phone
     * @return true-号码可用 false-号码不可用
     */
    public boolean checkedPhone(String phone) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone);
        List<User> users = userMapper.selectByExample(example);
        return users.isEmpty();
    }

    /**
     * 保存用户信息到数据库
     * @param user
     */
    public void save(User user) {
        user.setKind(1);
        userMapper.insert(user);
    }

    /**
     * 检验用户名和密码是否匹配
     * @param loginPhone
     * @param loginPwd
     * @return true-匹配 false-不匹配
     */
    public boolean checkedUser(String loginPhone, String loginPwd) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(loginPhone);
        List<User> users = userMapper.selectByExample(example);
        if(!users.isEmpty()) {
            if (users.get(0).getPwd().equals(loginPwd)) {
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    public User getByPhone(String loginPhone) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(loginPhone);
        List<User> users = userMapper.selectByExample(example);
        if(users.isEmpty()){
            return null;
        }else {
            return users.get(0);
        }
    }

    public User getById(Integer uid) {
        return userMapper.selectByPrimaryKey(uid);
    }

    public List<User> getAll(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<User> users = userMapper.selectByExample(null);
        return users;
    }

    public int getCount() {
        return (int)userMapper.countByExample(null);
    }

    public List<User> getAllByPhone(Integer page, Integer limit, String phone) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneLike("%"+phone+"%");
        PageHelper.startPage(page,limit);
        List<User> users = userMapper.selectByExample(example);
        return users;
    }

    public int getCountByPhone(String phone) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneLike("%"+phone+"%");
        return (int)userMapper.countByExample(example);
    }
}
