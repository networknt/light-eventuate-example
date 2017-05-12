package com.networknt.eventuate.reference;

import com.networknt.eventuate.common.AggregateRepository;
import com.networknt.eventuate.common.EntityWithIdAndVersion;
import com.networknt.eventuate.reference.command.CreateReferenceCommand;
import com.networknt.eventuate.reference.command.DeleteReferenceCommand;
import com.networknt.eventuate.reference.command.ReferenceCommand;
import com.networknt.eventuate.reference.command.UpdateReferenceCommand;
import com.networknt.eventuate.reference.common.model.ReferenceData;
import com.networknt.eventuate.reference.domain.ReferenceDataAggregate;


import java.util.concurrent.CompletableFuture;


public class ReferenceCommandServiceImpl implements ReferenceCommandService {

    private AggregateRepository<ReferenceDataAggregate, ReferenceCommand> aggregateRepository;

    public ReferenceCommandServiceImpl(AggregateRepository<ReferenceDataAggregate, ReferenceCommand> refRepository) {
        this.aggregateRepository = refRepository;
     }


    @Override
    public CompletableFuture<EntityWithIdAndVersion<ReferenceDataAggregate>> add(ReferenceData referenceData) {
        return aggregateRepository.save(new CreateReferenceCommand(referenceData));
    }

    @Override
    public CompletableFuture<EntityWithIdAndVersion<ReferenceDataAggregate>> remove(String id) {
        return aggregateRepository.update(id, new DeleteReferenceCommand());
    }

    @Override
    public CompletableFuture<EntityWithIdAndVersion<ReferenceDataAggregate>> update(String id, ReferenceData newReferenceData) {
        return aggregateRepository.update(id, new UpdateReferenceCommand(id, newReferenceData));
    }


}
