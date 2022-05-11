package com.fc.controller;

import com.fc.entity.ResultVO;
import com.fc.entity.TScore;
import com.fc.entity.TStudent;
import com.fc.service.ScoreService;
import com.fc.util.Constant;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/score")
@RestController //标识为返回类型为Json的控制
public class ScoreController {

    //先弄一个
    @Autowired
    private ScoreService scoreService;
    @RequestMapping("/addScore")
    public ResultVO<Object> addScore(TScore score){
        return scoreService.insertScore(score);
    }
    @RequestMapping("/deleteScore")
    public  ResultVO<Object> deleteScore(TScore tScore){
        Integer total =     scoreService.deleteScore(tScore.getScoreId()) ;
        ResultVO<Object> vo = new ResultVO();
        if (total==0||total==null){
            vo.setCode((Constant.FAILURE_RETUEN_CODE));
            vo.setMsg("删除学生成绩信息失败");
        }else {
            vo.setCode((Constant.SUCCESS_RETUEN_CODE));
            vo.setMsg("删除学生成绩信息成功");
        }
        return vo;
    }
    @RequestMapping("/updateScore")
    public  ResultVO<Object> updateScore(TScore tScore){
        ResultVO<Object> vo = new ResultVO<>();
        Integer total = scoreService.updateScore(tScore);
        if (total==0||total==null){
            vo.setCode((Constant.FAILURE_RETUEN_CODE));
            vo.setMsg("修改学生成绩信息失败");
        }else {
            vo.setCode((Constant.SUCCESS_RETUEN_CODE));
            vo.setMsg("修改学生信息成功");
        }
        return  vo;
    }
    @RequestMapping("/getAllScore")
    public  ResultVO<List<TScore>> getAllScore(TScore tscore, @RequestParam(defaultValue = "1") int limit,  @RequestParam(defaultValue = "5") int page){
        //进入查询，获得查询结果
        PageInfo<TScore> pageInfo =  scoreService.getAllScore(tscore,limit,page);
        //获取前端结果集
        ResultVO<List<TScore>> rs = new ResultVO<>();
        //创建List对象进行接收获取所有的
        List<TScore> list = pageInfo.getList();

        for (TScore temp:list){
            //获取所有的getScoreType
            String type = temp.getScoreType();
            //闲的了
            if("1".equals(type)) {
                temp.setScoreTypeName("习题");
            }
            if("2".equals(type)) {
                temp.setScoreTypeName("测验");
            }
            if("3".equals(type)) {
                temp.setScoreTypeName("考试成绩66666666666666666666666666666666666666");
            }
        }
        rs.setCode((Constant.SUCCESS_RETUEN_CODE));
        rs.setMsg("查询成功");
        //结果集
        rs.setData(list);
        //数量
        rs.setCount(pageInfo.getTotal());
        return rs;
    }
    @RequestMapping("/getAllSumScore")
    //因为要获取所有成绩所以不用TScore而是用TStudent
    public  ResultVO<List<TStudent>> getAllSumScore(TStudent student,@RequestParam(defaultValue = "1") int limit,  @RequestParam(defaultValue = "5") int page){
        PageInfo<TStudent> pageInfo = scoreService.getAllFinalScore(student,limit,page);
        ResultVO<List<TStudent>> rs = new ResultVO<>();
        rs.setCode((Constant.SUCCESS_RETUEN_CODE));
        rs.setMsg("查询成功");
        rs.setData(pageInfo.getList());
        rs.setCount(pageInfo.getTotal());
        return rs;
    }
    @RequestMapping("/getMyScore")
    public  ResultVO<List<TStudent>> getMyScore(HttpServletRequest request,@RequestParam(defaultValue = "1") int limit,  @RequestParam(defaultValue = "5") int page){
        //通过这个获取tstudent对象
        TStudent student = (TStudent) request.getSession().getAttribute("student");
        PageInfo<TStudent> pageInfo =  scoreService.getAllFinalScore(student,limit,page);
        ResultVO<List<TStudent>> rs = new ResultVO<>();
        rs.setCode((Constant.SUCCESS_RETUEN_CODE));
        rs.setMsg("查询成功");
        rs.setData(pageInfo.getList());
        rs.setCount(pageInfo.getTotal());
        return rs;
    }
    @RequestMapping("/getMyScoreInfo")
    public ResultVO<List<TScore>> getMyScoreInfo(HttpServletRequest request,@RequestParam(defaultValue = "1") int limit,  @RequestParam(defaultValue = "5") int page){
        TStudent student = (TStudent) request.getSession().getAttribute("student");
        TScore tScore = new TScore();
        //拿来吧getStudentNo等于学号，先获取学号
        tScore.setStudentId(student.getStudentNo());
        PageInfo<TScore> pageInfo=scoreService.getAllScore(tScore,limit, page);

        ResultVO<List<TScore>> rs = new ResultVO<>();
        List<TScore> list=pageInfo.getList();
        for(TScore temp:list) {
            String type=temp.getScoreType();
            if("1".equals(type)) {
                temp.setScoreTypeName("习题");
            }
            if("2".equals(type)) {
                temp.setScoreTypeName("测验");
            }
            if("3".equals(type)) {
                temp.setScoreTypeName("考试成绩");
            }
        }
        rs.setCode((Constant.SUCCESS_RETUEN_CODE));
        rs.setMsg("查询成功");
        rs.setData(list);
        rs.setCount(pageInfo.getTotal());
        return rs;
    }
}