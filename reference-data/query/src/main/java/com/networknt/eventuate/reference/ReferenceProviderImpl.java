package com.networknt.eventuate.reference;

import com.networknt.eventuate.common.AggregateRepository;
import com.networknt.eventuate.common.EntityWithMetadata;
import com.networknt.eventuate.reference.common.model.ReferenceTable;
import com.networknt.eventuate.reference.domain.ReferenceDataAggregate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Created by gavin on 2017-05-15.
 */
public class ReferenceProviderImpl implements ReferenceProvider {

    private AggregateRepository aggregateRepository;

    public ReferenceProviderImpl(AggregateRepository refRepository) {
        this.aggregateRepository = refRepository;
    }


    public CompletableFuture<Map<String, ReferenceTable>> getReferenceById(String id) {
        CompletableFuture<EntityWithMetadata<ReferenceDataAggregate>> result = aggregateRepository.find(id);

        return result.thenApply(e -> {
            ReferenceTable ref = e.getEntity().getReferenceData();
            Map<String, ReferenceTable> refMap = new HashMap<>();
            refMap.put(ref.getReferenceName(), ref);
            return refMap;

        });
    }
}
