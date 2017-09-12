package com.networknt.eventuate.reference.service;

import com.networknt.eventuate.common.EntityWithIdAndVersion;

import com.networknt.eventuate.reference.common.model.ReferenceValue;
import com.networknt.eventuate.reference.domain.ReferenceTableAggregate;
import com.networknt.eventuate.reference.domain.ReferenceValueAggregate;

import java.util.concurrent.CompletableFuture;


public interface ReferenceValueCommandService {

    CompletableFuture<EntityWithIdAndVersion<ReferenceValueAggregate>> add(String tableId, ReferenceValue referenceValue);

    CompletableFuture<EntityWithIdAndVersion<ReferenceValueAggregate>> remove(String id);

    CompletableFuture<EntityWithIdAndVersion<ReferenceValueAggregate>> update(String id, String tableId, ReferenceValue referenceValue);

    CompletableFuture<EntityWithIdAndVersion<ReferenceValueAggregate>> relation(String id, String toValueId, String type);

}
