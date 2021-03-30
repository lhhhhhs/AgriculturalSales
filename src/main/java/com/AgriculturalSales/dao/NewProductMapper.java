package com.AgriculturalSales.dao;

import com.AgriculturalSales.bean.NewProduct;
import com.AgriculturalSales.bean.NewProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NewProductMapper {
    long countByExample(NewProductExample example);

    int deleteByExample(NewProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NewProduct record);

    int insertSelective(NewProduct record);

    List<NewProduct> selectByExample(NewProductExample example);

    NewProduct selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NewProduct record, @Param("example") NewProductExample example);

    int updateByExample(@Param("record") NewProduct record, @Param("example") NewProductExample example);

    int updateByPrimaryKeySelective(NewProduct record);

    int updateByPrimaryKey(NewProduct record);
}