package com.venturedive.rotikhilao.service.officeboy;

import com.venturedive.rotikhilao.DTO.*;

import java.util.List;

public interface IOfficeBoyService {
    List<OrderDto> ordersToBeReceived(Long officeBoyId);
    void creditReceivedFromCustomer(UpdateCustomerBalanceDto updateCustomerBalanceDto);
    void markOrderAsReceived(Long orderId);
    UserTokenResponseDto authenticateOfficeBoy(LoginDto loginDto);
    OfficeBoyDTO addOfficeBoy(CreateOfficeBoyDto createOfficeBoyDto);

}
