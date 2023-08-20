package com.ZEST_CRM._SYSTEM.Mapper;

import com.ZEST_CRM._SYSTEM.entity.OrderItem;
import com.ZEST_CRM._SYSTEM.payload.OrderItemDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public OrderMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public OrderItem convertCreateRequestToEntity(OrderItemDTO orderItemDTO) {
        OrderItem orderItem= modelMapper.map(orderItemDTO, OrderItem.class);
        return orderItem;
    }


    public void convertUpdateRequestToEntity(OrderItemDTO orderItemDTO, OrderItem orderItem) {
        modelMapper.map(orderItemDTO, orderItem);
    }
}
