package com.networknt.eventuate.reference;

import com.networknt.eventuate.common.EntityWithIdAndVersion;
import com.networknt.eventuate.reference.common.model.ReferenceTable;
import com.networknt.eventuate.reference.domain.ReferenceDataAggregate;


import java.util.concurrent.CompletableFuture;


public interface ReferenceCommandService {

    CompletableFuture<EntityWithIdAndVersion<ReferenceDataAggregate>> add(ReferenceTable referenceData);

    CompletableFuture<EntityWithIdAndVersion<ReferenceDataAggregate>> remove(String id);

    CompletableFuture<EntityWithIdAndVersion<ReferenceDataAggregate>> update(String id, ReferenceTable newReferenceData);

 //   CompletableFuture<EntityWithIdAndVersion<ReferenceDataAggregate>>  deleteAll(List<String> ids);
}
