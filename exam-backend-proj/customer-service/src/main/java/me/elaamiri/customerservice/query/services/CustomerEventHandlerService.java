package me.elaamiri.customerservice.query.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.elaamiri.customerservice.query.entities.Customer;
import me.elaamiri.customerservice.query.repositories.CustomerRepository;
import me.elaamiri.events.customerEvents.CustomerCreatedEvent;
import me.elaamiri.events.customerEvents.CustomerDeletedEvent;
import me.elaamiri.events.customerEvents.CustomerUpdatedEvent;
import me.elaamiri.exceptions.EntryNotFoundException;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerEventHandlerService {
    private CustomerRepository customerRepository;

    @EventHandler
    public void on(CustomerCreatedEvent event){
        Customer customer = Customer.builder()
                .id(event.getId())
                .adresse(event.getAdresse())
                .email(event.getEmail())
                .telephone(event.getTelephone())
                .nom(event.getNom())
                .build();
        customerRepository.save(customer);
    }

    @EventHandler
    public void on(CustomerUpdatedEvent event){
        Customer customer = customerRepository.findById(event.getId())
                .orElseThrow(()-> new EntryNotFoundException("Customer Not Found"));
        customer.setNom(event.getNom());
        customer.setAdresse(event.getAdresse());
        customer.setEmail(event.getEmail());
        customer.setTelephone(event.getTelephone());
//        customer.setId(event.getId());
        customerRepository.save(customer);

    }

    @EventHandler
    public void on(CustomerDeletedEvent event){
        Customer customer = customerRepository.findById(event.getId())
                .orElseThrow(()-> new EntryNotFoundException("Customer Not Found"));
        customerRepository.delete(customer);
    }

}
