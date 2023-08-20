package com.ZEST_CRM._SYSTEM.Mapper;

import com.ZEST_CRM._SYSTEM.entity.Customer;
import com.ZEST_CRM._SYSTEM.entity.OrderItem;
import com.ZEST_CRM._SYSTEM.payload.CustomerDto;
import com.ZEST_CRM._SYSTEM.payload.CustomerResponseDTO;
import com.ZEST_CRM._SYSTEM.payload.OrderItemDTO;
import com.ZEST_CRM._SYSTEM.payload.OrderResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerMapper  {


    @Autowired
    OrderMapper orderMapper;

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public CustomerResponseDTO convertEntityToResponseDTO(Customer customer) {
        CustomerResponseDTO customerResponseDTO = modelMapper.map(customer, CustomerResponseDTO.class);
        customerResponseDTO.setId(customerResponseDTO.getId());

        if (customer.getOrderItems() != null) {
            List<OrderResponseDTO> orderResponseDTOS = new ArrayList<>();
            for (OrderItem orderItem : customer.getOrderItems()) {
                OrderResponseDTO responseDTO = modelMapper.map(orderItem, OrderResponseDTO.class);
                orderResponseDTOS.add(responseDTO);
            }
            customerResponseDTO.setOrderItemList(orderResponseDTOS);
        }
        return customerResponseDTO;
      }
    public Customer convertCreateRequestToEntity(CustomerDto customerDto) {
        Customer customer= modelMapper.map(customerDto, Customer.class);
        if (customerDto.getOrderItemList() != null) {
            customer.setOrderItems(new ArrayList<>());
            for (OrderItemDTO orderItemDTO : customerDto.getOrderItemList()) {
                OrderItem orderItem = modelMapper.map(orderItemDTO, OrderItem.class);
                orderItem.setCustomer(customer);
                customer.getOrderItems().add(orderItem);
            }
        }
        return customer;
    }

    public void convertUpdateRequestToEntity(CustomerDto customerDto, Customer customer) {

        modelMapper.map(customerDto, customer);

        if (customerDto != null && customerDto.getOrderItemList()!= null) {
            for (OrderItemDTO orderItemDTO: customerDto.getOrderItemList()) {
                if (orderItemDTO.getId() != 0) {
                    for (OrderItem orderItem : customer.getOrderItems()) {
                        if (orderItemDTO.getId().equals(orderItem.getId())) {
                            orderMapper.convertUpdateRequestToEntity(orderItemDTO, orderItem);
                        }
                    }
                } else {
                    OrderItem orderItem = orderMapper.convertCreateRequestToEntity(orderItemDTO);
                    orderItem.setId(null);
                    orderItem.setCustomer(customer);
                    customer.getOrderItems().add(orderItem);
                }
            }
        }
    }

}
