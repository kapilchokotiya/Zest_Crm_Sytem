package com.ZEST_CRM._SYSTEM.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderCalculationRequest {
    private List<OrderItemDTO> orderItems;
    private double discountPercentage;
}
