package com.fc.service.impl;

import com.fc.dao.TScoreMapper;
import com.fc.dao.TStudentMapper;
import com.fc.entity.ResultVO;
import com.fc.entity.TScore;
import com.fc.entity.TStudent;
import com.fc.service.ScoreService;
import com.fc.util.Constant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private TScoreMapper scoreMapper;
    @Autowired
    private TStudentMapper studentMapper;
    @Override
    public ResultVO<Object> insertScore(TScore score) {
       //先看看有多少
        int count = scoreMapper.countByType(score);
//获取里面的值
        String type = score.getScoreType();
boolean flag = true;
String str = "";
switch (Integer.parseInt(type)){
    case 1:
        if (count>=16){
            flag = false;
            str = "改学生的习题已经达到16条";
        }
        break;
    case 2:
        if (count>=3){
            flag = false;
            str = "改学生成绩已经有三条";
        }
        break;
    case 3:
        if (count>=1){
            flag = false;
            str = "改学生的考试成绩已经达到一条";
        }
        break;
    default:
        flag  = true;
        break;
}
//统一返回
        ResultVO<Object> re = new ResultVO<>();
if (flag){
    Integer dso = scoreMapper.insert(score);
    if (dso == null || dso ==0){
        re.setCode(Integer.valueOf(Constant.HASE_RETUEN_CODE));
    re.setMsg("失败");
    }else {
        re.setCount(Long.valueOf(Constant.SUCCESS_RETUEN_CODE));
        re.setMsg("成绩添加成功");
    }
}else {
    re.setCode(Integer.valueOf(Constant.HASE_RETUEN_CODE));
    re.setMsg(str);
}
        return re;
    }

    @Override
    public Integer deleteScore(Integer scoreId) {
        return scoreMapper.deleteByPrimaryKey(scoreId);
    }
    @Override
    public Integer updateScore(TScore tScore) {
        return scoreMapper.updateByPrimaryKey(tScore);
    }

    @Override
    public PageInfo<TScore> getAllScore(TScore tscore, int limit, int page) {
      //开启分页
        PageHelper.startPage(page,limit);
        //查询分页信息，调用方式和普通方式一致
        List<TScore> list = scoreMapper.selectByExample(null);
        //生产对象
        PageInfo<TScore> pageInfo = new PageInfo<>(list);
        return pageInfo;


    }

    @Override
    public PageInfo<TStudent> getAllFinalScore(TStudent student, int limit, int page) {
    //开启分页
        PageHelper.startPage(limit,page);
        //查询分页信息，调用方式和普通方式一致,
        List<TStudent> list=    studentMapper.selectAll(student);
    //遍历
        for (TStudent temp:list){
            double sum = 0;
            Map<String,Object> map=  studentMapper.selectFinalScore(temp);
            DecimalFormat df = new DecimalFormat("#.00");
            double score1=Double.parseDouble(map.get("score1").toString());
            temp.setScore1(Double.parseDouble(df.format(score1)));
            double score2=Double.parseDouble(map.get("score2").toString());
            temp.setScore2(Double.parseDouble(df.format(score2)));
            double score3=Double.parseDouble(map.get("score3").toString());
            temp.setScore3(Double.parseDouble(df.format(score3)));
            sum=(score1)+(score2)+(score3);
            temp.setSumScore(Double.parseDouble(df.format(sum)));
        }

        return new PageInfo<TStudent>(list);
    }


}
