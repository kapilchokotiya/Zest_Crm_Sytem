package com.ZEST_CRM._SYSTEM;

import com.ZEST_CRM._SYSTEM.Mapper.CustomerMapper;
import com.ZEST_CRM._SYSTEM.controller.CustomerController;
import com.ZEST_CRM._SYSTEM.entity.Customer;
import com.ZEST_CRM._SYSTEM.payload.CustomerDto;
import com.ZEST_CRM._SYSTEM.payload.OrderCalculationRequest;
import com.ZEST_CRM._SYSTEM.payload.OrderItemDTO;
import com.ZEST_CRM._SYSTEM.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CustomerControllerUnitTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CustomerMapper customerMapper;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }
    //this test case pass
    @Test
    public void testCreateCustomer() throws Exception {
        CustomerDto customerDto = new CustomerDto(); // Set appropriate values for customerDto
        Customer customer = new Customer();
        when(customerService.createCustomer(any())).thenReturn(customer);

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customerDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(customerService).createCustomer(any());
    }


    @Test
    public void testGetAllCustomers() throws Exception {
        List<Customer> customers = new ArrayList<>();
        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(customerService).getAllCustomers();
    }

    @Test
    public void testGetCustomerById() throws Exception {
        Long customerId = 1L;
        Customer customer = new Customer();
        when(customerService.getCustomerById(customerId)).thenReturn(customer);

        mockMvc.perform(get("/api/customers/{id}", customerId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(customerService).getCustomerById(customerId);
    }



    @Test
    public void testCalculateTotalOrderAmount() throws Exception {
        Long customerId = 1L;
        List<OrderItemDTO> orderItems = new ArrayList<>();
        orderItems.add(new OrderItemDTO("Item A", 10.0, 2));
        orderItems.add(new OrderItemDTO("Item B", 20.0, 3));
        double discountPercentage = 10.0;

        OrderCalculationRequest calculationRequest = new OrderCalculationRequest();
        calculationRequest.setOrderItems(orderItems);
        calculationRequest.setDiscountPercentage(discountPercentage);


        when(customerService.getCustomerById(customerId)).thenReturn(new Customer());

        mockMvc.perform(post("/api/customers/{customerId}/calculate-total", customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(calculationRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));


    }


    private String asJsonString(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
