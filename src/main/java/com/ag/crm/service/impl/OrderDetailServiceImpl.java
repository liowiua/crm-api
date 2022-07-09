package com.ag.crm.service.impl;

import com.ag.crm.dao.OrderDetailsMapper;
import com.ag.crm.domain.ResultVO;
import com.ag.crm.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailsMapper orderDetailsMapper;

    @Override
    public ResultVO selectByOrderId(Integer orderId) {

        return ResultVO.success("查询成功",orderDetailsMapper.selectByOrderId(orderId));
    }
}
