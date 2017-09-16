package com.networknt.eventuate.reference;



/**
 * A User setting configuration file. It get from defined resource yml file
 *
 */
public class ReferenceConfig {

    private boolean useCache;


    public ReferenceConfig() {
    }

    public boolean isUseCache() {
        return useCache;
    }

    public void setUseCache(boolean useCache) {
        this.useCache = useCache;
    }
}
