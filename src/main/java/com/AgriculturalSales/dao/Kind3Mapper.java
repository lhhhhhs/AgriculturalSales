package com.AgriculturalSales.dao;

import com.AgriculturalSales.bean.Kind3;
import com.AgriculturalSales.bean.Kind3Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Kind3Mapper {
    long countByExample(Kind3Example example);

    int deleteByExample(Kind3Example example);

    int deleteByPrimaryKey(Integer id);

    int insert(Kind3 record);

    int insertSelective(Kind3 record);

    List<Kind3> selectByExample(Kind3Example example);

    Kind3 selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Kind3 record, @Param("example") Kind3Example example);

    int updateByExample(@Param("record") Kind3 record, @Param("example") Kind3Example example);

    int updateByPrimaryKeySelective(Kind3 record);

    int updateByPrimaryKey(Kind3 record);
}