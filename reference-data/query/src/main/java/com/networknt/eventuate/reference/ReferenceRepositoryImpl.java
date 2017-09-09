package com.networknt.eventuate.reference;

import com.networknt.eventuate.reference.common.model.ReferenceTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReferenceRepositoryImpl implements ReferenceRepository {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private DataSource dataSource;

    public ReferenceRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setDataSource(DataSource dataSource) {this.dataSource = dataSource;}

    @Override
    public List<Map<String, ReferenceTable>> getAll() {
        List<Map<String, ReferenceTable>> allRefs = new ArrayList<Map<String, ReferenceTable>>();


        return allRefs;
    }


    @Override
    public Map<String, ReferenceTable> findById(String id) {
        Map<String, ReferenceTable> refMap = new HashMap<String, ReferenceTable>();
          return refMap;
    }

    @Override
    public Map<String, ReferenceTable> findByName(String referenceName) {
        Map<String, ReferenceTable> refMap = new HashMap<String, ReferenceTable>();

        return refMap;
    }

    @Override
    public Map<String, ReferenceTable> save(String id, ReferenceTable ref) {

        return null;
    }

    @Override
    public void inActive(String id) {
        try (final Connection connection = dataSource.getConnection()){
            String psDelete = "UPDATE REFERENCE_REPOSITORY SET ACTIVE_FLG = 'N' WHERE ID = ?";
            PreparedStatement psEntity = connection.prepareStatement(psDelete);
            psEntity.setString(1, id);
            int count = psEntity.executeUpdate();
            if (count != 1) {
                logger.error("Failed to update REFERENCE_REPOSITORY: {}", id);
            }

        } catch (SQLException e) {
            logger.error("SqlException:", e);
        }

    }


}
