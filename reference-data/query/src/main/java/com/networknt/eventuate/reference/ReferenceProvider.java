package com.networknt.eventuate.reference;

import com.networknt.eventuate.reference.common.model.ReferenceTable;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Provide reference data.
 */
public interface ReferenceProvider {

    CompletableFuture<Map<String, ReferenceTable>> getReferenceById (String id);

}
