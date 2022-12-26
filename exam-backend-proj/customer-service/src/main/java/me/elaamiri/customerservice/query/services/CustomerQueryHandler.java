package me.elaamiri.customerservice.query.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.elaamiri.customerservice.query.entities.Customer;
import me.elaamiri.customerservice.query.repositories.CustomerRepository;
import me.elaamiri.exceptions.EntryNotFoundException;
import me.elaamiri.queries.customerQueries.GetAllCustomersQuery;
import me.elaamiri.queries.customerQueries.GetCustomerByIdQuery;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerQueryHandler {
    private CustomerRepository customerRepository;

    @QueryHandler
    public List<Customer> handle(GetAllCustomersQuery query){
        return customerRepository.findAll();
    }

    @QueryHandler
    public Customer handle(GetCustomerByIdQuery query){
        return customerRepository.findById(query.getId())
                .orElseThrow(()-> new EntryNotFoundException("Customer Not Found"));
    }
}
