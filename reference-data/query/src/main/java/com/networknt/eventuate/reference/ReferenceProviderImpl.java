package com.networknt.eventuate.reference;

import com.networknt.eventuate.common.AggregateRepository;
import com.networknt.eventuate.common.EntityWithMetadata;
import com.networknt.eventuate.reference.common.model.ReferenceData;
import com.networknt.eventuate.reference.domain.ReferenceDataAggregate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collector;

/**
 * Created by gavin on 2017-05-15.
 */
public class ReferenceProviderImpl implements ReferenceProvider {

    private AggregateRepository aggregateRepository;

    public ReferenceProviderImpl(AggregateRepository refRepository) {
        this.aggregateRepository = refRepository;
    }


    public CompletableFuture<Map<String, ReferenceData>> getReferenceById(String id) {
        CompletableFuture<EntityWithMetadata<ReferenceDataAggregate>> result = aggregateRepository.find(id);

        return result.thenApply(e -> {
            ReferenceData ref = e.getEntity().getReferenceData();
            Map<String, ReferenceData> refMap = new HashMap<>();
            refMap.put(ref.getReferenceName(), ref);
            return refMap;

        });
    }
}
