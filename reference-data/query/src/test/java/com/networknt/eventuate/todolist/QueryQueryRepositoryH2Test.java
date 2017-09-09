package com.networknt.eventuate.todolist;

import com.networknt.eventuate.common.Int128;
import com.networknt.eventuate.reference.ReferenceQuerySideRepository;
import com.networknt.eventuate.reference.common.model.ReferenceTable;
import com.networknt.service.SingletonServiceFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.h2.tools.RunScript;

import javax.sql.DataSource;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by gavin on 2017-04-24.
 * This is a sample test for H2 test DB.
 * refer the
 */
public class QueryQueryRepositoryH2Test {

    public static DataSource ds;

    static {
        ds = (DataSource) SingletonServiceFactory.getBean(DataSource.class);
        try (Connection connection = ds.getConnection()) {
            // Runscript doesn't work need to execute batch here.
        String schemaResourceName = "/query-side-h2-ddl.sql";
        InputStream in = QueryQueryRepositoryH2Test.class.getResourceAsStream(schemaResourceName);

        if (in == null) {
            throw new RuntimeException("Failed to load resource: " + schemaResourceName);
        }
        InputStreamReader reader = new InputStreamReader(in);
        RunScript.execute(connection, reader);

    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    private ReferenceQuerySideRepository refQueryRepository = (ReferenceQuerySideRepository)SingletonServiceFactory.getBean(ReferenceQuerySideRepository.class);
    private static ReferenceTable ref;
    private static  String  id;
    @BeforeClass
    public static void setUp() {
        ref = new ReferenceTable();
        ref.setReferenceName("Country");
        ref.setDescription("complete the test first");


        Int128 idGen = new Int128(1222L, 1011L);
        id = idGen.asString();
    }

    @Test
    public void testSave() {
        Map<String, ReferenceTable>  result = refQueryRepository.save(id, ref);
        assertNotNull(result);
    }



    @Test
    public void testRemove() {
        refQueryRepository.inActive(id);

    }
}
