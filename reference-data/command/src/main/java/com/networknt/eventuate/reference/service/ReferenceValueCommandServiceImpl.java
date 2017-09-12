package com.networknt.eventuate.reference.service;

import com.networknt.eventuate.common.AggregateRepository;
import com.networknt.eventuate.common.EntityWithIdAndVersion;
import com.networknt.eventuate.reference.command.*;
import com.networknt.eventuate.reference.common.model.ReferenceValue;
import com.networknt.eventuate.reference.domain.ReferenceTableAggregate;
import com.networknt.eventuate.reference.domain.ReferenceValueAggregate;

import java.util.concurrent.CompletableFuture;

/**
 * Created by gavin on 2017-09-09.
 */
public class ReferenceValueCommandServiceImpl implements ReferenceValueCommandService {

    private AggregateRepository<ReferenceValueAggregate, ReferenceCommand> aggregateRepository;

    public ReferenceValueCommandServiceImpl(AggregateRepository<ReferenceValueAggregate, ReferenceCommand> refRepository) {
        this.aggregateRepository = refRepository;
    }

    @Override
    public CompletableFuture<EntityWithIdAndVersion<ReferenceValueAggregate>> add(String tableId, ReferenceValue referenceValue) {
        return aggregateRepository.save(new CreateReferenceValueCommand(tableId, referenceValue));

    }

    @Override
    public CompletableFuture<EntityWithIdAndVersion<ReferenceValueAggregate>> remove(String id){
        return aggregateRepository.update(id, new DeleteReferenceValueCommand());

    }

    @Override
    public CompletableFuture<EntityWithIdAndVersion<ReferenceValueAggregate>> update(String id, String tableId, ReferenceValue referenceValue){
        return aggregateRepository.update(id, new UpdateReferenceValueCommand(tableId, referenceValue));

    }

    @Override
    public CompletableFuture<EntityWithIdAndVersion<ReferenceValueAggregate>> relation(String id, String toValueId, String type){
        return aggregateRepository.update(id, new RelationAddCommand(toValueId, type));

    }
}
