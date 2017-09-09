package com.networknt.eventuate.reference.service;

import com.networknt.eventuate.common.EntityWithIdAndVersion;
import com.networknt.eventuate.reference.common.model.ReferenceTable;
import com.networknt.eventuate.reference.domain.ReferenceTableAggregate;

import java.util.concurrent.CompletableFuture;


public interface ReferenceTableCommandService {

    CompletableFuture<EntityWithIdAndVersion<ReferenceTableAggregate>> add(ReferenceTable referenceData);

    CompletableFuture<EntityWithIdAndVersion<ReferenceTableAggregate>> remove(String id);

    CompletableFuture<EntityWithIdAndVersion<ReferenceTableAggregate>> update(String id, ReferenceTable newReferenceData);


}
