package com.ag.crm.service.impl;

import com.ag.crm.dao.CustomerOrderMapper;
import com.ag.crm.dao.OrderDetailsMapper;
import com.ag.crm.domain.*;
import com.ag.crm.service.CustomerOrderService;
import com.ag.crm.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private CustomerOrderMapper customerOrderMapper;

    @Autowired
    private OrderDetailsMapper orderDetailsMapper;

    @Override
    public ResultVO selectCOrderById(Integer cId,Integer pageCount,Integer pageSize) {
        int start = (pageCount-1)*pageSize;
        List<CustomerOrderVO> customerOrderVOS = new ArrayList<>();
        List<CustomerOrder> customers = customerOrderMapper.selectCOrderById(cId,start,pageSize);
        customers.forEach(
                customer->{
                    List<OrderDetails> orderDetails = orderDetailsMapper.selectByOrderId(customer.getId());
                    CustomerOrderVO customerOrderVO = new CustomerOrderVO();
                    customerOrderVO.setCustomerOrder(customer);
                    customerOrderVO.setOrderDetailsList(orderDetails);
                    customerOrderVOS.add(customerOrderVO);
                }
        );
        int count = customerOrderMapper.selectCountByParams(cId);
        int pageCounts = count%pageSize==0?count/pageSize:count/pageSize+1;
        return ResultVO.success("查询成功",new PageHelper<>(count,pageCounts,customerOrderVOS));

    }

    @Override
    @Transactional
    public ResultVO insertOrder(CustomerOrderDTO customerOrderDTO) {

        CustomerOrder customerOrder = copyToCOrder(customerOrderDTO);
        int insert = customerOrderMapper.insert(customerOrder);
        int id = customerOrderMapper.selectIdByONo(customerOrder.getOrderNo());
        OrderDetails orderDetails = copyToODetails(customerOrderDTO);
        orderDetails.setOrderId(id);
        int insert1 = orderDetailsMapper.insert(orderDetails);
        if (insert > 0&&insert1 > 0){
            return ResultVO.success("添加成功",null);
        }

        return ResultVO.fail("添加失败");
    }

    private OrderDetails copyToODetails(CustomerOrderDTO customerOrderDTO) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setCreateDate(new Date());
        orderDetails.setGoodsName(customerOrderDTO.getGoodsName());
        orderDetails.setGoodsNum(customerOrderDTO.getGoodsNum());
        orderDetails.setIsValid(1);
        orderDetails.setPrice(customerOrderDTO.getPrice());
        orderDetails.setSum(customerOrderDTO.getSum());
        orderDetails.setUnit(customerOrderDTO.getUnit());
        orderDetails.setUpdateDate(new Date());
        return orderDetails;
    }

    private CustomerOrder copyToCOrder(CustomerOrderDTO customerOrderDTO) {
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setAddress(customerOrderDTO.getAddress());
        customerOrder.setCreateDate(new Date());
        customerOrder.setCusId(customerOrderDTO.getCusId());
        customerOrder.setIsValid(1);
        customerOrder.setOrderDate(new Date());
        customerOrder.setOrderNo(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        customerOrder.setState(1);
        customerOrder.setUpdateDate(new Date());
        return customerOrder;
    }
}
