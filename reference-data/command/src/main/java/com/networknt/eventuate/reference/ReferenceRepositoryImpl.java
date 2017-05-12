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

public class ReferenceRepositoryImpl implements ReferenceRepository {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private DataSource dataSource;

    public ReferenceRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setDataSource(DataSource dataSource) {this.dataSource = dataSource;}

    @Override
    public List<Map<String, ReferenceData>> getAll() {
        List<Map<String, ReferenceData>> allRefs = new ArrayList<Map<String, ReferenceData>>();

        try (final Connection connection = dataSource.getConnection()){
            String psSelect = "SELECT ID, REFERENCE_NAME, DESCRIPTION FROM REFERENCE_REPOSITORY WHERE ACTIVE_FLG = 'Y' order by REFERENCE_NAME asc";
            PreparedStatement stmt = connection.prepareStatement(psSelect);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ReferenceData ref = new ReferenceData();
                ref.setReferenceName(rs.getString("REFERENCE_NAME"));
                ref.setDescription(rs.getString("DESCRIPTION"));
                Map<String, ReferenceData> todoMap = new HashMap<String, ReferenceData>();
                todoMap.put(rs.getString("id"), ref);
                allRefs.add(todoMap);
            }
        } catch (SQLException e) {
            logger.error("SqlException:", e);
        }

        return allRefs;
    }


    @Override
    public Map<String, ReferenceData> findById(String id) {
        Map<String, ReferenceData> refMap = new HashMap<String, ReferenceData>();
        try (final Connection connection = dataSource.getConnection()){
            String psSelect = "SELECT ID, REFERENCE_NAME, DESCRIPTION FROM REFERENCE_REPOSITORY WHERE ACTIVE_FLG = 'Y' AND ID = ?";
            PreparedStatement stmt = connection.prepareStatement(psSelect);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs == null || rs.getFetchSize() > 1) {
                logger.error("incorrect fetch result {}", id);
            }
            while (rs.next()) {
                ReferenceData ref = new ReferenceData();
                ref.setReferenceName(rs.getString("REFERENCE_NAME"));
                ref.setDescription(rs.getString("DESCRIPTION"));
                refMap.put(rs.getString("id"), ref);
            }
        } catch (SQLException e) {
            logger.error("SqlException:", e);
        }

        return refMap;
    }

    @Override
    public Map<String, ReferenceData> findByName(String referenceName) {
        Map<String, ReferenceData> refMap = new HashMap<String, ReferenceData>();
        try (final Connection connection = dataSource.getConnection()){
            String psSelect = "SELECT ID, REFERENCE_NAME, DESCRIPTION FROM REFERENCE_REPOSITORY WHERE ACTIVE_FLG = 'Y' AND REFERENCE_NAME = ?";
            PreparedStatement stmt = connection.prepareStatement(psSelect);
            stmt.setString(1, referenceName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ReferenceData ref = new ReferenceData();
                ref.setReferenceName(rs.getString("REFERENCE_NAME"));
                ref.setDescription(rs.getString("DESCRIPTION"));
                refMap.put(rs.getString("id"), ref);
            }
        } catch (SQLException e) {
            logger.error("SqlException:", e);
        }

        return refMap;
    }

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
