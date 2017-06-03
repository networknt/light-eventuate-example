
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

@ServiceHandler(id="lightapi.net/command/delete/0.1.0")
public class DeleteTodo implements Handler {

    private EventuateAggregateStore eventStore  = (EventuateAggregateStore) SingletonServiceFactory.getBean(EventuateAggregateStore.class);

    private AggregateRepository todoRepository = new AggregateRepository(TodoAggregate.class, eventStore);
    private AggregateRepository bulkDeleteAggregateRepository  = new AggregateRepository(TodoBulkDeleteAggregate.class, eventStore);

    private TodoCommandService service = new TodoCommandServiceImpl(todoRepository, bulkDeleteAggregateRepository);


    @Override
    public ByteBuffer handle(Object input)  {

        // delete a todo-event
        //TODO get input
        String id = null;

        CompletableFuture<TodoInfo> result = service.remove(id).thenApply((e) -> {
            TodoInfo m = e.getAggregate().getTodo();
            return m;
        });


        return NioUtils.toByteBuffer("{\"message\":" + result + "}");
    }
}
