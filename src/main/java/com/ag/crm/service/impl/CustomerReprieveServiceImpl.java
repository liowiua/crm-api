package com.ag.crm.service.impl;

import com.ag.crm.dao.CustomerReprieveMapper;
import com.ag.crm.domain.CustomerReprieve;
import com.ag.crm.domain.ResultVO;
import com.ag.crm.service.CustomerReprieveService;
import com.ag.crm.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerReprieveServiceImpl implements CustomerReprieveService {

    @Autowired
    private CustomerReprieveMapper customerReprieveMapper;

    @Override
    public ResultVO selectByLossId(Integer lossId, Integer pageCount, Integer pageSize) {

        int start = (pageCount-1)*pageSize;
        List<CustomerReprieve> customerReprieves = customerReprieveMapper.selectByLossId(lossId,start,pageSize);

        int count = customerReprieveMapper.selectCount(lossId);
        int pageCounts = count%pageSize==0?count/pageSize:count/pageSize+1;
        return ResultVO.success("查询成功",new PageHelper<>(count,pageCounts,customerReprieves));

    }

    @Override
    public ResultVO insertCustomerReprieve(CustomerReprieve customerReprieve) {
        //参数校验
        if (customerReprieve.getLossId() == null||customerReprieve.getMeasure() == null){
            return ResultVO.fail("lossId或暂缓措施不完整");
        }

        /* 2. 设置参数的默认值 */
        customerReprieve.setIsValid(1);
        customerReprieve.setCreateDate(new Date());
        customerReprieve.setUpdateDate(new Date());
        int insert = customerReprieveMapper.insert(customerReprieve);
        if (insert > 0){
            return ResultVO.success("添加成功",null);
        }
        return ResultVO.fail("添加失败");
    }

    @Override
    public ResultVO updateCustomerReprieve(CustomerReprieve customerReprieve) {

        /* 1. 参数校验 */
        // 主键ID    id
        if (null == customerReprieve.getId()
                || customerReprieveMapper.selectByPrimaryKey(customerReprieve.getId()) == null){
            return ResultVO.fail("待更新记录不存在！");
        }
        // 参数校验
        if (customerReprieve.getLossId()==null || customerReprieve.getMeasure()==null){
            return ResultVO.fail("lossId或暂缓措施不能为空");
        }

        /* 2. 设置参数的默认值 */
        customerReprieve.setUpdateDate(new Date());

        /* 3. 执行修改操作，判断受影响的行数 */
        if (customerReprieveMapper.updateByPrimaryKeySelective(customerReprieve) < 1){
            return ResultVO.fail("修改暂缓数据失败！");
        }

        return ResultVO.success("修改成功",null);
    }

    @Override
    public ResultVO selectById(Integer id) {
        return ResultVO.success("查询成功",customerReprieveMapper.selectByPrimaryKey(id));
    }

    @Override
    public ResultVO deleteById(Integer id) {

        int i = customerReprieveMapper.deleteByPrimaryKey(id);
        if (i > 0){
            return ResultVO.success("删除成功",null);
        }

        return ResultVO.fail("删除失败");
    }


}
