package com.ag.crm.service;

import com.ag.crm.domain.SaleChance;
import com.ag.crm.domain.SaleChanceDTO;
import com.ag.crm.domain.ResultVO;
import com.ag.crm.domain.SaleChanceVO;

public interface SaleChanceService {


    ResultVO selectAllByPage(Integer pageCount,Integer pageSize);

    ResultVO selectByCondition(SaleChanceDTO saleChanceDTO);

    ResultVO addSaleChance(SaleChance saleChance);

    ResultVO delectASaleChance(Integer saleChanceId);

    ResultVO delectMultipleSaleChance(String saleChanceIds);

    ResultVO editSaleChance(SaleChanceVO saleChance);

    ResultVO selectSaleChanceById(Integer id);

    ResultVO updateDevResult(Integer id, Integer devResult);
}
