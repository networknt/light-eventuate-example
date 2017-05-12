package com.networknt.eventuate.reference;

import com.networknt.eventuate.reference.common.model.ReferenceData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReferenceQuerySideRepositoryImpl implements ReferenceQuerySideRepository {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private DataSource dataSource;

    public ReferenceQuerySideRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setDataSource(DataSource dataSource) {this.dataSource = dataSource;}



    @Override
    public Map<String, ReferenceData> save(String id, ReferenceData ref) {
        try (final Connection connection = dataSource.getConnection()){
            String psInsert = "INSERT INTO REFERENCE_REPOSITORY (ID, REFERENCE_NAME, DESCRIPTION) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(psInsert);
            stmt.setString(1, id);
            stmt.setString(2, ref.getReferenceName());
            stmt.setString(3, ref.getDescription());

            int count = stmt.executeUpdate();
            if (count != 1) {
                logger.error("Failed to insert REFERENCE_REPOSITORY: {}", id);
            } else {
                Map<String, ReferenceData> refMap = new HashMap<String, ReferenceData>();
                refMap.put(id, ref);
                return refMap;
            }
        } catch (SQLException e) {
            logger.error("SqlException:", e);
        }

        return null;
    }

    @Override
    public Map<String, ReferenceData> update(String id, ReferenceData ref) {
        try (final Connection connection = dataSource.getConnection()){
            String psInsert = "INSERT INTO REFERENCE_REPOSITORY (ID, REFERENCE_NAME, DESCRIPTION) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(psInsert);
            stmt.setString(1, id);
            stmt.setString(2, ref.getReferenceName());
            stmt.setString(3, ref.getDescription());

            int count = stmt.executeUpdate();
            if (count != 1) {
                logger.error("Failed to insert REFERENCE_REPOSITORY: {}", id);
            } else {
                Map<String, ReferenceData> refMap = new HashMap<String, ReferenceData>();
                refMap.put(id, ref);
                return refMap;
            }
        } catch (SQLException e) {
            logger.error("SqlException:", e);
        }

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
