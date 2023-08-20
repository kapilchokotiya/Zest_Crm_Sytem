package com.ZEST_CRM._SYSTEM.controller;


import com.ZEST_CRM._SYSTEM.Mapper.CustomerMapper;
import com.ZEST_CRM._SYSTEM.entity.Customer;
import com.ZEST_CRM._SYSTEM.payload.CustomerDto;
import com.ZEST_CRM._SYSTEM.payload.CustomerResponseDTO;
import com.ZEST_CRM._SYSTEM.payload.OrderCalculationRequest;
import com.ZEST_CRM._SYSTEM.payload.OrderItemDTO;
import com.ZEST_CRM._SYSTEM.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    CustomerMapper customerMapper;

    //http://localhost:8081/api/customers
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerResponseDTO create(@RequestBody CustomerDto customerDto) {
        Customer customer = customerMapper.convertCreateRequestToEntity(customerDto);
        customer = customerService.createCustomer(customer);
        CustomerResponseDTO result = customerMapper.convertEntityToResponseDTO(customer);
        return result;
    }
    //http://localhost:8081/api/customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
    //http://localhost:8081/api/customers/{id}
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }
    //http://localhost:8081/api/customers/{id}
    @PutMapping("/{id}")
    public CustomerResponseDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        Customer customer = customerService.getCustomerById(id);
        customerMapper.convertUpdateRequestToEntity(customerDto, customer);
        customer = customerService.updateCustomer(customer);
        CustomerResponseDTO result = customerMapper.convertEntityToResponseDTO(customer);
        return result;
    }
    //http://localhost:8081/api/customers/{id}
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }


    private double calculateTotalAmount(List<OrderItemDTO> orderItems) {
        double totalAmount = 0;
        for (OrderItemDTO orderItem : orderItems) {
            totalAmount += orderItem.getPrice() * orderItem.getQuantity();
        }
        return totalAmount;
    }

    private double applyDiscount(double amount, double discountPercentage) {
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("Discount percentage should be between 0 and 100");
        }
        double discountAmount = (discountPercentage / 100) * amount;
        return amount - discountAmount;
    }

    // http://localhost:8081/api/customers/{customerId}/calculate-total
    @PostMapping("/{customerId}/calculate-total")
    public double calculateTotalOrderAmount(
            @PathVariable Long customerId,
            @RequestBody OrderCalculationRequest calculationRequest) {
        Customer customer = customerService.getCustomerById(customerId);

        double totalAmount = calculateTotalAmount(calculationRequest.getOrderItems());
        double discountedAmount = applyDiscount(totalAmount, calculationRequest.getDiscountPercentage());

        return discountedAmount;
    }



}
