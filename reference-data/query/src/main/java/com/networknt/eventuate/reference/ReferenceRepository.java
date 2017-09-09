package com.networknt.eventuate.reference;

import com.networknt.eventuate.reference.common.model.ReferenceTable;

import java.util.List;
import java.util.Map;


public interface ReferenceRepository {

    List<Map<String, ReferenceTable>> getAll();

    Map<String, ReferenceTable> findById(String id);

    Map<String, ReferenceTable> findByName(String name);

    Map<String, ReferenceTable> save(String id, ReferenceTable referenceData);

    void inActive(String id);
}
