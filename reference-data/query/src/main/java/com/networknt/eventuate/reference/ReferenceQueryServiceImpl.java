package com.networknt.eventuate.reference;



import com.networknt.eventuate.reference.common.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;


public class ReferenceQueryServiceImpl implements ReferenceQueryService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private DataSource dataSource;

    public ReferenceQueryServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setDataSource(DataSource dataSource) {this.dataSource = dataSource;}


    @Override
    public ReferenceTable saveRefTable(String id, ReferenceTable referenceTable){
        Objects.requireNonNull(id);
        Objects.requireNonNull(referenceTable);

        try (final Connection connection = dataSource.getConnection()){
            String psInsert = "INSERT INTO ref_table (table_id, table_name, table_desc, host, active, editable) VALUES (?, ?, ?, ?, ?,?)";
            PreparedStatement stmt = connection.prepareStatement(psInsert);
            stmt.setString(1, id);
            stmt.setString(2, referenceTable.getTableName());
            stmt.setString(3, referenceTable.getTableDesc());
            stmt.setString(4, referenceTable.getHost());
            stmt.setString(5, referenceTable.isActive()?"Y":"N");
            stmt.setString(6, referenceTable.isEditable()?"Y":"N");

            int count = stmt.executeUpdate();
            if (count != 1) {
                logger.error("Failed to insert ref_table: {}", id);
            } else {
                return referenceTable;
            }
        } catch (SQLException e) {
            logger.error("SqlException:", e);
        }

        return null;

    }



    @Override
    public ReferenceValue saveRefValue(String id, ReferenceValue referenceValue){
        Objects.requireNonNull(id);
        Objects.requireNonNull(referenceValue);

        try (final Connection connection = dataSource.getConnection()){
            String psInsert = "INSERT INTO ref_value (value_id, table_id, value_code,  start_time,  end_time, display_order, active) VALUES (?, ?, ?, ?, ?,?, ?)";
            PreparedStatement stmt = connection.prepareStatement(psInsert);
            stmt.setString(1, id);
            stmt.setString(2, referenceValue.getTableId());
            stmt.setString(3, referenceValue.getValueCode());
            stmt.setTimestamp(4, referenceValue.getStartTime()== null?null: Timestamp.valueOf(referenceValue.getStartTime()));
            stmt.setTimestamp(5, referenceValue.getEndTime()== null?null: Timestamp.valueOf(referenceValue.getEndTime()));
            stmt.setInt(6, referenceValue.getDisplayOrder());
            stmt.setString(7, referenceValue.isActive()?"Y":"N");

            int count = stmt.executeUpdate();
            if (count != 1) {
                logger.error("Failed to insert ref_value: {}", id);
            } else {
                return referenceValue;
            }
        } catch (SQLException e) {
            logger.error("SqlException:", e);
        }

        return null;
    }

    @Override
    public int saveRefRelation(String type, String fromValueId, String toValueId){
        try (final Connection connection = dataSource.getConnection()){
            String psInsert = "INSERT INTO relation (type, value_id_from, value_id_to) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(psInsert);
            stmt.setString(1, type);
            stmt.setString(2, fromValueId);
            stmt.setString(3, toValueId);


            int count = stmt.executeUpdate();
            if (count != 1) {
                logger.error("Failed to insert relation: {}", type);
            } else {
                return count;
            }
        } catch (SQLException e) {
            logger.error("SqlException:", e);
        }

        return 0;
    }

    @Override
    public List< ReferenceTable> getAllReferences(String host){
        return null;
    }

    @Override
    public List<String> getAllRefTableNames(String host){
        List<String> refTableNames = new ArrayList<String>();
        String psSelect = "SELECT table_name  FROM ref_table WHERE  active = 'Y' and host = ?";
        try (final Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(psSelect);
            stmt.setString(1, host);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                refTableNames.add(rs.getString("table_name"));
            }
        } catch (SQLException e) {
            logger.error("SqlException:", e);
        }
        return refTableNames;
    }

    @Override
    public ReferenceTable getReferenceByName(String host, String name){
        List<String> hosts = new ArrayList<String>();
        String psSelect = "SELECT host  FROM ref_table WHERE  active = 'Y'";
        try (final Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(psSelect);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                hosts.add(rs.getString("host"));
            }
        } catch (SQLException e) {
            logger.error("SqlException:", e);
        }
        return null;

    }

    @Override
    public ReferenceTable getReferenceById( String id){
        Objects.requireNonNull(id);
        ReferenceTable referenceTable = null;

        String psSelect = "SELECT table_id, table_name, table_desc, host, active, editable, common  FROM ref_table WHERE  active = 'Y' and table_id = ? ";
        try (final Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(psSelect);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                referenceTable  = new ReferenceTable();
                referenceTable.setTableId(rs.getString("table_id"));
                referenceTable.setHost(rs.getString("host"));
                referenceTable.setTableName(rs.getString("table_name"));
                referenceTable.setTableDesc(rs.getString("table_desc"));
                referenceTable.setActive("Y".equalsIgnoreCase(rs.getString("active"))?true:false);
                referenceTable.setEditable("Y".equalsIgnoreCase(rs.getString("editable"))?true:false);
                referenceTable.setCommon("Y".equalsIgnoreCase(rs.getString("common"))?true:false);

                referenceTable.setValues(getReferenceValuesById(id));
            }
        } catch (SQLException e) {
            logger.error("SqlException:", e);
        }
        return referenceTable;

    }

    @Override
    public List<ReferenceValue> getReferenceValuesById( String id) {
        Objects.requireNonNull(id);
        List<ReferenceValue> values = new ArrayList<ReferenceValue>();

        String psSelect = "SELECT value_id, table_id, value_code,  start_time,  end_time, display_order  FROM ref_value WHERE  active = 'Y' table_id = ?";
        try (final Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(psSelect);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ReferenceValue value = new ReferenceValue();
                value.setValueId(rs.getString("value_id"));
                value.setTableId(id);
                value.setValueCode(rs.getString("value_code"));
                value.setStartTime(rs.getTimestamp("start_time")==null?null:rs.getTimestamp("start_time").toLocalDateTime());
                value.setEndTime(rs.getTimestamp("end_time")==null?null:rs.getTimestamp("end_time").toLocalDateTime());
                value.setDisplayOrder(rs.getInt("display_order"));

                value.setLocales(getValueLocaleById(rs.getString("value_id")));
                value.setRelations(getRelationListById(rs.getString("value_id")));

                values.add(value);
            }
        } catch (SQLException e) {
            logger.error("SqlException:", e);
        }
        return values;
    }

    public Set<ValueLocale>  getValueLocaleById(String valueId) {

        Objects.requireNonNull(valueId);
        Set<ValueLocale> values = new HashSet<ValueLocale>();

        String psSelect = "SELECT language, value_desc  FROM value_locale WHERE   value_id = ?";
        try (final Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(psSelect);
            stmt.setString(1, valueId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ValueLocale value = new ValueLocale();
                value.setValueId(valueId);
                value.setValueId(rs.getString("value_id"));
                value.setLanguage(Language.valueOf(rs.getString("language")));
                values.add(value);
            }
        } catch (SQLException e) {
            logger.error("SqlException:", e);
        }

        return values;

    }

    public List<Relation>  getRelationListById(String valueId) {


        return null;

    }

    @Override
    public List<String> getAllHosts(){
        List<String> hosts = new ArrayList<String>();
        String psSelect = "SELECT host  FROM ref_table WHERE  active = 'Y'";
        try (final Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(psSelect);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                hosts.add(rs.getString("host"));
            }
        } catch (SQLException e) {
            logger.error("SqlException:", e);
        }
        return hosts;
    }

    @Override
    public int deleteRefTable(String id){

        Objects.requireNonNull(id);
        int count = 0;
        try (final Connection connection = dataSource.getConnection()){
            String psDelete1 = "UPDATE ref_table SET active = 'N' WHERE table_id = ?";;
            String psDelete2 = "UPDATE ref_value SET active = 'N' WHERE table_id = ?";
            connection.setAutoCommit(false);
            PreparedStatement psEntity = connection.prepareStatement(psDelete1);
            psEntity.setString(1, id);
            count = psEntity.executeUpdate();
            psEntity = connection.prepareStatement(psDelete2);
            psEntity.setString(1, id);
            psEntity.executeUpdate();
            connection.commit();
            if (count != 1) {
                logger.error("Failed to delete reference table: {}", id);
            }

        } catch (SQLException e) {
            logger.error("SqlException:", e);
        }

        return count;
    }

    @Override
    public int deleteRefValue(String id){
        Objects.requireNonNull(id);
        int count = 0;
        try (final Connection connection = dataSource.getConnection()){
            String psDelete = "UPDATE ref_value SET active = 'N' WHERE value_id = ?";;
            PreparedStatement psEntity = connection.prepareStatement(psDelete);
            psEntity.setString(1, id);

            count = psEntity.executeUpdate();
            if (count != 1) {
                logger.error("Failed to delete reference value: {}", id);
            }

        } catch (SQLException e) {
            logger.error("SqlException:", e);
        }

        return count;
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

}
