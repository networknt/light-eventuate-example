
package com.networknt.command.handler;


import com.networknt.eventuate.common.AggregateRepository;
import com.networknt.eventuate.common.EventuateAggregateStore;
import com.networknt.eventuate.todolist.TodoCommandService;
import com.networknt.eventuate.todolist.TodoCommandServiceImpl;
import com.networknt.eventuate.todolist.common.model.TodoInfo;
import com.networknt.eventuate.todolist.domain.TodoAggregate;
import com.networknt.eventuate.todolist.domain.TodoBulkDeleteAggregate;
import com.networknt.service.SingletonServiceFactory;
import com.networknt.utility.NioUtils;
import com.networknt.rpc.Handler;
import com.networknt.rpc.router.ServiceHandler;

import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;

@ServiceHandler(id="lightapi.net/command/update/0.1.0")
public class UpdateTodo implements Handler {

    private EventuateAggregateStore eventStore  = (EventuateAggregateStore)SingletonServiceFactory.getBean(EventuateAggregateStore.class);

    private AggregateRepository todoRepository = new AggregateRepository(TodoAggregate.class, eventStore);
    private AggregateRepository bulkDeleteAggregateRepository  = new AggregateRepository(TodoBulkDeleteAggregate.class, eventStore);

    private TodoCommandService service = new TodoCommandServiceImpl(todoRepository, bulkDeleteAggregateRepository);
    @Override
    public ByteBuffer handle(Object input)  {

        //TODO get input
        String id = null;
        TodoInfo todo = new TodoInfo();

        CompletableFuture<TodoInfo> result = service.update(id, todo).thenApply((e) -> {
            TodoInfo m = e.getAggregate().getTodo();
            return m;
        });


        return NioUtils.toByteBuffer("{\"message\":" + result + "}");
    }
}
