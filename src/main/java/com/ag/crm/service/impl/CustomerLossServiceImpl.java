package com.ag.crm.service.impl;

import com.ag.crm.dao.CustomerLossMapper;
import com.ag.crm.dao.CustomerMapper;
import com.ag.crm.dao.UserMapper;
import com.ag.crm.domain.CustomerLoss;
import com.ag.crm.domain.CustomerLossDTO;
import com.ag.crm.domain.CustomerLossVO;
import com.ag.crm.domain.ResultVO;
import com.ag.crm.service.CustomerLossService;
import com.ag.crm.utils.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomerLossServiceImpl implements CustomerLossService {

    @Autowired
    private CustomerLossMapper customerLossMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultVO customerLossAnalysis(Integer pageCount,Integer pageSize) {

        int start = (pageCount-1)*pageSize;
        List<CustomerLoss> lossList = customerLossMapper.customerLossAnalysis(start,pageSize);
        List<CustomerLossVO> lossVOList = new ArrayList<>();
        lossList.forEach(
                customerLoss -> {
                    CustomerLossVO customerLossVO = copy(customerLoss);
                    String customerName = customerMapper.selectUsernameByCusNo(customerLoss.getCusNo());
                    int managerId = customerMapper.selectCusManagerByCusNo(customerLoss.getCusNo());
                    String managerName = userMapper.selectUserNameById(managerId);
                    customerLossVO.setCustomerName(customerName);
                    customerLossVO.setManagerName(managerName);
                    lossVOList.add(customerLossVO);
                }
        );
        int count = customerLossMapper.selectCount();
        int pageCounts = count%pageSize==0?count/pageSize:count/pageSize+1;
        return ResultVO.success("查询成功",new PageHelper<>(count,pageCounts,lossVOList));
    }

    @Override
    public ResultVO selectByParams(CustomerLossDTO customerLossDTO) {

        int start = (customerLossDTO.getPageCount()-1)*customerLossDTO.getPageSize();
        customerLossDTO.setPageCount(start);
        List<CustomerLoss> lossList = customerLossMapper.selectByParams(customerLossDTO);
        List<CustomerLossVO> lossVOList = new ArrayList<>();
        lossList.forEach(
                customerLoss -> {
                    CustomerLossVO customerLossVO = copy(customerLoss);
                    String customerName = customerMapper.selectUsernameByCusNo(customerLoss.getCusNo());
                    int managerId = customerMapper.selectCusManagerByCusNo(customerLoss.getCusNo());
                    String managerName = userMapper.selectUserNameById(managerId);
                    customerLossVO.setCustomerName(customerName);
                    customerLossVO.setManagerName(managerName);
                    lossVOList.add(customerLossVO);
                }
        );
        int count = customerLossMapper.selectCount1(customerLossDTO);
        int pageCounts = count%customerLossDTO.getPageSize()==0?count/customerLossDTO.getPageSize():count/customerLossDTO.getPageSize()+1;
        return ResultVO.success("查询成功",new PageHelper<>(count,pageCounts,lossVOList));

    }

    @Override
    public ResultVO selectById(Integer lossId) {
        CustomerLoss customerLoss = customerLossMapper.selectByPrimaryKey(lossId);
        String username = customerMapper.selectUsernameByCusNo(customerLoss.getCusNo());
        Integer managerId = customerMapper.selectCusManagerByCusNo(customerLoss.getCusNo());
        String managerName = userMapper.selectUserNameById(managerId);
        CustomerLossVO customerLossVO = copy(customerLoss);
        customerLossVO.setManagerName(managerName);
        customerLossVO.setCustomerName(username);


        return ResultVO.success("查询成功",customerLossVO);
    }

    /**
     * 更新流失客户的流失状态
     *  1. 参数校验
     *      判断id非空且对应的数据存在
     *      流失原因非空
     *  2. 设置参数的默认值
     *      设置流失状态  state=1  0=暂缓流失，1=确认流失
     *      流失原因
     *      客户流失时间  系统当前时间
     *      更新时间     系统当前时间
     *  3. 执行更新操作，判断受影响的行数
     *
     * @param id
     * @param lossReason
     * @return ResultVO
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public ResultVO updateCustomerLossStateById(Integer id, String lossReason) {
        /* 1. 参数校验 */
        // 判断id非空
        if(null == id ){
            return ResultVO.fail("待确认流失的客户不存在！");
        }
        // 通过id查询流失客户的记录
        CustomerLoss customerLoss = customerLossMapper.selectByPrimaryKey(id);
        // 判断流失客户记录是否存在
        if(null == customerLoss ){
            return ResultVO.fail("待确认流失的客户不存在！");
        }
        // 流失原因非空
        if(StringUtils.isBlank(lossReason) ){
            return ResultVO.fail("流失原因不能为空！");
        }

        /* 2. 设置参数的默认值 */
        // 设置流失状态  state=1  0=暂缓流失，1=确认流失
        customerLoss.setState(1);
        // 设置流失原因
        customerLoss.setLossReason(lossReason);
        // 客户流失时间  系统当前时间
        customerLoss.setConfirmLossTime(new Date());
        // 更新时间     系统当前时间
        customerLoss.setUpdateDate(new Date());

        /* 3. 执行更新操作，判断受影响的行数 */
        if(customerLossMapper.updateByPrimaryKeySelective(customerLoss) < 1){
            return ResultVO.fail("确认流失失败！");
        }
        return ResultVO.success("确认流失成功",null);
    }

    private CustomerLossVO copy(CustomerLoss customerLoss) {
        CustomerLossVO customerLossVO = new CustomerLossVO();
        customerLossVO.setId(customerLoss.getId());
        customerLossVO.setConfirmLossTime(customerLoss.getConfirmLossTime());
        customerLossVO.setCreateDate(customerLoss.getCreateDate());
        customerLossVO.setCusNo(customerLoss.getCusNo());
        customerLossVO.setLastOrderTime(customerLoss.getLastOrderTime());
        customerLossVO.setLossReason(customerLoss.getLossReason());
        customerLossVO.setState(customerLoss.getState());
        customerLossVO.setUpdateDate(customerLoss.getUpdateDate());
        return customerLossVO;
    }
}
