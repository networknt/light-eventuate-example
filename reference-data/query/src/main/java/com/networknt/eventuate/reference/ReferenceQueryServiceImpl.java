package com.networknt.eventuate.reference;


import com.networknt.eventuate.common.AggregateRepository;
import com.networknt.eventuate.reference.common.model.ReferenceTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.CompletableFuture;


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
    public List<Map<String, ReferenceTable>> getAll() {
        List<String> ids = referenceQuerySideRepository.getAllIds();
        List<Map<String, ReferenceTable>> result = new ArrayList<Map<String, ReferenceTable>>();

        ids.stream().forEach(e-> findById(e).thenApply((ref)-> {
            result.add(ref);
            return ref;
        }));

        return result;
    }

    @Override
    public CompletableFuture<Map<String, ReferenceTable>> findByName(String refName) {
        String id = referenceQuerySideRepository.getRefIdByName(refName);
        return referenceProvider.getReferenceById(id);
    }

    @Override
    public CompletableFuture<Map<String, ReferenceTable>> findById(String id) {
      return referenceProvider.getReferenceById(id);
    }

    @Override
    public Map<String, ReferenceTable> save(String id, ReferenceTable ref) {
        return referenceQuerySideRepository.save(id, ref);
    }

    @Override
    public Map<String, ReferenceTable> update(String id, ReferenceTable ref) {
        return referenceQuerySideRepository.update(id, ref);
    }

}
