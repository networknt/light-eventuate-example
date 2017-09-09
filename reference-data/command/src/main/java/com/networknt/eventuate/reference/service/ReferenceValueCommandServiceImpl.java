package com.networknt.eventuate.reference.service;

import com.networknt.eventuate.common.AggregateRepository;
import com.networknt.eventuate.common.EntityWithIdAndVersion;
import com.networknt.eventuate.reference.command.*;
import com.networknt.eventuate.reference.common.model.ReferenceValue;
import com.networknt.eventuate.reference.domain.ReferenceTableAggregate;

import java.util.concurrent.CompletableFuture;

/**
 * Created by gavin on 2017-09-09.
 */
public class ReferenceValueCommandServiceImpl implements ReferenceValueCommandService {

    private AggregateRepository<ReferenceTableAggregate, ReferenceCommand> aggregateRepository;

    public ReferenceValueCommandServiceImpl(AggregateRepository<ReferenceTableAggregate, ReferenceCommand> refRepository) {
        this.aggregateRepository = refRepository;
    }

    @Override
    public CompletableFuture<EntityWithIdAndVersion<ReferenceTableAggregate>> add(String tableId, ReferenceValue referenceValue) {
        return aggregateRepository.save(new CreateReferenceValueCommand(tableId, referenceValue));

    }

    @Override
    public CompletableFuture<EntityWithIdAndVersion<ReferenceTableAggregate>> remove(String id){
        return aggregateRepository.update(id, new DeleteReferenceValueCommand());

    }

    @Override
    public CompletableFuture<EntityWithIdAndVersion<ReferenceTableAggregate>> update(String id, String tableId, ReferenceValue referenceValue){
        return aggregateRepository.update(id, new UpdateReferenceValueCommand(tableId, referenceValue));

    }

    @Override
    public CompletableFuture<EntityWithIdAndVersion<ReferenceTableAggregate>> relation(String id, String toValueId, String type){
        return aggregateRepository.update(id, new RelationAddCommand(toValueId, type));

    }
}
