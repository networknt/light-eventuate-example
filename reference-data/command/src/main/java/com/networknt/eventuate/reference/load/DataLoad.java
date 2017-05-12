package com.networknt.eventuate.reference.load;

import com.networknt.eventuate.reference.common.model.ReferenceData;

import java.util.List;



public interface DataLoad {

    ReferenceData loadByReferenceName(String refName);

    List<ReferenceData> loadReferenceList(List<String> refNameList);

    List<ReferenceData> loadFromDestination(String destination);

}
