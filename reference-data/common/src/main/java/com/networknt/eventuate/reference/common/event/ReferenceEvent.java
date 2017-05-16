package com.networknt.eventuate.reference.common.event;


import com.networknt.eventuate.common.Event;
import com.networknt.eventuate.common.EventEntity;

@EventEntity(entity = "com.networknt.eventuate.reference.domain.ReferenceDataAggregate")
public interface ReferenceEvent extends Event {
}
