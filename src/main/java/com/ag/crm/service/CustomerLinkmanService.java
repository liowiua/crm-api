package com.ag.crm.service;

import com.ag.crm.domain.CustomerLinkman;
import com.ag.crm.domain.ResultVO;

public interface CustomerLinkmanService {
    ResultVO selectByCId(Integer cId);

    ResultVO insertLinkman(CustomerLinkman customerLinkman);

    ResultVO updateLinkman(CustomerLinkman customerLinkman);

    ResultVO deleteLinkmanById(Integer id);

    ResultVO deleteLinkmanByIds(String ids);
}
