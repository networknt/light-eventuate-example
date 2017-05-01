package com.networknt.command.handler;

import com.networknt.client.Client;
import com.networknt.eventuate.common.impl.JSonMapper;
import com.networknt.eventuate.todolist.common.model.TodoInfo;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * Created by gavin on 2017-05-01.
 */
public class CommandSideMainTest {

    public static void main (String... args) {
        HttpPost httpPost = new HttpPost("http://localhost:8081/v1/todos");

       TodoInfo todo = new TodoInfo();
        todo.setTitle(" this is the test todo from main test class");
        String json = JSonMapper.toJson(todo);
     //   Client.getInstance().addAuthorization(httpPost);
        try {
            Client client = Client.getInstance();
            httpPost.setEntity(new StringEntity(json));
            httpPost.setHeader("Content-type", "application/json");
          //  client.populateHeader(httpPost, null, null, null);


            CloseableHttpResponse response = client.getSyncClient().execute(httpPost);
            System.out.println( response.getStatusLine().getStatusCode());
            System.out.println( IOUtils.toString(response.getEntity().getContent(), "utf8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
