package com.test.springcloud.service;

import com.test.springcloud.dao.OrderDao;
import com.test.springcloud.entities.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

public interface OrderService {

    // 新建订单
    public void create(Order order);

}
