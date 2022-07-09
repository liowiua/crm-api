package com.ag.crm.dao;

import com.ag.crm.domain.OrderDetails;
import java.util.List;

public interface OrderDetailsMapper {

    int deleteByPrimaryKey(Integer id);


    int insert(OrderDetails record);


    OrderDetails selectByPrimaryKey(Integer id);


    List<OrderDetails> selectAll();


    int updateByPrimaryKey(OrderDetails record);

    List<OrderDetails> selectByOrderId(Integer orderId);
}
