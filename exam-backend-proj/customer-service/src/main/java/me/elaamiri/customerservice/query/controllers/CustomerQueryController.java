package me.elaamiri.customerservice.query.controllers;


import lombok.AllArgsConstructor;
import me.elaamiri.customerservice.query.entities.Customer;
import me.elaamiri.queries.customerQueries.GetAllCustomersQuery;
import me.elaamiri.queries.customerQueries.GetCustomerByIdQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/query/customers")
public class CustomerQueryController {

    private QueryGateway queryGateway;

    @GetMapping("")
    public List<Customer> getAllCustomers(){
        List<Customer> customers = queryGateway
                .query(new GetAllCustomersQuery(), ResponseTypes.multipleInstancesOf(Customer.class)).join();
        return customers;
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable String id){
        Customer customer = queryGateway
                .query(new GetCustomerByIdQuery(id), ResponseTypes.instanceOf(Customer.class)).join();
        return customer;
    }
}
