package me.elaamiri.customerservice.query.repositories;

import me.elaamiri.customerservice.query.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
