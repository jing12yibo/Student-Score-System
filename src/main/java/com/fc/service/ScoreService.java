package com.fc.service;

import com.fc.entity.ResultVO;
import com.fc.entity.TScore;
import com.fc.entity.TStudent;
import com.github.pagehelper.PageInfo;

public interface ScoreService {


    ResultVO<Object> insertScore(TScore score);

    Integer deleteScore(Integer scoreId);

    Integer updateScore(TScore tScore);

    PageInfo<TScore> getAllScore(TScore tscore, int limit, int page);


    PageInfo<TStudent> getAllFinalScore(TStudent student, int limit, int page);
}
