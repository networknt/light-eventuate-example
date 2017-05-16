package com.networknt.eventuate.reference;

import com.networknt.eventuate.reference.common.model.ReferenceData;

import java.util.List;
import java.util.Map;


public interface ReferenceQuerySideRepository {


    Map<String, ReferenceData> save(String id, ReferenceData referenceData);

    Map<String, ReferenceData> update(String id, ReferenceData referenceData);


    List<String> getAllIds();

    String getRefIdByName(String name);

    void inActive(String id);
}
