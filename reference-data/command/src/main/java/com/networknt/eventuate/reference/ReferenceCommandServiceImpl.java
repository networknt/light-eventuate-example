package com.networknt.eventuate.reference;

import com.networknt.eventuate.common.AggregateRepository;
import com.networknt.eventuate.common.EntityWithIdAndVersion;
import com.networknt.eventuate.reference.command.CreateReferenceTableCommand;
import com.networknt.eventuate.reference.command.DeleteReferenceTableCommand;
import com.networknt.eventuate.reference.command.ReferenceCommand;
import com.networknt.eventuate.reference.command.UpdateReferenceTableCommand;
import com.networknt.eventuate.reference.common.model.ReferenceTable;
import com.networknt.eventuate.reference.domain.ReferenceDataAggregate;


import java.util.concurrent.CompletableFuture;


public class ReferenceCommandServiceImpl implements ReferenceCommandService {

    private AggregateRepository<ReferenceDataAggregate, ReferenceCommand> aggregateRepository;

    public ReferenceCommandServiceImpl(AggregateRepository<ReferenceDataAggregate, ReferenceCommand> refRepository) {
        this.aggregateRepository = refRepository;
     }


    @Override
    public CompletableFuture<EntityWithIdAndVersion<ReferenceDataAggregate>> add(ReferenceTable referenceData) {
        return aggregateRepository.save(new CreateReferenceTableCommand(referenceData));
    }

    @Override
    public CompletableFuture<EntityWithIdAndVersion<ReferenceDataAggregate>> remove(String id) {
        return aggregateRepository.update(id, new DeleteReferenceTableCommand());
    }

    @Override
    public CompletableFuture<EntityWithIdAndVersion<ReferenceDataAggregate>> update(String id, ReferenceTable newReferenceData) {
        return aggregateRepository.update(id, new UpdateReferenceTableCommand(id, newReferenceData));
    }


}
