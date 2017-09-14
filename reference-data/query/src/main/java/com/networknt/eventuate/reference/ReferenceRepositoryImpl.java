package com.networknt.eventuate.reference;

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

    //Local cache for reference data, structure: host->Map(refTableName->ReferenceTable)
    private Map<String, List<ReferenceTable>> referenceDataMap = new ConcurrentHashMap<String, List<ReferenceTable>>();

    private ReferenceDataCache dataCache;

    public ReferenceRepositoryImpl(ReferenceQueryService referenceQueryService) {
        this.referenceQueryService = referenceQueryService;
        this.dataCache =  ReferenceDataCache.getInstance();
    }

    @Override
    public ReferenceTable saveRefTable(String id, ReferenceTable referenceTable)  throws ReferenceDuplicatedException {
        Objects.requireNonNull(id);
        Objects.requireNonNull(referenceTable);

        if (getReferenceByName(referenceTable.getHost(), referenceTable.getTableName()) !=null ) {
            throw new ReferenceDuplicatedException("Reference table Name has already been taken");
        }
        return referenceQueryService.saveRefTable(id, referenceTable);
    }

    @Override
    public ReferenceValue saveRefValue(String id, ReferenceValue referenceValue){
        Objects.requireNonNull(id);
        Objects.requireNonNull(referenceValue);
        return referenceQueryService.saveRefValue (id, referenceValue);
    }


    @Override
    public int saveRefRelation(String type, String fromValueId, String toValueId){
        Objects.requireNonNull(type);
        Objects.requireNonNull(fromValueId);
        Objects.requireNonNull(toValueId);
        return referenceQueryService.saveRefRelation(type, fromValueId, toValueId);
    }

    @Override
    public List< ReferenceTable> getAllReferences(String host){
        Objects.requireNonNull(host);
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
        if (!dataCache.hasRefTablesForHost(host)) {
            dataCache.putHostRefTables(host, referenceQueryService.getAllReferences(host));
        }

        ReferenceTable ref = dataCache.getRefTableByName(host, name);
        if (ref== null) {
            ReferenceTable ref1 = referenceQueryService.getReferenceByName(host, name);
            if (ref1!=null) {
                dataCache.addHostRefTable(host, ref1);
                if (ref1 == null) {
                    return Optional.empty();
                }
                return Optional.of(ref1);
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
        ReferenceTable ref = dataCache.getRefTableById(id);
        if (ref== null) {
            ReferenceTable ref1 = referenceQueryService.getReferenceById(id);
            if (ref1!=null) {
                dataCache.addHostRefTable(ref1.getHost(), ref1);
                if (ref1 == null) {
                    return Optional.empty();
                }
                return Optional.of(ref1);
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
        return referenceQueryService.getReferenceValuesById(id);
    }


    @Override
    public List<String> getAllHosts(){
        return referenceQueryService.getAllHosts();

    }

    @Override
    public int deleteRefTable(String id){
        Objects.requireNonNull(id);
        boolean found;
        for (Map.Entry<String, List<ReferenceTable>> entry : referenceDataMap.entrySet()) {
            List<ReferenceTable> refList = entry.getValue();
            for (ReferenceTable reference:refList) {
                if (id.equals(reference.getTableId()))  {
                    break;
                }
            }
          //  refList.remove();
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
        return referenceQueryService.updateRefTable(id, referenceTable);

    }

    @Override
    public ReferenceValue updateRefValue(String id, ReferenceValue referenceValue){
        Objects.requireNonNull(id);
        Objects.requireNonNull(referenceValue);
        return referenceQueryService.updateRefValue(id, referenceValue);
    }

    @Override
    public void referenceActive(String id, boolean active){
        Objects.requireNonNull(id);
        referenceQueryService.referenceActive(id, active);
    }

}
