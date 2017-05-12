package com.networknt.eventuate.reference;

import com.networknt.eventuate.reference.common.model.ReferenceData;


import java.util.List;
import java.util.Map;


public interface ReferenceRepository {

    List<Map<String, ReferenceData>> getAll();

    Map<String, ReferenceData> findById(String id);

    Map<String, ReferenceData> findByName(String name);

    Map<String, ReferenceData> save(String id, ReferenceData referenceData);

    void inActive(String id);
}
