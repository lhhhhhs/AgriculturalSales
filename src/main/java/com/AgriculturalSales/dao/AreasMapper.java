package com.AgriculturalSales.dao;

import com.AgriculturalSales.bean.Areas;
import com.AgriculturalSales.bean.AreasExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AreasMapper {
    long countByExample(AreasExample example);

    int deleteByExample(AreasExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Areas record);

    int insertSelective(Areas record);

    List<Areas> selectByExample(AreasExample example);

    Areas selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Areas record, @Param("example") AreasExample example);

    int updateByExample(@Param("record") Areas record, @Param("example") AreasExample example);

    int updateByPrimaryKeySelective(Areas record);

    int updateByPrimaryKey(Areas record);
}