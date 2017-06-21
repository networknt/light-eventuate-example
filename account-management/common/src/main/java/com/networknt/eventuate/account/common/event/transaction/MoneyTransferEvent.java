package com.networknt.eventuate.account.common.event.transaction;


import com.networknt.eventuate.common.Event;
import com.networknt.eventuate.common.EventEntity;

@EventEntity(entity="net.chrisrichardson.eventstore.javaexamples.banking.transactionsservice.backend.MoneyTransfer")
public abstract class MoneyTransferEvent implements Event {
}
