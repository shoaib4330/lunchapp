package com.venturedive.rotikhilao.service.officeboy;

import com.venturedive.rotikhilao.DTO.*;
import com.venturedive.rotikhilao.common.CommonUtils;
import com.venturedive.rotikhilao.configuration.JwtTokenProvider;
import com.venturedive.rotikhilao.entitiy.Company;
import com.venturedive.rotikhilao.entitiy.OfficeBoy;
import com.venturedive.rotikhilao.entitiy.Order;
import com.venturedive.rotikhilao.enums.OrderStatus;
import com.venturedive.rotikhilao.exception.ApplicationException;
import com.venturedive.rotikhilao.mapper.OfficeBoyMapper;
import com.venturedive.rotikhilao.mapper.OrderMapper;
import com.venturedive.rotikhilao.repository.CompanyRepository;
import com.venturedive.rotikhilao.repository.OfficeBoyRepository;
import com.venturedive.rotikhilao.repository.OrderRepository;
import com.venturedive.rotikhilao.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OfficeBoyService implements IOfficeBoyService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OfficeBoyRepository officeBoyRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private OfficeBoyMapper officeBoyMapper;

    public List<OrderDto> ordersToBeReceived(Long officeBoyId) {
        CommonUtils.checkRequiredField(officeBoyId);

        OfficeBoy officeBoy = officeBoyRepository.findById(officeBoyId)
                .orElseThrow(() -> new ApplicationException("OfficeBoy with foodItemId [" + officeBoyId + "] not found"));
        List<Order> orders = orderRepository.findAllByOfficeBoyAndOrderStatus(officeBoy, OrderStatus.ACCEPTED.value());
        return orderMapper.mapToDto(orders);
    }

    public void creditReceivedFromCustomer(UpdateCustomerBalanceDto updateCustomerBalanceDto) {
        customerService.creditTransaction(updateCustomerBalanceDto);
    }

    @Transactional
    public void markOrderAsReceived(Long orderId) {
        CommonUtils.checkRequiredField(orderId);

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ApplicationException("OfficeBoy with foodItemId [" + orderId + "] not found"));

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

    @Override
    public UserTokenResponseDto authenticateOfficeBoy(LoginDto loginDto) {
        OfficeBoy officeBoy = officeBoyRepository.findByOfficeBoyName(loginDto.getUsername())
                .orElseThrow(()-> new ApplicationException("OfficeBoy does not exist with name: "+ loginDto.getUsername()));
        if(bCryptPasswordEncoder.matches(loginDto.getPassword(),officeBoy.getOfficeBoyPassword()))
        {
            return UserTokenResponseDto.builder().userId(officeBoy.getId())
                    .jwtToken(tokenProvider.generateToken(officeBoy.getId()))
                    .isAuthorized(true)
                    .name(officeBoy.getOfficeBoyName())
                    .build();
        }
        else
        {
            throw new ApplicationException("Invalid Password");
        }

    }

    @Override
    public OfficeBoyDTO addOfficeBoy(CreateOfficeBoyDto createOfficeBoyDto) {

        CommonUtils.checkRequiredField(createOfficeBoyDto.getCompanyId());
        CommonUtils.checkRequiredField(createOfficeBoyDto.getName());
        CommonUtils.checkRequiredField(createOfficeBoyDto.getPassword());

        Company company = companyRepository.findById(createOfficeBoyDto.getCompanyId())
                .orElseThrow(()->new ApplicationException("Company not found with ID: "+ createOfficeBoyDto.getCompanyId() ));

        if(officeBoyRepository.findByOfficeBoyName(createOfficeBoyDto.getName()).isPresent()) {
            throw new ApplicationException("OfficeBoy already exist with name : " + createOfficeBoyDto.getName());
        }

        OfficeBoy officeBoy = OfficeBoy.builder()
                .officeBoyName(createOfficeBoyDto.getName())
                .officeBoyPassword(bCryptPasswordEncoder.encode(createOfficeBoyDto.getPassword()))
                .companyId(company.getCompanyId())
                .build();
        officeBoy = officeBoyRepository.save(officeBoy);

        return officeBoyMapper.mapToDto(officeBoy);

    }
}
