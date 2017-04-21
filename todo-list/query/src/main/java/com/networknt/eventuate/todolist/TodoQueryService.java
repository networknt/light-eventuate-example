package com.networknt.eventuate.todolist;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Created by stevehu on 2016-12-08.
 */
public interface TodoQueryService {

    Collection<Map<String, Object>> getAll();

    CompletableFuture<Map<String, Object>> findById(String id);

    Map<String, Object> save(Map<String, Object> todo);

    void remove(String id);
}
