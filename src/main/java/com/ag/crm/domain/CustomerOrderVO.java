package com.ag.crm.domain;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class CustomerOrderVO {

    private CustomerOrder customerOrder;
    private List<OrderDetails> orderDetailsList;

}
