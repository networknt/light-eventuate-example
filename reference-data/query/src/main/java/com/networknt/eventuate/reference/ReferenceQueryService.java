package com.networknt.eventuate.reference;


import com.networknt.eventuate.reference.common.model.ReferenceTable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;


public interface ReferenceQueryService {

    boolean required(String refName);

    List<Map<String, ReferenceTable>> getAll();

    CompletableFuture<Map<String, ReferenceTable>> findById(String id);

    CompletableFuture<Map<String, ReferenceTable>> findByName(String refName);

    Map<String, ReferenceTable> save(String id, ReferenceTable ref);

    Map<String, ReferenceTable> update(String id, ReferenceTable ref);

    void remove(String id);
}
