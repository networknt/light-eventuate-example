package com.networknt.eventuate.reference;

import com.networknt.eventuate.reference.common.model.ReferenceTable;
import com.networknt.eventuate.reference.common.model.ReferenceValue;

import java.util.List;



public interface ReferenceQuerySideRepository {



    ReferenceTable saveRefTable(String id, ReferenceTable referenceTable);

    ReferenceValue saveRefValue(String id, ReferenceValue referenceValue);

    ReferenceValue saveRefRelation(String id, String toValueId, String type);


    List< ReferenceTable> getAllReferences(String host);

    List<String> getAllRefTableNames(String host);

    ReferenceTable getReferenceByName(String host, String name);

    ReferenceTable getReferenceById( String id);

    List<String> getAllHosts();

    int deleteRefTable(String id);

    int deleteRefValue(String id);


    ReferenceTable updateRefTable(String id, ReferenceTable referenceTable);

    ReferenceValue updateRefValue(String id, ReferenceValue referenceValue);

    void referenceActive(String id, boolean active);
}
