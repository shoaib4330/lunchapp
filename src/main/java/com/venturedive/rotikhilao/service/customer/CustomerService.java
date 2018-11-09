package com.venturedive.rotikhilao.service.customer;

import com.venturedive.rotikhilao.DTO.*;
import com.venturedive.rotikhilao.common.CommonUtils;
import com.venturedive.rotikhilao.entitiy.*;
import com.venturedive.rotikhilao.enums.OrderStatus;
import com.venturedive.rotikhilao.exception.ApplicationException;
import com.venturedive.rotikhilao.mapper.CustomerMapper;
import com.venturedive.rotikhilao.mapper.FoodItemMapper;
import com.venturedive.rotikhilao.mapper.OrderMapper;
import com.venturedive.rotikhilao.pojo.BooleanResponse;
import com.venturedive.rotikhilao.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private FoodItemRepository foodItemRepository;

    @Autowired
    private OfficeBoyRepository officeBoyRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private FoodItemMapper foodItemMapper;

    @Override
    @Transactional
    public OrderDto placeOrder(CreateOrderDto createOrderDto) {
        CommonUtils.checkRequiredField(createOrderDto.getCustomerId());
        CommonUtils.checkRequiredField(createOrderDto.getOfficeBoyId());
        CommonUtils.checkRequiredField(createOrderDto.getVendorId());
        CommonUtils.checkRequiredField(createOrderDto.getFoodItems().size());

        Customer customer = customerRepository.findById(createOrderDto.getCustomerId())
                .orElseThrow(()->new ApplicationException("Customer not Found With ID: " + createOrderDto.getCustomerId()));

        Vendor vendor = vendorRepository.findById(createOrderDto.getVendorId())
                .orElseThrow(()->new ApplicationException("Vendor not Found With ID: " + createOrderDto.getVendorId()));

        OfficeBoy officeBoy = officeBoyRepository.findById(createOrderDto.getOfficeBoyId())
                .orElseThrow(()-> new ApplicationException("OfficeBoy not found with ID: "+createOrderDto.getOfficeBoyId()));

        List<FoodItem> foodItems = new ArrayList<>();
        createOrderDto.getFoodItems()
                .forEach(f->{
                    foodItems.add(foodItemRepository.findByFoodItemIdAndQuantityGreaterThanAndVendor(f,0,vendor)
                            .orElseThrow(()-> new ApplicationException("FoodItem with ID: " + f + " Not Available")));}
                );

        final Order order = Order.builder().customer(customer).orderStatus(OrderStatus.PENDING.value()).vendorId(vendor.getId()).officeBoy(officeBoy).build();
        List<OrderItem> orderItems = new ArrayList<>();
        Integer bill = 0;
        foodItems.forEach(f->
        {
            f.setQuantity(f.getQuantity()-1);
            foodItemRepository.save(f);
            orderItems.add(OrderItem.builder().footItem(f).order(order).build());
        });
        for(FoodItem f: foodItems){
            bill = bill + f.getUnitPrice();
        }
        order.setBill(bill);
        order.setOrderItems(orderItems);
        Order mOrder = orderRepository.save(order);


        OrderDto orderDto = orderMapper.mapToDto(mOrder);
        orderDto.setFoodItems(foodItemMapper.mapToDtoList(foodItems));

        return orderDto;
    }

    @Override
    public CustomerDto getCustomerById(long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new ApplicationException("Customer not found with given foodItemId"));
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
                .orElseThrow(() -> new ApplicationException("Company not found with given foodItemId"));
        List<Customer> customers = customerRepository.findByCompany(company);
        return customerMapper.mapToDtoList(customers);
    }

    @Transactional
    public void creditTransaction(UpdateCustomerBalanceDto customerCreditDto) {
        CommonUtils.checkRequiredField(customerCreditDto.getCustomerId());
        CommonUtils.checkRequiredField(customerCreditDto.getAmount());

        Customer customer = customerRepository.findById(customerCreditDto.getCustomerId())
                .orElseThrow(() -> new ApplicationException("Customer with foodItemId [" + customerCreditDto.getCustomerId() + "] not found"));

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
                .orElseThrow(() -> new ApplicationException("Customer with foodItemId [" + customerCreditDto.getCustomerId() + "] not found"));

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
