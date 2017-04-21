package com.networknt.eventuate.todolist;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by stevehu on 2016-12-09.
 */
public class TodoQueryRepositoryImpl implements TodoQueryRepository {
    private final Map<String, Map<String, Object>> todoMap = new ConcurrentHashMap<>();

    @Override
    public Collection<Map<String, Object>> getAll() {
        return todoMap.values();
    }

    @Override
    public Map<String, Object> findById(String id) {
        return todoMap.get(id);
    }

    @Override
    public Map<String, Object> save(Map<String, Object> todo) {
        todoMap.put((String)todo.get("id"), todo);
        return todo;
    }

    @Override
    public void remove(String id) {
        todoMap.remove(id);
    }


}
