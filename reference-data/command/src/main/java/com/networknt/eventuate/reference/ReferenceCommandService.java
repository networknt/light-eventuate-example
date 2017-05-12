package com.networknt.eventuate.reference;

import com.networknt.eventuate.common.EntityWithIdAndVersion;
import com.networknt.eventuate.reference.common.model.ReferenceData;
import com.networknt.eventuate.reference.domain.ReferenceDataAggregate;


import java.util.List;
import java.util.concurrent.CompletableFuture;


public interface ReferenceCommandService {

    CompletableFuture<EntityWithIdAndVersion<ReferenceDataAggregate>> add(ReferenceData referenceData);

    CompletableFuture<EntityWithIdAndVersion<ReferenceDataAggregate>> remove(String id);

    CompletableFuture<EntityWithIdAndVersion<ReferenceDataAggregate>> update(String id, ReferenceData newReferenceData);

 //   CompletableFuture<EntityWithIdAndVersion<ReferenceDataAggregate>>  deleteAll(List<String> ids);
}
