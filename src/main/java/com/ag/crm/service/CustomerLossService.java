package com.ag.crm.service;

import com.ag.crm.domain.CustomerLossDTO;
import com.ag.crm.domain.ResultVO;

public interface CustomerLossService {
    ResultVO customerLossAnalysis(Integer pageCount,Integer pageSize);

    ResultVO selectByParams(CustomerLossDTO customerLossDTO);

    ResultVO selectById(Integer lossId);

    ResultVO updateCustomerLossStateById(Integer id, String lossReason);
}
