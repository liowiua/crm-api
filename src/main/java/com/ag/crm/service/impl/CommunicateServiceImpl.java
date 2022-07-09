package com.ag.crm.service.impl;


import com.ag.crm.dao.CustomerContactMapper;
import com.ag.crm.domain.CustomerContact;
import com.ag.crm.domain.ResultVO;
import com.ag.crm.service.CommunicateService;
import com.ag.crm.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommunicateServiceImpl implements CommunicateService {


    @Autowired
    private CustomerContactMapper customerContactMapper;

    @Override
    public ResultVO selectByCId(Integer cId, Integer pageCount, Integer pageSize) {
        int start = (pageCount-1)*pageSize;
        List<CustomerContact> customerContacts = customerContactMapper.selectByCId(cId,start,pageSize);

        int count = customerContactMapper.selectCount(cId);
        int pageCounts = count%pageSize==0?count/pageSize:count/pageSize+1;
        return ResultVO.success("查询成功",new PageHelper<>(count,pageCounts,customerContacts));

    }

    @Override
    public ResultVO insertCommunication(CustomerContact customerContact) {

        customerContact.setCreateDate(new Date());
        customerContact.setUpdateDate(new Date());
        customerContact.setIsValid(1);
        int insert = customerContactMapper.insert(customerContact);
        if (insert>0){
            return ResultVO.success("添加成功",null);
        }

        return ResultVO.fail("添加失败");
    }
}
