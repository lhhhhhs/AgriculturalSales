package com.AgriculturalSales.dao;

import com.AgriculturalSales.bean.Edit;
import com.AgriculturalSales.bean.EditExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EditMapper {
    long countByExample(EditExample example);

    int deleteByExample(EditExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Edit record);

    int insertSelective(Edit record);

    List<Edit> selectByExample(EditExample example);

    Edit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Edit record, @Param("example") EditExample example);

    int updateByExample(@Param("record") Edit record, @Param("example") EditExample example);

    int updateByPrimaryKeySelective(Edit record);

    int updateByPrimaryKey(Edit record);
}