package com.fc.dao;

import com.fc.entity.TScore;
import com.fc.entity.TScoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TScoreMapper {
    long countByExample(TScoreExample example);

    int deleteByExample(TScoreExample example);

    int deleteByPrimaryKey(Integer scoreId);

    int insert(TScore record);

    int insertSelective(TScore record);

    List<TScore> selectByExample(TScoreExample example);

    TScore selectByPrimaryKey(Integer scoreId);

    int updateByExampleSelective(@Param("record") TScore record, @Param("example") TScoreExample example);

    int updateByExample(@Param("record") TScore record, @Param("example") TScoreExample example);

    int updateByPrimaryKeySelective(TScore record);

    int updateByPrimaryKey(TScore record);
}