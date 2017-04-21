package com.networknt.eventuate.todolist;

import java.util.Collection;
import java.util.Map;

/**
 * Created by stevehu on 2016-12-09.
 */
public interface TodoQueryRepository {

    Collection<Map<String, Object>> getAll();

    Map<String, Object> findById(String id);

    Map<String, Object> save(Map<String, Object> todo);

    void remove(String id);
}
