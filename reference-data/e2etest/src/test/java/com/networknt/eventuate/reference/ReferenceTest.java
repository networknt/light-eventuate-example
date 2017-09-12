package com.networknt.eventuate.reference;

import com.networknt.eventuate.common.AggregateRepository;
import com.networknt.eventuate.common.EventuateAggregateStore;


import com.networknt.eventuate.reference.common.model.ReferenceTable;
import com.networknt.eventuate.reference.domain.ReferenceTableAggregate;

import com.networknt.eventuate.reference.domain.ReferenceValueAggregate;
import com.networknt.eventuate.reference.service.*;
import com.networknt.service.SingletonServiceFactory;

import org.h2.tools.RunScript;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.concurrent.CompletableFuture;

/**
* implement end to end test for reference data event process
*/
public class ReferenceTest {

    static final Logger logger = LoggerFactory.getLogger(ReferenceTest.class);

    public static DataSource ds;

    static {
        ds = (DataSource) SingletonServiceFactory.getBean(DataSource.class);
        try (Connection connection = ds.getConnection()) {
            // Runscript doesn't work need to execute batch here.
            String schemaResourceName = "/embedded-event-store-schema.sql";
            InputStream in = ReferenceTest.class.getResourceAsStream(schemaResourceName);

            if (in == null) {
                throw new RuntimeException("Failed to load resource: " + schemaResourceName);
            }
            InputStreamReader reader = new InputStreamReader(in);
            RunScript.execute(connection, reader);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private EventuateAggregateStore eventStore  = (EventuateAggregateStore)SingletonServiceFactory.getBean(EventuateAggregateStore.class);

    private AggregateRepository refTableRepository = new AggregateRepository(ReferenceTableAggregate.class, eventStore);
    private AggregateRepository refValueRepository = new AggregateRepository(ReferenceValueAggregate.class, eventStore);

    private ReferenceTableCommandService tableService = new ReferenceTableCommandServiceImpl(refTableRepository);
    private ReferenceValueCommandService valueService = new ReferenceValueCommandServiceImpl(refValueRepository);
    private ReferenceCommandService service = new ReferenceCommandServiceImpl(tableService, valueService);

    @Test
    public void testAddRed() throws Exception {

        ReferenceTable ref = new ReferenceTable();
        ref.setTableName("country");
        String tableId = service.addRefTable(ref);

        System.out.println("result = " + tableId);
    }
/*
    @Test
    public void testUpdateRef() throws Exception {

        ReferenceTable ref = new ReferenceTable();
        ref.setReferenceName("country");

        CompletableFuture<String > id  = service.add(ref).thenApply((e) -> {
            String m =  e.getEntityId();
            return m;
        });

        ref.setActive(false);
        CompletableFuture<ReferenceTable> result = service.update(id.get(), ref).thenApply((e) -> {
            ReferenceTable m = e.getAggregate().getReferenceData();
            System.out.println("m = " + m);
            System.out.println("m = " + e.getEntityId());
            return m;
        });

        System.out.println("result = " + result.get());
    }

    */
}
