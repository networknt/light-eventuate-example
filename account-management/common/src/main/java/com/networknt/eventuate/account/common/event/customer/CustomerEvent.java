package com.networknt.eventuate.account.common.event.customer;


import com.networknt.eventuate.common.Event;
import com.networknt.eventuate.common.EventEntity;

@EventEntity(entity = "net.chrisrichardson.eventstore.javaexamples.banking.customersservice.backend.Customer")
public abstract class CustomerEvent implements Event {
}