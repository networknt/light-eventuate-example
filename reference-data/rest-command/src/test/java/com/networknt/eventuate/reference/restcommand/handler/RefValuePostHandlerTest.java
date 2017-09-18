
package com.networknt.eventuate.reference.restcommand.handler;

import com.networknt.client.Http2Client;
import com.networknt.eventuate.common.impl.JSonMapper;
import com.networknt.eventuate.reference.common.model.Language;
import com.networknt.eventuate.reference.common.model.ReferenceTable;
import com.networknt.eventuate.reference.common.model.ReferenceValue;
import com.networknt.eventuate.reference.common.model.ValueLocale;
import com.networknt.exception.ApiException;
import com.networknt.exception.ClientException;
import com.networknt.service.SingletonServiceFactory;
import io.undertow.UndertowOptions;
import io.undertow.client.ClientConnection;
import io.undertow.client.ClientRequest;
import io.undertow.client.ClientResponse;
import io.undertow.util.Headers;
import io.undertow.util.Methods;
import org.h2.tools.RunScript;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xnio.IoUtils;
import org.xnio.OptionMap;

import javax.sql.DataSource;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import java.io.IOException;


public class RefValuePostHandlerTest {
    @ClassRule
    public static TestServer server = TestServer.getInstance();

    static final Logger logger = LoggerFactory.getLogger(RefValuePostHandlerTest.class);
    static final boolean enableHttp2 = server.getServerConfig().isEnableHttp2();
    static final boolean enableHttps = server.getServerConfig().isEnableHttps();
    static final int httpPort = server.getServerConfig().getHttpPort();
    static final int httpsPort = server.getServerConfig().getHttpsPort();
    static final String url = enableHttp2 || enableHttps ? "https://localhost:" + httpsPort : "http://localhost:" + httpPort;

    public static DataSource ds;
    static {
        ds = (DataSource) SingletonServiceFactory.getBean(DataSource.class);
        try (Connection connection = ds.getConnection()) {
            // Runscript doesn't work need to execute batch here.
            String schemaResourceName = "/embedded-event-store-schema.sql";
            InputStream in = RefTablePostHandlerTest.class.getResourceAsStream(schemaResourceName);

            if (in == null) {
                throw new RuntimeException("Failed to load resource: " + schemaResourceName);
            }
            InputStreamReader reader = new InputStreamReader(in);
            RunScript.execute(connection, reader);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRefValuePostHandlerTest() throws ClientException, ApiException {

        final Http2Client client = Http2Client.getInstance();
        final CountDownLatch latch = new CountDownLatch(1);
        final ClientConnection connection;
        try {
            connection = client.connect(new URI(url), Http2Client.WORKER, Http2Client.SSL, Http2Client.POOL, enableHttp2 ? OptionMap.create(UndertowOptions.ENABLE_HTTP2, true): OptionMap.EMPTY).get();
        } catch (Exception e) {
            throw new ClientException(e);
        }
        final AtomicReference<ClientResponse> reference = new AtomicReference<>();

        ReferenceValue ref = new ReferenceValue();
        ref.setTableId("222-33333-9988");
        ref.setValueCode("ccccccccc");
        ref.setDisplayOrder(1);
        ref.setStartTime(Timestamp.valueOf(LocalDateTime.now()));
        ref.setActive(true);

        ValueLocale localeValue = new ValueLocale();
        localeValue.setLanguage(Language.En);
        localeValue.setValueDesc("test value");

        ref.addLocale(localeValue);

        String json = JSonMapper.toJson(ref);


    //    String json = "{\"tableId\":\"222-33333-9988\",\"valueCode\":\"ccccccccc\",\"startTime\":\"2014-01-01T23:28:56.782Z\",\"displayOrder\":1,\"active\":true,\"locales\":[],\"relations\":[],\"startTimestamp\":1505493323873}";

        System.out.println(json);

        try {
            ClientRequest request = new ClientRequest().setPath("/v1/refValue").setMethod(Methods.POST);
            
            request.getRequestHeaders().put(Headers.CONTENT_TYPE, "application/json");
            request.getRequestHeaders().put(Headers.TRANSFER_ENCODING, "chunked");
            connection.sendRequest(request, client.createClientCallback(reference, latch, json));
            
            latch.await();
        } catch (Exception e) {
            logger.error("Exception: ", e);
            throw new ClientException(e);
        } finally {
            IoUtils.safeClose(connection);
        }
        int statusCode = reference.get().getResponseCode();
        String body = reference.get().getAttachment(Http2Client.RESPONSE_BODY);
        System.out.println("result:" + body);
        Assert.assertEquals(200, statusCode);
        Assert.assertNotNull(body);

    }
}
