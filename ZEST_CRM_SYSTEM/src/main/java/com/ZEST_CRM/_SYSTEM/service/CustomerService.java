package com.ZEST_CRM._SYSTEM.service;



import com.ZEST_CRM._SYSTEM.entity.Customer;
import com.ZEST_CRM._SYSTEM.entity.OrderItem;
import com.ZEST_CRM._SYSTEM.payload.OrderItemDTO;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    Customer updateCustomer(Customer updatedCustomer);
    void deleteCustomer(Long id);

    public double calculateTotalWithDiscount(List<OrderItemDTO> items, double discountPercentage);


}
