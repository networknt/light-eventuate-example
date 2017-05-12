package com.networknt.eventuate.reference;


import com.networknt.eventuate.reference.common.model.ReferenceData;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;


public interface ReferenceQueryService {

    boolean required(String refName);

    List<Map<String, ReferenceData>> getAll();

    CompletableFuture<Map<String, ReferenceData>> findById(String id);

    CompletableFuture<Map<String, ReferenceData>> findByName(String refName);

    Map<String, ReferenceData> save(String id, ReferenceData ref);

    void remove(String id);
}
