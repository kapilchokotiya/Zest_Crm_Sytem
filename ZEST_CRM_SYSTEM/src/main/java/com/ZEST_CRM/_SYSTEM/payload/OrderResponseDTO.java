package com.ZEST_CRM._SYSTEM.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderResponseDTO {
    private Long id;
    private String name;
    private double price;
    private int quantity;
}
