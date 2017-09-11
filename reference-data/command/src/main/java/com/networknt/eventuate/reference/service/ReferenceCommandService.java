package com.networknt.eventuate.reference.service;

import com.networknt.eventuate.common.EntityWithIdAndVersion;
import com.networknt.eventuate.reference.common.model.ReferenceTable;
import com.networknt.eventuate.reference.common.model.ReferenceValue;
import com.networknt.eventuate.reference.domain.ReferenceTableAggregate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public interface ReferenceCommandService {

    String addRefTable(ReferenceTable referenceData)  throws Exception;

    CompletableFuture<EntityWithIdAndVersion<ReferenceTableAggregate>> removeRefTable(String id);

    CompletableFuture<EntityWithIdAndVersion<ReferenceTableAggregate>> updateRefTable(String id, ReferenceTable newReferenceData);

    CompletableFuture<EntityWithIdAndVersion<ReferenceTableAggregate>> addRefValue(String tableId, ReferenceValue referenceValue);

    CompletableFuture<EntityWithIdAndVersion<ReferenceTableAggregate>> removeRefValue(String id);

    CompletableFuture<EntityWithIdAndVersion<ReferenceTableAggregate>> updateRefValue(String id, String tableId, ReferenceValue referenceValue);

    CompletableFuture<EntityWithIdAndVersion<ReferenceTableAggregate>> relation(String id, String toValueId, String type);
}
