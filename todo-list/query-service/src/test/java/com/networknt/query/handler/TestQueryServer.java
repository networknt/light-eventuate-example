package com.networknt.query.handler;

import com.networknt.server.Server;
import org.junit.rules.ExternalResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestQueryServer extends ExternalResource {
    static final Logger logger = LoggerFactory.getLogger(TestQueryServer.class);

    private static volatile int refCount = 0;
    private static Server server;

    private static final TestQueryServer instance  = new TestQueryServer();

    public static TestQueryServer getInstance () {
        return instance;
    }

    private TestQueryServer() {

    }

    protected void before() {
        try {
            if (refCount == 0) {
                server.start();
            }
        }
        finally {
            refCount++;
        }
    }

    protected void after() {
        refCount--;
        if (refCount == 0) {
            server.stop();
        }
    }

}
