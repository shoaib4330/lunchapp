package com.venturedive.rotikhilao.service.customer;

import com.venturedive.rotikhilao.DTO.*;
import com.venturedive.rotikhilao.common.CommonUtils;
import com.venturedive.rotikhilao.entitiy.*;
import com.venturedive.rotikhilao.exception.ApplicationException;
import com.venturedive.rotikhilao.mapper.CustomerMapper;
import com.venturedive.rotikhilao.pojo.BooleanResponse;
import com.venturedive.rotikhilao.repository.CompanyRepository;
import com.venturedive.rotikhilao.repository.CustomerRepository;
import com.venturedive.rotikhilao.repository.OrderRepository;
import com.venturedive.rotikhilao.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public BooleanResponse placeOrder(PlaceOrderDto placeOrderDto) {
        CommonUtils.checkRequiredField(placeOrderDto.getCustomerId());
        CommonUtils.checkRequiredField(placeOrderDto.getOfficeBoyId());
        CommonUtils.checkRequiredField(placeOrderDto.getVendorId());

        if(CommonUtils.empty(placeOrderDto.getFoodItems())){
            throw new ApplicationException("Order empty, no food-items added to order");
        }

        Integer orderTotalBill=0;
        for (FoodItemDTO foodItem: placeOrderDto.getFoodItems()) {
            orderTotalBill = orderTotalBill + foodItem.getUnitPrice();
        }

        Order order = Order.builder()
                .customerId(placeOrderDto.getCustomerId())
                .officeBoyId(placeOrderDto.getOfficeBoyId())
                .vendorId(placeOrderDto.getVendorId())
                .bill(orderTotalBill)
                .build();
        orderRepository.save(order);

        return BooleanResponse.success("Order successfully placed");
    }

    @Override
    public CustomerDto getCustomerById(long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new ApplicationException("Customer not found with given id"));
        return customerMapper.mapToDto(customer);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.mapToDtoList(customers);
    }

    @Override
    public List<CustomerDto> getAllCustomersByCompany(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ApplicationException("Company not found with given id"));
        List<Customer> customers = customerRepository.findByCompany(company);
        return customerMapper.mapToDtoList(customers);
    }

    @Transactional
    public void creditTransaction(UpdateCustomerBalanceDto customerCreditDto) {
        CommonUtils.checkRequiredField(customerCreditDto.getCustomerId());
        CommonUtils.checkRequiredField(customerCreditDto.getAmount());

        Customer customer = customerRepository.findById(customerCreditDto.getCustomerId())
                .orElseThrow(() -> new ApplicationException("Customer with id [" + customerCreditDto.getCustomerId() + "] not found"));

        Integer credit = customer.getCredit();
        credit = credit + customerCreditDto.getAmount();

        customer.setCredit(credit);

        customerRepository.save(customer);

        Transaction transaction = Transaction.builder()
                .amount(customerCreditDto.getAmount())
                .customerId(customerCreditDto.getCustomerId())
                .officeGuyId(customerCreditDto.getOfficeBoyId())
                .build();

        transactionRepository.save(transaction);
    }

    @Transactional
    public void debitTransaction(UpdateCustomerBalanceDto customerCreditDto) {
        CommonUtils.checkRequiredField(customerCreditDto.getCustomerId());
        CommonUtils.checkRequiredField(customerCreditDto.getAmount());

        Customer customer = customerRepository.findById(customerCreditDto.getCustomerId())
                .orElseThrow(() -> new ApplicationException("Customer with id [" + customerCreditDto.getCustomerId() + "] not found"));

        Integer credit = customer.getCredit();
        credit = credit - customerCreditDto.getAmount();

        customer.setCredit(credit);

        customerRepository.save(customer);

        Transaction transaction = Transaction.builder()
                .amount(customerCreditDto.getAmount()*-1)
                .customerId(customerCreditDto.getCustomerId())
                .officeGuyId(customerCreditDto.getOfficeBoyId())
                .build();

        transactionRepository.save(transaction);
    }
}
