package com.networknt.eventuate.reference;

import com.networknt.config.Config;
import com.networknt.eventuate.reference.common.exception.ReferenceDuplicatedException;
import com.networknt.eventuate.reference.common.model.*;
import com.networknt.eventuate.reference.domain.ReferenceDataCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ReferenceRepositoryImpl implements ReferenceRepository {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    ReferenceQueryService referenceQueryService;

    static String REFERENCE_CONFIG_NAME = "reference";
    static ReferenceConfig referenceConfig = (ReferenceConfig) Config.getInstance().getJsonObjectConfig(REFERENCE_CONFIG_NAME, ReferenceConfig.class);

    private boolean useCache = false;

    private ReferenceDataCache dataCache;

    public ReferenceRepositoryImpl(ReferenceQueryService referenceQueryService) {
        this.referenceQueryService = referenceQueryService;
        this.dataCache =  ReferenceDataCache.getInstance();
        useCache = referenceConfig.isUseCache();
    }

    @Override
    public ReferenceTable saveRefTable(String id, ReferenceTable referenceTable)  throws ReferenceDuplicatedException {
        Objects.requireNonNull(id);
        Objects.requireNonNull(referenceTable);

        if (getReferenceByName(referenceTable.getHost(), referenceTable.getTableName()) !=null ) {
            throw new ReferenceDuplicatedException("Reference table Name has already been taken");
        }
        if (useCache) {
            referenceTable.setTableId(id);
            dataCache.addHostRefTable(referenceTable.getHost(), referenceTable);
        }
        return referenceQueryService.saveRefTable(id, referenceTable);
    }

    @Override
    public ReferenceValue saveRefValue(String id, ReferenceValue referenceValue){
        Objects.requireNonNull(id);
        Objects.requireNonNull(referenceValue);
        referenceValue.setValueId(id);
        if (useCache) {
            ReferenceTable referenceTable =  dataCache.getRefTableById(referenceValue.getTableId());
            if (referenceTable!=null) {
                referenceTable.getValues().add(referenceValue);
            }
        }
        return referenceQueryService.saveRefValue (id, referenceValue);
    }


    @Override
    public int saveRefRelation(String type, String fromValueId, String toValueId){
        Objects.requireNonNull(type);
        Objects.requireNonNull(fromValueId);
        Objects.requireNonNull(toValueId);
        if (useCache) {
            ReferenceValue value = dataCache.getReferenceValueByValueId(fromValueId);
            if (value!=null) {
                value.addRelation(new Relation(toValueId, type));
            }
        }
        return referenceQueryService.saveRefRelation(type, fromValueId, toValueId);
    }

    @Override
    public List< ReferenceTable> getAllReferences(String host){
        Objects.requireNonNull(host);
        if (!useCache) {
            return referenceQueryService.getAllReferences(host);
        }
        List< ReferenceTable> refTables ;
        if (dataCache.hasRefTablesForHost(host)) {
            refTables = dataCache.getAllRefByHost(host);
        } else {
            refTables = referenceQueryService.getAllReferences(host);
            dataCache.putHostRefTables(host, refTables);
        }

        return refTables;
    }

    @Override
    public List<String> getAllRefTableNames(String host){
        Objects.requireNonNull(host);
        if (!useCache) {
            referenceQueryService.getAllRefTableNames(host);
        }

        if (!dataCache.hasRefTablesForHost(host)) {
            dataCache.putHostRefTables(host, referenceQueryService.getAllReferences(host));
        }
        return dataCache.getAllRefByHost(host).stream().map(e->e.getTableName()).collect(Collectors.toList());
        //return referenceQueryService.getAllRefTableNames(host);
    }

    @Override
    public Optional<ReferenceTable> getReferenceByName(String host, String name) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(host);
        ReferenceTable ref;
        if (!useCache) {
            ref = referenceQueryService.getReferenceByName(host, name);
        } else {
            if (!dataCache.hasRefTablesForHost(host)) {
                dataCache.putHostRefTables(host, referenceQueryService.getAllReferences(host));
            }

            ref = dataCache.getRefTableByName(host, name);
            if (ref== null) {
                ref = referenceQueryService.getReferenceByName(host, name);
                if (ref!=null) {
                    dataCache.addHostRefTable(host, ref);
                }
            }
        }

        if (ref == null) {
            return Optional.empty();
        }
        return Optional.of(ref);
    }

    @Override
    public Optional<ReferenceTable> getReferenceById( String id){
        Objects.requireNonNull(id);
        ReferenceTable ref;
        if (!useCache) {
            ref = referenceQueryService.getReferenceById(id);
        } else {
            ref = dataCache.getRefTableById(id);
            if (ref== null) {
                ref = referenceQueryService.getReferenceById(id);
                if (ref!=null) {
                    dataCache.addHostRefTable(ref.getHost(), ref);
                }
            }
        }

        if (ref == null) {
            return Optional.empty();
        }
        return Optional.of(ref);
    }

    @Override
    public List<ReferenceValue> getReferenceValuesById( String id) {
        Objects.requireNonNull(id);
        if (useCache) {
            dataCache.getReferenceValuesById(id);
        }
        return referenceQueryService.getReferenceValuesById(id);
    }


    @Override
    public List<String> getAllHosts(){
        return referenceQueryService.getAllHosts();

    }

    @Override
    public int deleteRefTable(String id){
        Objects.requireNonNull(id);
        if (useCache) {
            dataCache.deleteRefTableById(id);
        }
        return referenceQueryService.deleteRefTable(id);
     }

    @Override
    public int deleteRefValue(String id){
        Objects.requireNonNull(id);
        return referenceQueryService.deleteRefValue(id);
    }

    @Override
    public ReferenceTable updateRefTable(String id, ReferenceTable referenceTable){
        Objects.requireNonNull(id);
        Objects.requireNonNull(referenceTable);
        if (useCache) {
            ReferenceTable oldReferenceTable =  dataCache.getRefTableById(id);
            if (oldReferenceTable!=null) {
                oldReferenceTable.updateObject(referenceTable);
            }
        }
        return referenceQueryService.updateRefTable(id, referenceTable);

    }

    @Override
    public ReferenceValue updateRefValue(String id, ReferenceValue referenceValue){
        Objects.requireNonNull(id);
        Objects.requireNonNull(referenceValue);
        if (useCache) {
            ReferenceValue value = dataCache.getReferenceValueByValueId(id);
            if (value!=null) {
                value.updateObject(referenceValue);
            }
        }
        return referenceQueryService.updateRefValue(id, referenceValue);
    }

    @Override
    public void referenceActive(String id, boolean active){
        Objects.requireNonNull(id);
        referenceQueryService.referenceActive(id, active);
    }

}
