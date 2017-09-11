package com.networknt.eventuate.reference.service;

import com.networknt.eventuate.common.AggregateRepository;
import com.networknt.eventuate.common.EntityWithIdAndVersion;
import com.networknt.eventuate.reference.command.*;
import com.networknt.eventuate.reference.common.model.ReferenceTable;
import com.networknt.eventuate.reference.common.model.ReferenceValue;
import com.networknt.eventuate.reference.domain.ReferenceTableAggregate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by gavin on 2017-09-09.
 */
public class ReferenceCommandServiceImpl implements ReferenceCommandService {

    private ReferenceTableCommandService referenceTableCommandService;
    private ReferenceValueCommandService referenceValueCommandService;

    public ReferenceCommandServiceImpl(ReferenceTableCommandService referenceTableCommandService, ReferenceValueCommandService referenceValueCommandService) {
        this.referenceTableCommandService = referenceTableCommandService;
        this.referenceValueCommandService = referenceValueCommandService;
    }

    @Override
    public String addRefTable(ReferenceTable referenceData)  throws Exception {
        String tableId = referenceTableCommandService.add(referenceData).thenApply((e) -> {
            String id = e.getEntityId();
            return id;
        }).get();
        if (referenceData.getValues()!=null && referenceData.getValues().size()>0) {
            for (ReferenceValue value:referenceData.getValues()) {
                referenceValueCommandService.add(tableId, value);
            }

        }
        return tableId;
    }

    @Override
    public CompletableFuture<EntityWithIdAndVersion<ReferenceTableAggregate>> removeRefTable(String id) {
       return referenceTableCommandService.remove(id);
    }

    @Override
    public CompletableFuture<EntityWithIdAndVersion<ReferenceTableAggregate>> updateRefTable(String id, ReferenceTable newReferenceData){
        return referenceTableCommandService.update(id, newReferenceData);
    }

    @Override
    public CompletableFuture<EntityWithIdAndVersion<ReferenceTableAggregate>> addRefValue(String tableId, ReferenceValue referenceValue) {
        return referenceValueCommandService.add(tableId, referenceValue);
    }

    @Override
    public CompletableFuture<EntityWithIdAndVersion<ReferenceTableAggregate>> removeRefValue(String id){
        return referenceValueCommandService.remove(id);
    }

    @Override
    public CompletableFuture<EntityWithIdAndVersion<ReferenceTableAggregate>> updateRefValue(String id, String tableId, ReferenceValue referenceValue){
        return referenceValueCommandService.update(id, tableId, referenceValue);
    }

    @Override
    public CompletableFuture<EntityWithIdAndVersion<ReferenceTableAggregate>> relation(String id, String toValueId, String type){
        return referenceValueCommandService.relation(id, toValueId, type);
    }
}
