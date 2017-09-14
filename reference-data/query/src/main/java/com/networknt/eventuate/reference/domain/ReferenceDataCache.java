package com.networknt.eventuate.reference.domain;

import com.networknt.eventuate.reference.common.model.ReferenceTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by gavin on 2017-09-13.
 */
public class ReferenceDataCache {

    private static ReferenceDataCache instance;

    private ReferenceDataCache(){}

    //Local cache for reference data, structure: host->Map(refTableName->ReferenceTable)
    private Map<String, List<ReferenceTable>> referenceDataMap = new ConcurrentHashMap<String, List<ReferenceTable>>();

    public static synchronized ReferenceDataCache getInstance(){
        if(instance == null){
            instance = new ReferenceDataCache();
        }
        return instance;
    }

    public  boolean hasRefTablesForHost(String host) {
        if (referenceDataMap.get(host) != null  && referenceDataMap.get(host).size()>1) {
            return true;
        } else {
            return false;
        }
    }

    public  List<ReferenceTable> getAllRefByHost(String host) {
        return referenceDataMap.get(host);
    }

    public  void putHostRefTables(String host, List<ReferenceTable> refs) {
      referenceDataMap.put(host, refs);
    }

    public  ReferenceTable getRefTableByName(String host, String name) {
        for (ReferenceTable referenceTable:referenceDataMap.get(host)) {
            if (name.equalsIgnoreCase(referenceTable.getTableName())) {
                return referenceTable;
            }
        }

        return null;
    }

    public  void addHostRefTable(String host, ReferenceTable ref) {
        if (referenceDataMap.get(host)!=null) {
            referenceDataMap.get(host).add(ref);
        } else {
            List<ReferenceTable> refs = new ArrayList<ReferenceTable>();
            refs.add(ref);
            referenceDataMap.put(host, refs);
        }
    }

    public  ReferenceTable getRefTableById(String id) {

        for (Map.Entry<String, List<ReferenceTable>> entry : referenceDataMap.entrySet()) {
            List<ReferenceTable> refList = entry.getValue();
            for (ReferenceTable reference:refList) {
                if (id.equals(reference.getTableId()))  {
                   return reference;
                }
            }
        }
        return null;
    }

}
