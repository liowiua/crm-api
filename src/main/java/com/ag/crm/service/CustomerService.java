package com.ag.crm.service;


import com.ag.crm.domain.Customer;
import com.ag.crm.domain.CustomerDTO;
import com.ag.crm.domain.CustomerVO;
import com.ag.crm.domain.ResultVO;

public interface CustomerService {
    ResultVO insert(CustomerVO customer);

    ResultVO edit(CustomerVO customer);

    ResultVO delete(Integer customerId);


    ResultVO selectByParams(CustomerVO customerVO, Integer pageCount, Integer pageSize);

    ResultVO selectById(Integer id);

    ResultVO selectCustomerContributionByParams(CustomerDTO customerDTO);

    ResultVO countCustomerMake();

    ResultVO countCustomerMake02();

    void updateCustomerState();


//    ResultVO findByFinish(CustomerDTO customerDTO);
}
