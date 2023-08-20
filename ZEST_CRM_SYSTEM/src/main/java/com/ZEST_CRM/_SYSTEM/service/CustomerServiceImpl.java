package com.ZEST_CRM._SYSTEM.service;


import com.ZEST_CRM._SYSTEM.Repository.CustomerRepository;
import com.ZEST_CRM._SYSTEM.config.NotFoundException;
import com.ZEST_CRM._SYSTEM.entity.Customer;
import com.ZEST_CRM._SYSTEM.entity.OrderItem;
import com.ZEST_CRM._SYSTEM.payload.OrderItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (customerOptional.isPresent()) {
            return customerOptional.get();
        } else {
            throw new NotFoundException("Customer with ID " + id + " does not exist");
        }
    }


    @Override
    public Customer updateCustomer(Customer updatedCustomer) {
        return customerRepository.save(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public double calculateTotalWithDiscount(List<OrderItemDTO> items, double discountPercentage) {
        double totalAmount = items.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
        double discountAmount = totalAmount * (discountPercentage / 100);
        return totalAmount - discountAmount;
    }
}
