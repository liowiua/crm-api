package com.ag.crm.service;

import com.ag.crm.domain.Datadic;
import com.ag.crm.domain.ResultVO;

public interface DatadicService {
    ResultVO selectAll(Integer pageCount,Integer pageSize);

    ResultVO insertDatadic(Datadic datadic);

    ResultVO updateDatadic(Datadic datadic);

    ResultVO deleteDatadicById(Integer id);

    ResultVO deleteDatadicByIds(String ids);
}
