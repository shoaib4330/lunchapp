package com.venturedive.rotikhilao.service.order;

import com.venturedive.rotikhilao.DTO.CreateOrderDto;
import com.venturedive.rotikhilao.DTO.OrderDto;
import com.venturedive.rotikhilao.entitiy.*;
import com.venturedive.rotikhilao.enums.OrderStatus;
import com.venturedive.rotikhilao.exception.ApplicationException;
import com.venturedive.rotikhilao.mapper.FoodItemMapper;
import com.venturedive.rotikhilao.mapper.OrderMapper;
import com.venturedive.rotikhilao.repository.*;
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
    @Autowired
    private OfficeBoyRepository officeBoyRepository;

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private FoodItemMapper foodItemMapper;

    @Override
    public OrderDto orderFoodItems(CreateOrderDto createOrderDto) {
       validateCreateDto(createOrderDto);
       Customer customer = customerRepository.findById(createOrderDto.getCustomerId())
               .orElseThrow(()->new ApplicationException("Customer not Found With ID: " + createOrderDto.getCustomerId()));

        OfficeBoy officeBoy = officeBoyRepository.findById(createOrderDto.getOfficeBoyId())
                .orElseThrow(()-> new ApplicationException("OfficeBoy not found with ID: "+createOrderDto.getOfficeBoyId()));

       List<FoodItem> foodItems = new ArrayList <>();
       createOrderDto.getFoodItems()
               .forEach(f->{
                   foodItems.add(foodItemRepository.findById(f)
                    .orElseThrow(()-> new ApplicationException("FoodItem with ID: " + f + " Not found")));}
       );

        final Order order = Order.builder().customer(customer).orderStatus(OrderStatus.PENDING.value()).officeBoy(officeBoy).build();
        List<OrderItem> orderItems = new ArrayList<>();
        Integer bill = 0;
        foodItems.forEach(f->
        {
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

    private void validateCreateDto(CreateOrderDto createOrderDto){
        if(createOrderDto.getCustomerId()==null || createOrderDto.getCustomerId() < 1) {
            throw new ApplicationException("Please provide valid customer foodItemId");
        }
        if(createOrderDto.getFoodItems().isEmpty()){
            throw new ApplicationException("Please provide food items list");
        }
        if(createOrderDto.getOfficeBoyId() == null || createOrderDto.getOfficeBoyId() < 1){
            throw new ApplicationException("Please provide valid officeBoy Id");
        }
    }
}
