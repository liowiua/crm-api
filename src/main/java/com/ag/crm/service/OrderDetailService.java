package com.ag.crm.service;

import com.ag.crm.domain.ResultVO;

public interface OrderDetailService {
    ResultVO selectByOrderId(Integer orderId);
}
