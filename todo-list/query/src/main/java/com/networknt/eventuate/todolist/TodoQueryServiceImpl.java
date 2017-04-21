package com.networknt.eventuate.todolist;


import com.networknt.eventuate.common.CompletableFutureUtil;

import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;


public class TodoQueryServiceImpl implements TodoQueryService {

    private TodoQueryRepository todoQueryRepository;

    public TodoQueryServiceImpl(TodoQueryRepository todoQueryRepository) {
        this.todoQueryRepository = todoQueryRepository;
    }

    @Override
    public Map<String, Object> save(Map<String, Object> todo) {
        return todoQueryRepository.save(todo);
    }

    @Override
    public void remove(String id) {
        todoQueryRepository.remove(id);
    }


    @Override
    public Collection<Map<String, Object>> getAll() {
        return todoQueryRepository.getAll();
    }

    @Override
    public CompletableFuture<Map<String, Object>> findById(String id) {
        Map<String, Object> res = todoQueryRepository.findById(id);
        if (res != null) {
            return CompletableFuture.completedFuture(res);
        }
        return CompletableFutureUtil.failedFuture(new NoSuchElementException("No todo with given id found"));
    }
}
