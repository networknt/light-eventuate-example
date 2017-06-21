package com.networknt.eventuate.account.common.event.account;


import com.networknt.eventuate.common.Event;
import com.networknt.eventuate.common.EventEntity;

@EventEntity(entity="net.chrisrichardson.eventstore.javaexamples.banking.accountsservice.backend.Account")
public abstract class AccountEvent implements Event {
}
