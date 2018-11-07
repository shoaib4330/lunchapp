package com.venturedive.rotikhilao.service;

import com.venturedive.rotikhilao.common.CommonUtils;
import com.venturedive.rotikhilao.dto.OrderDto;
import com.venturedive.rotikhilao.dto.UpdateCustomerBalanceDto;
import com.venturedive.rotikhilao.entitiy.OfficeBoy;
import com.venturedive.rotikhilao.entitiy.Order;
import com.venturedive.rotikhilao.enums.OrderStatus;
import com.venturedive.rotikhilao.exception.ApplicationException;
import com.venturedive.rotikhilao.mapper.OrderMapper;
import com.venturedive.rotikhilao.repository.OfficeBoyRepository;
import com.venturedive.rotikhilao.repository.OrderRepository;
import com.venturedive.rotikhilao.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OfficeGuyService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OfficeBoyRepository officeBoyRepository;

    @Autowired
    private CustomerService customerService;

    public List<OrderDto> ordersToBeReceived(Long officeGuyId) {
        CommonUtils.checkRequiredField(officeGuyId);

        OfficeBoy officeBoy = officeBoyRepository.findById(officeGuyId)
                .orElseThrow(() -> new ApplicationException("OfficeGuy with id [" + officeGuyId + "] not found"));
        List<Order> orders = orderRepository.findAllByOfficeBoyAndOrderStatus(officeBoy, OrderStatus.ACCEPTED.value());
        return orderMapper.mapToDto(orders);
    }

    public void creditReceivedFromCustomer(UpdateCustomerBalanceDto updateCustomerBalanceDto) {
        customerService.creditTransaction(updateCustomerBalanceDto);
    }

    @Transactional
    public void markOrderAsReceived(Long orderId) {
        CommonUtils.checkRequiredField(orderId);

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ApplicationException("OfficeGuy with id [" + orderId + "] not found"));

        if (order.getOrderStatus() == OrderStatus.ACCEPTED.value()) {
            order.setOrderStatus(OrderStatus.DELIVERED.value());
            orderRepository.save(order);

            customerService.debitTransaction(UpdateCustomerBalanceDto.builder()
                    .customerId(order.getCustomer().getCustomerId())
                    .officeBoyId(order.getOfficeBoy().getId())
                    .amount(order.getBill())
                    .build());
        } else {
            throw new ApplicationException("Order is not 'accepted', therefore cannot be marked received");
        }
    }
}
