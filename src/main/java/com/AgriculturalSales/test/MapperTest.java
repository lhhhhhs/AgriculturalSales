package com.AgriculturalSales.test;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.AgriculturalSales.bean.*;
import com.AgriculturalSales.dao.*;
import com.AgriculturalSales.service.SyntheticService;
import com.AgriculturalSales.utils.phone.BasePhone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class MapperTest {


	@Autowired
	AdminMapper adminMapper;

	@Autowired
	UserMapper userMapper;


	@Autowired
	ProductMapper productMapper;

	@Autowired
	Kind1Mapper kind1Mapper;
	@Autowired
    Kind2Mapper kind2Mapper;
	@Autowired
    Kind3Mapper kind3Mapper;

	@Autowired
	SyntheticService syntheticService;

	@Autowired
	CartMapper cartMapper;

	@Autowired
	SalesRallyMapper salesRallyMapper;

	@Autowired
	KindSalesMapper kindSalesMapper;

	@Autowired
	NewProductMapper newProductMapper;
	/**
	 * 测试DepartmentMapper
	 */
	@Test
	public void testCRUD() {

		//adminMapper.insert(new Admin(null,"admin","admin"));
		//测试Admin dao
//		AdminExample adminExample = new AdminExample();
//		AdminExample.Criteria criteria = adminExample.createCriteria();
//		criteria.andAdminNameEqualTo("admin");
//		List<Admin> admins = adminMapper.selectByExample(adminExample);
//		System.out.println(admins.get(0));

		//测试User dao

//		userMapper.insert(new User(null, 17339824610, "123123qwe", 1));

//        for (int i = 0; i < 50; i++) {
//            productMapper.insert(new Product(null, "xxx"+i, new BigDecimal(10),10,10,"1","1",1));
//        }


//        List<Kind1> kind1s = kind1Mapper.selectByExample(null);
//        for (Kind1 kind1 : kind1s) {
//            System.out.println(kind1);
//        }

//        List<Kind3> kind3s = kind3Mapper.selectByExample(null);
//        for (Kind3 kind3 : kind3s) {
//            System.out.println(kind3);
//        }
//        for (int i = 0; i < 20; i++) {
//            String substring = UUID.randomUUID().toString().substring(0, 6);
//            productMapper.insert(new Product(null, substring+i,new BigDecimal(10),20,20,"/static/images/dzhi_ad.jpg",null,2,4,11));
//        }


//		Kind1Example example = new Kind1Example();
//		Kind1Example.Criteria criteria = example.createCriteria();
//		criteria.andIdEqualTo(1);
//		List<Kind1> kind1s = kind1Mapper.selectByExampleWithK2(null);
//		System.out.println(kind1s.size());
//		for (Kind1 kind1 : kind1s) {
//			System.out.println(kind1);
//		}

//		syntheticService.getAllProduct();

//		syntheticService.getAllKind();

//		CartExample example = new CartExample();
//		CartExample.Criteria criteria = example.createCriteria();
//		criteria.andUIdEqualTo(1);
//		List<Cart> carts = cartMapper.selectByExampleWithProduct(example);
//		for (Cart cart : carts) {
//			System.out.println(cart);
//		}

//		BasePhone.phoneCode("17339824610");

//		List<SalesRally> salesRallies = salesRallyMapper.selectByExample(null);
//		for (SalesRally salesRally : salesRallies) {
//			System.out.println(salesRally);
//		}

//		//kind集合
//		List<Kind1> kind1s = kind1Mapper.selectByExample(null);
//		//时间集合
//		List<LocalDate> timeList = new ArrayList<>();
//		//数据map
//		Map<String,List<Integer>> map = new HashMap<>();
//		for (Kind1 kind1 : kind1s) {
//			KindSalesExample example = new KindSalesExample();
//			example.setOrderByClause("creat_time");
//			KindSalesExample.Criteria criteria = example.createCriteria();
//			criteria.andK1EqualTo(kind1.getId());
//			List<KindSales> kindSales = kindSalesMapper.selectByExample(example);
//			List<KindSales> kindSales1 = kindSales.subList(kindSales.size() - 7, kindSales.size());
//			List<Integer> kindSalesList = new ArrayList<>();
//			timeList.clear();
//			for (KindSales kindSale : kindSales1) {
//				timeList.add(kindSale.getCreatTime());
//				kindSalesList.add(kindSale.getSales());
//			}
//			map.put(kind1.getName(), kindSalesList);
//		}
//
//		System.out.println("kind1s = " + kind1s);
//		System.out.println("timeList = " + timeList);
//		System.out.println("map = " + map);


		for (int i = 0; i < 20; i++) {
			String substring = UUID.randomUUID().toString().substring(0, 6);
			newProductMapper.insert(new NewProduct(null, 1, substring, new BigDecimal(10), 100, 100, null, "xxx", 1, 1, 1, null, LocalDate.now()));
		}


	}

}
