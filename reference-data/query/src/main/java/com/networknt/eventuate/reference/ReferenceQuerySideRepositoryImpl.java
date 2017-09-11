package com.networknt.eventuate.reference;

import com.networknt.eventuate.reference.common.model.ReferenceTable;
import com.networknt.eventuate.reference.common.model.ReferenceValue;
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
import com.networknt.eventuate.reference.domain.Status;

public class ReferenceQuerySideRepositoryImpl implements ReferenceQuerySideRepository {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private DataSource dataSource;

    public ReferenceQuerySideRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setDataSource(DataSource dataSource) {this.dataSource = dataSource;}


    @Override
    public ReferenceTable saveRefTable(String id, ReferenceTable referenceTable){
        return null;
    }

    @Override
    public ReferenceValue saveRefValue(String id, ReferenceValue referenceValue){
        return null;
    }

    @Override
    public ReferenceValue saveRefRelation(String id, String toValueId, String type){
        return null;
    }

    @Override
    public List< ReferenceTable> getAllReferences(String host){
        return null;
    }

    @Override
    public List<String> getAllRefTableNames(String host){
        return null;
    }

    @Override
    public ReferenceTable getReferenceByName(String host, String name){
        return null;
    }

    @Override
    public ReferenceTable getReferenceById( String id){
        return null;
    }

    @Override
    public List<String> getAllHosts(){
        return null;
    }

    @Override
    public int deleteRefTable(String id){
        return 0;
    }

    @Override
    public int deleteRefValue(String id){
        return 0;
    }

    @Override
    public ReferenceTable updateRefTable(String id, ReferenceTable referenceTable){
        return null;
    }

    @Override
    public ReferenceValue updateRefValue(String id, ReferenceValue referenceValue){
        return null;
    }

    @Override
    public void referenceActive(String id, boolean active){

    }

/*
    @Override
    public Map<String, ReferenceTable> save(String id, ReferenceTable ref) {
        try (final Connection connection = dataSource.getConnection()){
            String psInsert = "INSERT INTO REFERENCE_QUERY_LIST (ID, REFERENCE_NAME, DESCRIPTION, STATUS) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(psInsert);
            stmt.setString(1, id);
            stmt.setString(2, ref.getReferenceName());
            stmt.setString(3, ref.getDescription());
            stmt.setString(4, Status.CREATED.asString());

            int count = stmt.executeUpdate();
            if (count != 1) {
                logger.error("Failed to insert REFERENCE_QUERY_LIST: {}", id);
            } else {
                Map<String, ReferenceTable> refMap = new HashMap<String, ReferenceTable>();
                refMap.put(id, ref);
                return refMap;
            }
        } catch (SQLException e) {
            logger.error("SqlException:", e);
        }

        return null;
    }

    @Override
    public Map<String, ReferenceTable> update(String id, ReferenceTable ref) {
        try (final Connection connection = dataSource.getConnection()){
            String psInsert = "UPDATE REFERENCE_QUERY_LIST SET STATUS = ? WHERE ID = ?";
            PreparedStatement stmt = connection.prepareStatement(psInsert);
            stmt.setString(1, Status.UPDATED.asString());
            stmt.setString(2, id);


            int count = stmt.executeUpdate();
            if (count != 1) {
                logger.error("Failed to UPDATE REFERENCE_QUERY_LIST: {}", id);
            } else {
                Map<String, ReferenceTable> refMap = new HashMap<String, ReferenceTable>();
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
            String psDelete = "UPDATE REFERENCE_QUERY_LIST SET ACTIVE_FLG = 'N' WHERE ID = ?";
            PreparedStatement psEntity = connection.prepareStatement(psDelete);
            psEntity.setString(1, id);
            int count = psEntity.executeUpdate();
            if (count != 1) {
                logger.error("Failed to update REFERENCE_QUERY_LIST: {}", id);
            }

        } catch (SQLException e) {
            logger.error("SqlException:", e);
        }

    }

    @Override
    public List<String> getAllIds() {
        List<String> ids = new ArrayList<String>();

        try (final Connection connection = dataSource.getConnection()){
            String psSelect = "SELECT ID FROM REFERENCE_QUERY_LIST WHERE ACTIVE_FLG = 'Y'  and STATUS = 'A' order by ID asc";
            PreparedStatement stmt = connection.prepareStatement(psSelect);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                  ids.add(rs.getString("ID"));
            }
        } catch (SQLException e) {
            logger.error("SqlException:", e);
        }

        return ids;
    }

    @Override
    public String getRefIdByName(String name) {
        String id = null;
        try (final Connection connection = dataSource.getConnection()){
            String psSelect = "SELECT ID FROM REFERENCE_QUERY_LIST WHERE ACTIVE_FLG = 'Y'  and STATUS = 'A' and REFERENCE_NAME = ?";
            PreparedStatement stmt = connection.prepareStatement(psSelect);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getString("ID");
            }
        } catch (SQLException e) {
            logger.error("SqlException:", e);
        }

        return id;
    }

    */
}
