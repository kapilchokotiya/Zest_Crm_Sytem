package com.ZEST_CRM._SYSTEM.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    List<OrderResponseDTO> orderItemList;
}
