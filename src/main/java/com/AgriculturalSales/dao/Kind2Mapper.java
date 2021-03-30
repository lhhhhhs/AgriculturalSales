package com.AgriculturalSales.dao;

import com.AgriculturalSales.bean.Kind2;
import com.AgriculturalSales.bean.Kind2Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Kind2Mapper {
    long countByExample(Kind2Example example);

    int deleteByExample(Kind2Example example);

    int deleteByPrimaryKey(Integer id);

    int insert(Kind2 record);

    int insertSelective(Kind2 record);

    List<Kind2> selectByExample(Kind2Example example);

    Kind2 selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Kind2 record, @Param("example") Kind2Example example);

    int updateByExample(@Param("record") Kind2 record, @Param("example") Kind2Example example);

    int updateByPrimaryKeySelective(Kind2 record);

    int updateByPrimaryKey(Kind2 record);
}