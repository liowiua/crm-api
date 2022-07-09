package com.ag.crm.service;

import com.ag.crm.domain.CustomerServe;
import com.ag.crm.domain.CustomerServeDTO;
import com.ag.crm.domain.CustomerServeVO;
import com.ag.crm.domain.ResultVO;

public interface CustomerServeService {
    ResultVO selectByParams(CustomerServeDTO customerServeDTO, Integer flag);

    ResultVO insertCustomerServe(CustomerServeVO customerServeVO);

    ResultVO updateCustomerServe(CustomerServeVO customerServeVO);

    ResultVO archivedCustomerServe(Integer pageCount, Integer pageSize);
}
