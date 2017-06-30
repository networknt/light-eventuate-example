
package com.networknt.eventuate.account.command.handler;

import com.networknt.client.Client;
import com.networknt.eventuate.account.common.model.account.CreateAccountRequest;
import com.networknt.eventuate.common.impl.JSonMapper;
import com.networknt.exception.ClientException;
import com.networknt.exception.ApiException;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;


public class OpenAccountPostHandlerTest {
    @ClassRule
    public static TestServer server = TestServer.getInstance();

    static final Logger logger = LoggerFactory.getLogger(OpenAccountPostHandlerTest.class);

    @Test
    public void testOpenAccountPostHandlerTest() throws ClientException, ApiException {
        CloseableHttpClient client = Client.getInstance().getSyncClient();
        HttpPost httpPost = new HttpPost ("http://localhost:8081/v1/openaccount");
        CreateAccountRequest accountReq = new CreateAccountRequest();
        accountReq.setCustomerId("1111-2222");
        accountReq.setTitle("RRSP account");
        accountReq.setDescription("account for testing");
        accountReq.setInitialBalance(new BigDecimal(12355L));


        String json = JSonMapper.toJson(accountReq);
        System.out.println(json);

      //  Client.getInstance().addAuthorization(httpPost);
        try {
            httpPost.setEntity(new StringEntity(json));
            httpPost.setHeader("Content-type", "application/json");
            CloseableHttpResponse response = client.execute(httpPost);
            Assert.assertEquals(200, response.getStatusLine().getStatusCode());
            System.out.println("result:" + IOUtils.toString(response.getEntity().getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
