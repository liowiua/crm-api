package com.ag.crm.service.impl;

import com.ag.crm.dao.CustomerLinkmanMapper;
import com.ag.crm.domain.CustomerLinkman;
import com.ag.crm.domain.ResultVO;
import com.ag.crm.service.CustomerLinkmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CustomerLinkmanServiceImpl implements CustomerLinkmanService {

    @Autowired
    private CustomerLinkmanMapper customerLinkmanMapper;

    @Override
    public ResultVO selectByCId(Integer cId) {
        List<CustomerLinkman> customerLinkmanList = customerLinkmanMapper.selectByCId(cId);
        return ResultVO.success("查询成功",customerLinkmanList);
    }

    @Override
    public ResultVO insertLinkman(CustomerLinkman customerLinkman) {

        customerLinkman.setCeateDate(new Date());
        customerLinkman.setIsValid(1);
        customerLinkman.setUpdateDate(new Date());
        int insert = customerLinkmanMapper.insert(customerLinkman);
        if (insert > 0){
            return ResultVO.success("添加成功",null);
        }

        return ResultVO.fail("添加失败");
    }

    @Override
    public ResultVO updateLinkman(CustomerLinkman customerLinkman) {
        customerLinkman.setUpdateDate(new Date());
        int i = customerLinkmanMapper.updateByPrimaryKey(customerLinkman);
        if (i > 0){
            return ResultVO.success("修改成功",null);
        }
        return ResultVO.fail("修改失败");
    }

    @Override
    public ResultVO deleteLinkmanById(Integer id) {

        int i = customerLinkmanMapper.deleteByPrimaryKey(id);
        if (i > 0){
            return ResultVO.success("删除成功",null);
        }

        return ResultVO.fail("删除失败");
    }

    @Override
    @Transactional
    public ResultVO deleteLinkmanByIds(String ids) {

        String[] split = ids.split(",");
        for (String s : split) {
            customerLinkmanMapper.deleteByPrimaryKey(Integer.parseInt(s));
        }

        return ResultVO.success("删除成功",null);
    }
}
