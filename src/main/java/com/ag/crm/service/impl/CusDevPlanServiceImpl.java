package com.ag.crm.service.impl;

import com.ag.crm.domain.CusDevPlan;
import com.ag.crm.domain.CusDevPlanDTO;
import com.ag.crm.domain.CusDevPlanVO;
import com.ag.crm.utils.PageHelper;
import com.ag.crm.domain.ResultVO;
import com.ag.crm.dao.CusDevPlanMapper;
import com.ag.crm.dao.SaleChanceMapper;
import com.ag.crm.service.CusDevPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class CusDevPlanServiceImpl implements CusDevPlanService {

    @Autowired
    private CusDevPlanMapper cusDevPlanMapper;

    @Autowired
    private SaleChanceMapper saleChanceMapper;


    @Override
    public ResultVO selectByParams(CusDevPlanDTO cusDevPlanDTO, Integer pageCount, Integer pageSize) {

        int start = (pageCount-1)*pageSize;

        List<CusDevPlanVO> cusDevPlans = cusDevPlanMapper.selectByParams(cusDevPlanDTO,start,pageSize);
        int count = saleChanceMapper.selectCount();
        int pageCounts = count%pageSize==0?count/pageSize:count/pageSize+1;
        return ResultVO.success("查询成功",new PageHelper<>(count,pageCounts,cusDevPlans));
    }

    @Override
    public ResultVO selectBySaleChanceId(Integer id, Integer pageCount, Integer pageSize) {
        int start = (pageCount - 1)*pageSize;
        List<CusDevPlan> cusDevPlans = cusDevPlanMapper.selectBySaleChanceId(id,start,pageSize);
        int count = cusDevPlanMapper.selectCountBySaleChanceId(id);
        int pageCounts = count%pageSize==0?count/pageSize:count/pageSize+1;
        return ResultVO.success("查询成功",new PageHelper<>(count,pageCounts,cusDevPlans));
    }

    @Override
    public ResultVO insertCusDevPlan(CusDevPlan cusDevPlan) {

        cusDevPlan.setCreateDate(new Date());
        cusDevPlan.setIsValid(1);
        int insert = cusDevPlanMapper.insert(cusDevPlan);
        if (insert > 0){
            return ResultVO.success("添加成功",null);
        }

        return ResultVO.fail("添加失败");
    }

    @Override
    public ResultVO updateCusDevPlan(CusDevPlan cusDevPlan) {

        cusDevPlan.setUpdateDate(new Date());
        int judge = cusDevPlanMapper.updateCusDevPlanById(cusDevPlan);
        if (judge > 0){
            return ResultVO.success("更新成功",null);
        }
        return ResultVO.fail("更新失败");
    }

    @Override
    public ResultVO selectById(Integer id) {

        CusDevPlan cusDevPlan = cusDevPlanMapper.selectByPrimaryKey(id);

        return ResultVO.success("查询成功",cusDevPlan);
    }

    @Override
    public ResultVO delectById(Integer id) {
        int i = cusDevPlanMapper.deleteByPrimaryKey(id);
        if (i > 0){
            return ResultVO.success("删除成功",null);
        }
        return ResultVO.fail("删除失败");
    }

}
