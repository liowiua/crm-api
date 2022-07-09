package com.ag.crm.service.impl;

import com.ag.crm.dao.*;
import com.ag.crm.domain.*;
import com.ag.crm.utils.PageHelper;
import com.ag.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private DatadicMapper datadicMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CustomerOrderMapper customerOrderMapper;

    @Autowired
    private CustomerLossMapper customerLossMapper;

    @Override
    public ResultVO insert(CustomerVO customer) {
        User user = userMapper.selectByUserName(customer.getCusManagerId());
        if(user == null){
            return ResultVO.fail("客户经理不存在");
        }
        customer.setCusManagerId(user.getId()+"");
        Customer customer1 = customerMapper.selectByCName(customer.getName());
        if (customer1 != null){
            return ResultVO.fail("用户名已存在");
        }
        if (customer.getLevel() != null){
            Datadic datadic = datadicMapper.selectBydataDicValue(customer.getLevel());
            customer.setLevel(datadic.getId()+"");
        }
        String khno = "KH_"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        customer.setKhno(khno);
        customer.setIsValid(1);
        customer.setState(0);
        int insert = customerMapper.insert(customer);
        if (insert > 0){
            return ResultVO.success("添加成功",null);
        }else{
            throw new RuntimeException("添加失败");
        }
    }

    @Override
    public ResultVO edit(CustomerVO customer) {
        User user = userMapper.selectByUserName(customer.getCusManagerId());
        if(user == null){
            return ResultVO.fail("客户经理不存在");
        }
        customer.setCusManagerId(user.getId()+"");
        if (customer.getLevel() != null){
            Datadic datadic = datadicMapper.selectBydataDicValue(customer.getLevel());
            customer.setLevel(datadic.getId()+"");
        }
        int i = customerMapper.updateByPrimaryKey(customer);
        if (i > 0){
            return ResultVO.success("修改成功",null);
        }
        throw new RuntimeException("修改失败");
    }

    @Override
    public ResultVO delete(Integer customerId) {
        int i = customerMapper.deleteByPrimaryKey(customerId);
        if (i > 0){
            return ResultVO.success("删除成功",null);
        }
        throw new RuntimeException("删除失败");
    }

    @Override
    public ResultVO selectByParams(CustomerVO customerVO, Integer pageCount, Integer pageSize) {
        int start = (pageCount-1)*pageSize;
        if (customerVO.getLevel() != null){
            Datadic datadic = datadicMapper.selectBydataDicValue(customerVO.getLevel());
            if (datadic != null)
                customerVO.setLevel(datadic.getId()+"");
        }

        List<Customer> customers = customerMapper.selectByParams(customerVO,start,pageSize);

        List<CustomerVO> customerVOS = new ArrayList<>();
        customers.forEach(
                customer -> {
                    CustomerVO customerVO1 = copy(customer);

                    Datadic datadic1 = datadicMapper.selectByPrimaryKey(customer.getLevel());
                    if (datadic1 != null)
                        customerVO1.setLevel(datadic1.getDataDicValue());
                    customerVOS.add(customerVO1);
                }
        );

        int count = customerMapper.selectCountByParams(customerVO);
        int pageCounts = count%pageSize==0?count/pageSize:count/pageSize+1;
        return ResultVO.success("查询成功",new PageHelper<>(count,pageCounts,customerVOS));

    }

    private CustomerVO copy(Customer customer) {
        CustomerVO customerVO = new CustomerVO();
        customerVO.setAddress(customer.getAddress());
        customerVO.setArea(customer.getArea());
        customerVO.setCreateDate(customer.getCreateDate());
        String managerName = userMapper.selectUserNameById(customer.getCusManagerId());
        customerVO.setCusManagerId(managerName);
        customerVO.setDsdjh(customer.getDsdjh());
        customerVO.setFr(customer.getFr());
        customerVO.setGsdjh(customer.getGsdjh());
        customerVO.setId(customer.getId());
        customerVO.setIsValid(customer.getIsValid());
        customerVO.setKhno(customer.getKhno());
        customerVO.setKhyh(customer.getKhyh());
        customerVO.setKhzh(customer.getKhzh());
        customerVO.setMyd(customer.getMyd());
        customerVO.setName(customer.getName());
        customerVO.setNyye(customer.getNyye());
        customerVO.setPhone(customer.getPhone());
        customerVO.setState(customer.getState());
        customerVO.setUpdateDate(customer.getUpdateDate());
        customerVO.setWebSite(customer.getWebSite());
        customerVO.setXyd(customer.getXyd());
        customerVO.setYyzzzch(customer.getYyzzzch());
        customerVO.setZczj(customer.getZczj());
        return customerVO;
    }

    @Override
    public ResultVO selectById(Integer id) {
        Customer customer = customerMapper.selectByPrimaryKey(id);

        return ResultVO.success("查询成功",customer);
    }

    @Override
    public ResultVO selectCustomerContributionByParams(CustomerDTO customerDTO) {

        int start = (customerDTO.getPageCount()-1)*customerDTO.getPageSize();
        customerDTO.setPageCount(start);
        List<Map> contribution = customerMapper.selectCustomerContributionByParams(customerDTO);
        int count = customerMapper.selectCountByParams2(customerDTO);
        int pageCounts = count%customerDTO.getPageSize()==0?count/customerDTO.getPageSize():count/customerDTO.getPageSize()+1;
        return ResultVO.success("查询成功",new PageHelper<>(count,pageCounts,contribution));
    }

    @Override
    public ResultVO countCustomerMake() {
        Map<String, Object> result = new HashMap<>();
        List<Map<String,Object>> mapList = customerMapper.countCustomerMake();
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Integer> integers = new ArrayList<>();
        mapList.forEach(
                m->{
                    if(m.get("level") != null){
                        list.add(m.get("level").toString());
                    }

                    integers.add(Integer.parseInt(m.get("total")+""));
                }
        );
        result.put("data1",list);
        result.put("data2",integers);
        return ResultVO.success("查询成功",result);
    }

    @Override
    public ResultVO countCustomerMake02() {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> list = customerMapper.countCustomerMake();
        List<String> dataList01 = new ArrayList<>();
        List<Map<String,Object>> dataList02 = new ArrayList<Map<String,Object>>();
        list.forEach(
                m->{
                    Map<String, Object> temp = new HashMap<>();
                    if(m.get("level") != null){
                        dataList01.add(m.get("level").toString());
                        temp.put("name",m.get("level"));
                    }



                    temp.put("value",m.get("total"));
                    dataList02.add(temp);
                }
        );

        result.put("dataList01",dataList01);
        result.put("dataList02",dataList02);

        return ResultVO.success("查询成功",result);
    }


    /**
     * 更新客户的流失状态
     *  1. 查询待流失的客户数据
     *  2. 将流失客户数据批量添加到客户流失表中
     *  3. 批量更新客户的流失状态  0=正常客户  1=流失客户
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCustomerState() {
        /* 1. 查询待流失的客户数据 */
        List<Customer> lossCustomerList = customerMapper.queryLossCustomers();

        /* 2. 将流失客户数据批量添加到客户流失表中 */
        // 判断流失客户数据是否存在
        if (lossCustomerList != null && lossCustomerList.size() > 0) {
            // 定义集合 用来接收流失客户的ID
            List<Integer> lossCustomerIds = new ArrayList<>();
            // 定义流失客户的列表
            List<CustomerLoss> customerLossList = new ArrayList<>();
            // 遍历查询到的流失客户的数据
            lossCustomerList.forEach(customer -> {
                // 定义流失客户对象
                CustomerLoss customerLoss = new CustomerLoss();
                // 创建时间  系统当前时间
                customerLoss.setCreateDate(new Date());

                // 客户编号
                customerLoss.setCusNo(customer.getKhno());

                // 修改时间  系统当前时间
                customerLoss.setUpdateDate(new Date());
                // 客户流失状态   0=暂缓流失状态  1=确认流失状态
                customerLoss.setState(0);
                // 客户最后下单时间
                // 通过客户ID查询最后的订单记录（最后一条订单记录）
                CustomerOrder customerOrder = customerOrderMapper.queryLossCustomerOrderByCustomerId(customer.getId());
                // 判断客户订单是否存在，如果存在，则设置最后下单时间
                if (customerOrder != null) {
                    customerLoss.setLastOrderTime(customerOrder.getOrderDate());
                }
                // 将流失客户对象设置到对应的集合中
                customerLossList.add(customerLoss);

                // 将流失客户的ID设置到对应的集合中
                lossCustomerIds.add(customer.getId());
            });
            // 批量添加流失客户记录
            if(customerLossMapper.insertBatch(customerLossList) != customerLossList.size()){
                throw new RuntimeException("客户流失数据转移失败！");
            }

            /* 3. 批量更新客户的流失状态 */
            if (customerMapper.updateCustomerStateByIds(lossCustomerIds) != lossCustomerIds.size()){
                throw new RuntimeException("客户流失数据转移失败！");
            }
        }

    }
}
