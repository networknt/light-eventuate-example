package com.networknt.command.handler;

import com.networknt.server.Server;
import org.junit.rules.ExternalResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCommandServer extends ExternalResource {
    static final Logger logger = LoggerFactory.getLogger(TestCommandServer.class);

    private static volatile int refCount = 0;
    private static Server server;

    private static final TestCommandServer instance  = new TestCommandServer();

    public static TestCommandServer getInstance () {
        return instance;
    }

    private TestCommandServer() {

    }

    protected void before() {
        try {
            if (refCount == 0) {
                System.out.println("TestCommandServer starting...");
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
            System.out.println("TestCommandServer stopping...");
            server.stop();
        }
    }

}
