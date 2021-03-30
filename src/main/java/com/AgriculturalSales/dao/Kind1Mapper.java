package com.AgriculturalSales.dao;

import com.AgriculturalSales.bean.Kind1;
import com.AgriculturalSales.bean.Kind1Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Kind1Mapper {
    long countByExample(Kind1Example example);

    int deleteByExample(Kind1Example example);

    int deleteByPrimaryKey(Integer id);

    int insert(Kind1 record);

    int insertSelective(Kind1 record);

    List<Kind1> selectByExample(Kind1Example example);

    Kind1 selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Kind1 record, @Param("example") Kind1Example example);

    int updateByExample(@Param("record") Kind1 record, @Param("example") Kind1Example example);

    int updateByPrimaryKeySelective(Kind1 record);

    int updateByPrimaryKey(Kind1 record);
}