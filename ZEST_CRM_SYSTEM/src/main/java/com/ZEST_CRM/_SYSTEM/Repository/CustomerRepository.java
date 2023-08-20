package com.ZEST_CRM._SYSTEM.Repository;


import com.ZEST_CRM._SYSTEM.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
@Component
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
