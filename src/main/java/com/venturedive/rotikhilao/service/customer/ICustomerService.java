package com.venturedive.rotikhilao.service.customer;

import com.venturedive.rotikhilao.model.entitiy.Order;
import com.venturedive.rotikhilao.pojo.BooleanResponse;
import com.venturedive.rotikhilao.pojo.ResponseList;
import com.venturedive.rotikhilao.pojo.UserProfile;
import com.venturedive.rotikhilao.request.OrderWrapper;

public interface ICustomerService {
    
    ResponseList<Order> viewCurrentOrders(Long customerId) throws Exception;

    ResponseList<Order> viewAllOrders(Long customerId) throws Exception;

    BooleanResponse orderFood(OrderWrapper request) throws Exception;

    BooleanResponse cancelOrder(Long customerId, Long orderId) throws Exception;

    BooleanResponse updateOrder(Long customerId, Long orderId, OrderWrapper request) throws Exception;

    UserProfile viewProfile(Long userId) throws Exception;
}
