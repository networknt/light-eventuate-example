package com.networknt.eventuate.todolist;


import com.networknt.eventuate.common.DispatchedEvent;
import com.networknt.eventuate.common.EventHandlerMethod;
import com.networknt.eventuate.common.EventSubscriber;
import com.networknt.eventuate.todolist.common.common.event.TodoCreatedEvent;
import com.networknt.eventuate.todolist.common.common.event.TodoDeletedEvent;
import com.networknt.eventuate.todolist.common.common.event.TodoUpdatedEvent;
import com.networknt.service.SingletonServiceFactory;

import java.util.Map;

@EventSubscriber(id = "todoQuerySideEventHandlers")
public class TodoQueryWorkflow {

    private TodoQueryService service =
            (TodoQueryService)SingletonServiceFactory.getBean(TodoQueryService.class);

    public TodoQueryWorkflow() {
    }

    @EventHandlerMethod
    public void create(DispatchedEvent<TodoCreatedEvent> de) {
        Map<String, Object> todo = de.getEvent().getTodo();
        todo.put("id", de.getEntityId());
        service.save(todo);
    }

    @EventHandlerMethod
    public void delete(DispatchedEvent<TodoDeletedEvent> de) {
        service.remove(de.getEntityId());
    }

    @EventHandlerMethod
    public void update(DispatchedEvent<TodoUpdatedEvent> de) {
        Map<String, Object> todo = de.getEvent().getTodo();
        todo.put("id", de.getEntityId());
        service.save(todo);
    }
}
