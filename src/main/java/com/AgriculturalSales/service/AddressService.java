package com.AgriculturalSales.service;

import cn.hutool.extra.spring.SpringUtil;
import com.AgriculturalSales.bean.*;
import com.AgriculturalSales.dao.*;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    AddressMapper addressMapper;
    @Autowired
    ProvincesMapper provincesMapper;
    @Autowired
    CitiesMapper citiesMapper;
    @Autowired
    AreasMapper areasMapper;
    @Autowired
    OrderMapper orderMapper;

    /**
     * 根据用户id获得地址
     *
     * @param id
     * @return
     */
    public List<Address> getAddressListByUser(Integer id) {
        AddressExample example = new AddressExample();
        AddressExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(id);
        List<Address> addressList = addressMapper.selectByExample(example);
        if(addressList.isEmpty()){
            return null;
        }
        return addressList;
    }

    /**
     * 将省市区的code码转化为省市区
     * @param address
     * @return
     */
    public Address codeToAddress(Address address) {
        String provinceCode = address.getProvince();
        String cityCode = address.getCity();
        String areaCode = address.getArea();
        ProvincesExample example = new ProvincesExample();
        ProvincesExample.Criteria criteria = example.createCriteria();
        criteria.andProvinceidEqualTo(Integer.parseInt(provinceCode));
        List<Provinces> provinces = provincesMapper.selectByExample(example);
        if (!provinces.isEmpty()) {
            Provinces provinces1 = provinces.get(0);
            String province = provinces1.getProvince();
            address.setProvince(province);
        }
        CitiesExample example1 = new CitiesExample();
        CitiesExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andCityidEqualTo(cityCode);
        List<Cities> cities = citiesMapper.selectByExample(example1);
        if (!cities.isEmpty()) {
            Cities cities1 = cities.get(0);
            String city = cities1.getCity();
            address.setCity(city);
        }
        AreasExample example3 = new AreasExample();
        AreasExample.Criteria criteria2 = example3.createCriteria();
        criteria2.andAreaidEqualTo(areaCode);
        List<Areas> areas = areasMapper.selectByExample(example3);
        if (!areas.isEmpty()) {
            Areas areas1 = areas.get(0);
            String area = areas1.getArea();
            address.setArea(area);
        }
        return address;
    }

    /**
     * 保存地址
     * @param address1
     */
    public void saveAddress(Address address1) {
        addressMapper.insert(address1);
    }


    public void delAdd(Integer addressId) {
        addressMapper.deleteByPrimaryKey(addressId);
    }

    /**
     * 判断是不是第一个地址，是的话返回false
     * @param id
     * @return
     */
    public Boolean checkAddressByUser(Integer id) {
        AddressExample example = new AddressExample();
        AddressExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(id);
        long l = addressMapper.countByExample(example);
        if (l==0){
            return false;
        }else {
         return true;
        }

    }

    /**
     * 判断改地址下有无未完成的订单 返回true代表可以删除（没有订单）
     * @param addressId
     * @return
     */
    public Boolean checkAddressByOrder(Integer addressId) {
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andAddressIdEqualTo(addressId);
        criteria.andStatusNotEqualTo(5);
        long l = orderMapper.countByExample(example);
        if(l == 0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 根据id获得地址
     * @param addressId
     * @return
     */
    public Address getAddressById(Integer addressId) {
        return addressMapper.selectByPrimaryKey(addressId);
    }

    /**
     * 更新地址
     * @param address
     */
    public void updateAddress(Address address) {
        addressMapper.updateByPrimaryKeySelective(address);
    }

    /**
     * 检查地址是不是默认地址 返回false不是默认返回true是默认
     * @param addressId
     * @return
     */
    public Boolean checkAddressIsDefaultByOrder(Integer addressId) {
        Address address = addressMapper.selectByPrimaryKey(addressId);
        Integer defaultStatus = address.getDefaultStatus();
        if (defaultStatus==0){
            return false;
        }else {
            return true;
        }
    }

    /**
     * 设置默认地址
     * @param addressId
     * @param user
     */
    public void setDefaultAddress(Integer addressId, User user) {
        Address address = new Address();
        address.setDefaultStatus(0);
        AddressExample example = new AddressExample();
        AddressExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(user.getId());
        addressMapper.updateByExampleSelective(address,example);
        Address address1 = new Address();
        address1.setId(addressId);
        address1.setDefaultStatus(1);
        addressMapper.updateByPrimaryKeySelective(address1);
    }

    public List<Provinces> getAllProvince(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Provinces> provinces = provincesMapper.selectByExample(null);
        return provinces;
    }

    public int getCountProvince() {
        return (int)provincesMapper.countByExample(null);
    }

    public List<Provinces> getAllProvinceSearch(Integer page, Integer limit, String name) {
        ProvincesExample example = new ProvincesExample();
        ProvincesExample.Criteria criteria = example.createCriteria();
        criteria.andProvinceLike("%"+name+"%");
        PageHelper.startPage(page,limit);
        List<Provinces> provinces = provincesMapper.selectByExample(example);
        return provinces;
    }

    public int getCountProvinceSearch(String name) {
        ProvincesExample example = new ProvincesExample();
        ProvincesExample.Criteria criteria = example.createCriteria();
        criteria.andProvinceLike("%"+name+"%");
        return (int)provincesMapper.countByExample(example);
    }

    public void updateProvince(Provinces provinces) {
        provincesMapper.updateByPrimaryKeySelective(provinces);
    }

    public void insertProvince(Provinces provinces) {
        provincesMapper.insert(provinces);
    }

    public List<Cities> getAllCity(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Cities> cities = citiesMapper.selectByExample(null);
        return cities;
    }

    public int getCountCity() {
        return (int)citiesMapper.countByExample(null);
    }

    public List<Cities> getAllCitySearch(Integer page, Integer limit, String name) {
        CitiesExample example = new CitiesExample();
        CitiesExample.Criteria criteria = example.createCriteria();
        criteria.andCityLike("%"+name+"%");
        PageHelper.startPage(page,limit);
        List<Cities> cities = citiesMapper.selectByExample(example);
        return cities;
    }

    public int getCountCitySearch(String name) {
        CitiesExample example = new CitiesExample();
        CitiesExample.Criteria criteria = example.createCriteria();
        criteria.andCityLike("%"+name+"%");
        return (int)citiesMapper.countByExample(example);
    }

    public void updateCity(Cities cities) {
        citiesMapper.updateByPrimaryKeySelective(cities);
    }

    public void addCity(Cities cities) {
        citiesMapper.insert(cities);
    }

    public List<Areas> getAllArea(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Areas> areas = areasMapper.selectByExample(null);
        return areas;
    }

    public int getCountAreae() {
        return (int)areasMapper.countByExample(null);
    }

    public List<Areas> getAllAreaSearch(Integer page, Integer limit, String name) {
        AreasExample example = new AreasExample();
        AreasExample.Criteria criteria = example.createCriteria();
        criteria.andAreaLike("%"+name+"%");
        PageHelper.startPage(page,limit);
        List<Areas> areas = areasMapper.selectByExample(example);
        return areas;
    }

    public int getCountAreaeSearch(String name) {
        AreasExample example = new AreasExample();
        AreasExample.Criteria criteria = example.createCriteria();
        criteria.andAreaLike("%"+name+"%");
        return (int)areasMapper.countByExample(example);
    }

    public void updateAreas(Areas areas) {
        areasMapper.updateByPrimaryKeySelective(areas);
    }

    public void addAreas(Areas areas) {
        areasMapper.insert(areas);
    }
}
