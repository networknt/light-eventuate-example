package com.networknt.eventuate.reference.service;

import com.networknt.eventuate.common.EntityWithIdAndVersion;
import com.networknt.eventuate.reference.common.model.ReferenceTable;
import com.networknt.eventuate.reference.common.model.ReferenceValue;
import com.networknt.eventuate.reference.domain.ReferenceTableAggregate;
import com.networknt.eventuate.reference.domain.ReferenceValueAggregate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public interface ReferenceCommandService {

    String addRefTable(ReferenceTable referenceData)  throws Exception;

    CompletableFuture<EntityWithIdAndVersion<ReferenceTableAggregate>> removeRefTable(String id);

    CompletableFuture<EntityWithIdAndVersion<ReferenceTableAggregate>> updateRefTable(String id, ReferenceTable newReferenceData);

    CompletableFuture<EntityWithIdAndVersion<ReferenceValueAggregate>> addRefValue(String tableId, ReferenceValue referenceValue);

    CompletableFuture<EntityWithIdAndVersion<ReferenceValueAggregate>> removeRefValue(String id);

    CompletableFuture<EntityWithIdAndVersion<ReferenceValueAggregate>> updateRefValue(String id, String tableId, ReferenceValue referenceValue);

    CompletableFuture<EntityWithIdAndVersion<ReferenceValueAggregate>> relation(String id, String toValueId, String type);
}
