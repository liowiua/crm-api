package com.ag.crm.service;

import com.ag.crm.domain.CustomerContact;
import com.ag.crm.domain.ResultVO;

public interface CommunicateService {
    ResultVO selectByCId(Integer cId, Integer pageCount, Integer pageSize);

    ResultVO insertCommunication(CustomerContact customerContact);
}
