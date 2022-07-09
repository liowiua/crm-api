package com.ag.crm.service;

import com.ag.crm.domain.CusDevPlan;
import com.ag.crm.domain.CusDevPlanDTO;
import com.ag.crm.domain.ResultVO;

public interface CusDevPlanService {

    ResultVO selectByParams(CusDevPlanDTO cusDevPlanDTO, Integer pageCount, Integer pageSize);


    ResultVO selectBySaleChanceId(Integer id, Integer pageCount, Integer pageSize);

    ResultVO insertCusDevPlan(CusDevPlan cusDevPlan);

    ResultVO updateCusDevPlan(CusDevPlan cusDevPlan);

    ResultVO selectById(Integer id);

    ResultVO delectById(Integer id);
}
