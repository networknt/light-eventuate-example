package com.networknt.eventuate.reference;


import com.networknt.eventuate.common.CompletableFutureUtil;
import com.networknt.eventuate.reference.common.model.ReferenceData;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;


public class ReferenceQueryServiceImpl implements ReferenceQueryService {

    private ReferenceProvider referenceProvider;

    public ReferenceQueryServiceImpl(ReferenceProvider referenceProvider) {
        this.referenceProvider = referenceProvider;
    }

    @Override
    public boolean required(String refName) {
        return true;
    }

    @Override
    public void remove(String id) {
        //todoQueryRepository.remove(id);
    }


    @Override
    public List<Map<String, ReferenceData>> getAll() {
        return null;
     //   return todoQueryRepository.getAll();
    }

    @Override
    public CompletableFuture<Map<String, ReferenceData>> findByName(String refName) {
        return null;
    }

    @Override
    public CompletableFuture<Map<String, ReferenceData>> findById(String id) {
      /*  Map<String, ReferenceData> res = todoQueryRepository.findById(id);
        if (res != null) {
            return CompletableFuture.completedFuture(res);
        }
        return CompletableFutureUtil.failedFuture(new NoSuchElementException("No todo with given id found"));
        */
      return null;
    }
}
