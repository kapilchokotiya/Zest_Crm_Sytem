package com.ZEST_CRM._SYSTEM.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemDTO {
    private Long id;
    private String name;
    private double price;
    private int quantity;

    public OrderItemDTO(String itemA, double v, int i) {
    }
}