package com.networknt.eventuate.reference;

import com.networknt.eventuate.common.Int128;
import com.networknt.eventuate.reference.common.model.ReferenceData;
import com.networknt.service.SingletonServiceFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.h2.tools.RunScript;

import javax.sql.DataSource;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by gavin on 2017-04-24.
 * This is a sample test for H2 test DB.
 * refer the
 */
public class ReferenceRepositoryH2Test {

    public static DataSource ds;

    static {
        ds = (DataSource) SingletonServiceFactory.getBean(DataSource.class);
        try (Connection connection = ds.getConnection()) {
            // Runscript doesn't work need to execute batch here.
        String schemaResourceName = "/reference-register-h2-ddl.sql";
        InputStream in = ReferenceRepositoryH2Test.class.getResourceAsStream(schemaResourceName);

        if (in == null) {
            throw new RuntimeException("Failed to load resource: " + schemaResourceName);
        }
        InputStreamReader reader = new InputStreamReader(in);
        RunScript.execute(connection, reader);

    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    private ReferenceRepository refRepository = (ReferenceRepository)SingletonServiceFactory.getBean(ReferenceRepository.class);
    private static ReferenceData ref;
    private static  String  id;
    @BeforeClass
    public static void setUp() {
        ref = new ReferenceData();

        ref.setReferenceName("COUNTRY");
        ref.setDescription("Country Code reference data");

        Int128 idGen = new Int128(1222L, 1011L);
        id = idGen.asString();
    }

    @Test
    public void testSave() {
        Map<String, ReferenceData>  result = refRepository.save(id, ref);
        assertNotNull(result);
    }

    @Test
    public void testGetAll() {
        List<Map<String, ReferenceData>> result= refRepository.getAll();
        assertTrue(result.size()>0);
    }

    @Test
    public void testFindById() {
        Map<String, ReferenceData> result = refRepository.findById(id);
        assertTrue(result.size()>0);

    }

    @Test
    public void testFindByName() {
        Map<String, ReferenceData> result = refRepository.findByName("COUNTRY");
        assertTrue(result.size()>0);

    }
    @Test
    public void testRemove() {
        refRepository.inActive(id);
        Map<String, ReferenceData> result = refRepository.findById(id);
        assertTrue(result.size() ==0);
    }
}
