package com.venturedive.rotikhilao.service.order;

import com.venturedive.rotikhilao.DTO.CreateOrderDto;
import com.venturedive.rotikhilao.DTO.OrderDto;
import com.venturedive.rotikhilao.entitiy.Customer;
import com.venturedive.rotikhilao.entitiy.FoodItem;
import com.venturedive.rotikhilao.exception.ApplicationException;
import com.venturedive.rotikhilao.repository.CustomerRepository;
import com.venturedive.rotikhilao.repository.FoodItemRepository;
import com.venturedive.rotikhilao.repository.OrderRepository;
import com.venturedive.rotikhilao.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOrderService {
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private FoodItemRepository foodItemRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;


    @Override
    public OrderDto orderFoodItems(CreateOrderDto createOrderDto) {
       validateCreateDto(createOrderDto);
       customerRepository.findById(createOrderDto.getCustomerId())
               .orElseThrow(()->new ApplicationException("Customer not Found With ID: " + createOrderDto.getCustomerId()));

       List<FoodItem> foodItems = new ArrayList <>();
       createOrderDto.getFoodItems()
               .forEach(f->{
                   foodItems.add(foodItemRepository.findById(f)
                    .orElseThrow(()-> new ApplicationException("FoodItem with ID: " + f + " Not found")));}
       );



    }

    private void validateCreateDto(CreateOrderDto createOrderDto){
        if(createOrderDto.getCustomerId()==null || createOrderDto.getCustomerId() < 1) {
            throw new ApplicationException("Please provide valid customer id");
        }
        if(createOrderDto.getFoodItems().isEmpty()){
            throw new ApplicationException("Please provide food items list");
        }
    }
}
