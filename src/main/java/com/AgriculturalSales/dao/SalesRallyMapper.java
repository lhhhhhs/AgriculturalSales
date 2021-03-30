package com.AgriculturalSales.dao;

import com.AgriculturalSales.bean.SalesRally;
import com.AgriculturalSales.bean.SalesRallyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SalesRallyMapper {
    long countByExample(SalesRallyExample example);

    int deleteByExample(SalesRallyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SalesRally record);

    int insertSelective(SalesRally record);

    List<SalesRally> selectByExample(SalesRallyExample example);

    SalesRally selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SalesRally record, @Param("example") SalesRallyExample example);

    int updateByExample(@Param("record") SalesRally record, @Param("example") SalesRallyExample example);

    int updateByPrimaryKeySelective(SalesRally record);

    int updateByPrimaryKey(SalesRally record);

    void truncateTable();
}