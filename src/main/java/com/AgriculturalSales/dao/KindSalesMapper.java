package com.AgriculturalSales.dao;

import com.AgriculturalSales.bean.KindSales;
import com.AgriculturalSales.bean.KindSalesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface KindSalesMapper {
    long countByExample(KindSalesExample example);

    int deleteByExample(KindSalesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(KindSales record);

    int insertSelective(KindSales record);

    List<KindSales> selectByExample(KindSalesExample example);

    KindSales selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") KindSales record, @Param("example") KindSalesExample example);

    int updateByExample(@Param("record") KindSales record, @Param("example") KindSalesExample example);

    int updateByPrimaryKeySelective(KindSales record);

    int updateByPrimaryKey(KindSales record);
}