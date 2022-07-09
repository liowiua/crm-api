package com.ag.crm.service;

import com.ag.crm.domain.ResultVO;
import com.ag.crm.domain.User;

public interface LoginServcie {
    ResultVO login(User user);

    ResultVO info();

    ResultVO logout();

}
