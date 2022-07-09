package com.ag.crm.service;

import com.ag.crm.domain.CustomerOrderDTO;
import com.ag.crm.domain.ResultVO;

public interface CustomerOrderService {
    ResultVO selectCOrderById(Integer cId,Integer pageCount,Integer pageSize);

    ResultVO insertOrder(CustomerOrderDTO customerOrderDTO);
}
