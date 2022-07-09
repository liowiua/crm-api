package com.ag.crm.service;

import com.ag.crm.domain.CustomerReprieve;
import com.ag.crm.domain.ResultVO;

public interface CustomerReprieveService {
    ResultVO selectByLossId(Integer lossId, Integer pageCount, Integer pageSize);

    ResultVO insertCustomerReprieve(CustomerReprieve customerReprieve);

    ResultVO updateCustomerReprieve(CustomerReprieve customerReprieve);

    ResultVO selectById(Integer id);

    ResultVO deleteById(Integer id);
}
