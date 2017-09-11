package com.networknt.eventuate.reference;

import com.networknt.eventuate.reference.common.exception.ReferenceDuplicatedException;
import com.networknt.eventuate.reference.common.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class ReferenceRepositoryImpl implements ReferenceRepository {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    ReferenceQueryService referenceQueryService;

    public ReferenceRepositoryImpl(ReferenceQueryService referenceQueryService) {
        this.referenceQueryService = referenceQueryService;
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
        return referenceQueryService.getAllReferences(host);
    }

    @Override
    public List<String> getAllRefTableNames(String host){
        Objects.requireNonNull(host);
        return referenceQueryService.getAllRefTableNames(host);
    }

    @Override
    public ReferenceTable getReferenceByName(String host, String name) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(host);
        return referenceQueryService.getReferenceByName(host, name);

    }

    @Override
    public ReferenceTable getReferenceById( String id){
        Objects.requireNonNull(id);
        return referenceQueryService.getReferenceById(id);
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
