package com.networknt.eventuate.reference;

import com.networknt.eventuate.reference.common.model.ReferenceData;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Provide reference data.
 */
public interface ReferenceProvider {

    CompletableFuture<Map<String, ReferenceData>> getReferenceById (String id);

}
