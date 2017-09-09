package com.networknt.eventuate.reference.service;

import com.networknt.eventuate.common.EntityWithIdAndVersion;

import com.networknt.eventuate.reference.common.model.ReferenceValue;
import com.networknt.eventuate.reference.domain.ReferenceTableAggregate;

import java.util.concurrent.CompletableFuture;


public interface ReferenceValueCommandService {

    CompletableFuture<EntityWithIdAndVersion<ReferenceTableAggregate>> add(String tableId, ReferenceValue referenceValue);

    CompletableFuture<EntityWithIdAndVersion<ReferenceTableAggregate>> remove(String id);

    CompletableFuture<EntityWithIdAndVersion<ReferenceTableAggregate>> update(String id, String tableId, ReferenceValue referenceValue);

    CompletableFuture<EntityWithIdAndVersion<ReferenceTableAggregate>> relation(String id, String toValueId, String type);

}
