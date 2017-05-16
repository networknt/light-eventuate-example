package com.networknt.eventuate.reference;


import com.networknt.eventuate.common.AggregateRepository;
import com.networknt.eventuate.common.CompletableFutureUtil;
import com.networknt.eventuate.common.DuplicateTriggeringEventException;
import com.networknt.eventuate.reference.common.model.ReferenceData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


public class ReferenceQueryServiceImpl implements ReferenceQueryService {
    private static Logger logger = LoggerFactory.getLogger(AggregateRepository.class);

    private ReferenceProvider referenceProvider;
    private ReferenceQuerySideRepository referenceQuerySideRepository;

    public ReferenceQueryServiceImpl(ReferenceProvider referenceProvider, ReferenceQuerySideRepository referenceQuerySideRepository) {
        this.referenceProvider = referenceProvider;
        this.referenceQuerySideRepository = referenceQuerySideRepository;
    }

    @Override
    public boolean required(String refName) {
        return true;
    }

    @Override
    public void remove(String id) {
        referenceQuerySideRepository.inActive(id);
    }


    @Override
    public List<Map<String, ReferenceData>> getAll() {
        List<String> ids = referenceQuerySideRepository.getAllIds();
        List<Map<String, ReferenceData>> result = new ArrayList<Map<String, ReferenceData>>();

        ids.stream().forEach(e-> findById(e).thenApply((ref)-> {
            result.add(ref);
            return ref;
        }));

        return result;
    }

    @Override
    public CompletableFuture<Map<String, ReferenceData>> findByName(String refName) {
        String id = referenceQuerySideRepository.getRefIdByName(refName);
        return referenceProvider.getReferenceById(id);
    }

    @Override
    public CompletableFuture<Map<String, ReferenceData>> findById(String id) {
      return referenceProvider.getReferenceById(id);
    }

    @Override
    public Map<String, ReferenceData> save(String id, ReferenceData ref) {
        return referenceQuerySideRepository.save(id, ref);
    }

    @Override
    public Map<String, ReferenceData> update(String id, ReferenceData ref) {
        return referenceQuerySideRepository.update(id, ref);
    }

}
