package com.networknt.eventuate.reference.load;

import com.networknt.eventuate.reference.common.model.ReferenceTable;

import java.util.List;



public interface DataLoad {

    ReferenceTable loadByReferenceName(String refName);

    List<ReferenceTable> loadReferenceList(List<String> refNameList);

    List<ReferenceTable> loadFromDestination(String destination);

}
