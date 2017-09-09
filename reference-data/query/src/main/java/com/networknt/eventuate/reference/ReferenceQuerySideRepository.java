package com.networknt.eventuate.reference;

import com.networknt.eventuate.reference.common.model.ReferenceTable;

import java.util.List;
import java.util.Map;


public interface ReferenceQuerySideRepository {


    Map<String, ReferenceTable> save(String id, ReferenceTable referenceData);

    Map<String, ReferenceTable> update(String id, ReferenceTable referenceData);


    List<String> getAllIds();

    String getRefIdByName(String name);

    void inActive(String id);
}
