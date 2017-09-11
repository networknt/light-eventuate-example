package com.networknt.eventuate.reference.service;

import com.networknt.eventuate.common.AggregateRepository;
import com.networknt.eventuate.common.EntityWithIdAndVersion;
import com.networknt.eventuate.reference.command.*;
import com.networknt.eventuate.reference.common.model.ReferenceTable;
import com.networknt.eventuate.reference.common.model.ReferenceValue;
import com.networknt.eventuate.reference.domain.ReferenceTableAggregate;

import java.util.List;
import java.util.concurrent.CompletableFuture;


public class ReferenceTableCommandServiceImpl implements ReferenceTableCommandService {

    private AggregateRepository<ReferenceTableAggregate, ReferenceCommand> aggregateRepository;

    public ReferenceTableCommandServiceImpl(AggregateRepository<ReferenceTableAggregate, ReferenceCommand> refRepository) {
        this.aggregateRepository = refRepository;
     }


    @Override
    public CompletableFuture<EntityWithIdAndVersion<ReferenceTableAggregate>> add(ReferenceTable referenceData) {

        return aggregateRepository.save(new CreateReferenceTableCommand(referenceData));
    }

    @Override
    public CompletableFuture<EntityWithIdAndVersion<ReferenceTableAggregate>> remove(String id) {
        return aggregateRepository.update(id, new DeleteReferenceTableCommand());
    }

    @Override
    public CompletableFuture<EntityWithIdAndVersion<ReferenceTableAggregate>> update(String id, ReferenceTable newReferenceData) {
        return aggregateRepository.update(id, new UpdateReferenceTableCommand(newReferenceData));
    }


}
