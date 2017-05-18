/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package exmaples;

import java.io.BufferedInputStream;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * How to send a request via proxy.
 *
 * @since 4.0
 */
public class ClientExecuteProxy {

    protected static final String ID_RESULTBOX = "result_box";
    protected static final String ENCODING = "UTF-8";

    public static void main(String[] args) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpEntity entity = null;
        InputStream result = null;
        Document doc = null;
        Element ele = null;

        try {
            //HttpHost target = new HttpHost("translate.google.com/?langpair=zh-CN%7Cen&text=%E7%BE%8E%E5%9B%BD", 80, "http");
            HttpHost target = new HttpHost("http://translate.google.com/?langpair=zh-CN%7Cen&text=%E7%BE%8E%E5%9B%BD");
            target.toString();
            HttpHost proxy = new HttpHost("127.0.0.1", 1080, "http");

            RequestConfig config = RequestConfig.custom()
                    .setProxy(proxy)
                    .build();
            HttpGet request = new HttpGet("http://translate.google.com/?langpair=zh-CN%7Cen&text=%E7%BE%8E%E5%9B%BD");
            request.setConfig(config);

            System.out.println("Executing request " + request.getRequestLine() + " to " + target + " via " + proxy);

            CloseableHttpResponse response = httpclient.execute(target, request);
            entity = response.getEntity();
            entity = new BufferedHttpEntity(entity);
            result = new BufferedInputStream(entity.getContent());
            try {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                //System.out.println(EntityUtils.toString(response.getEntity()));
                System.out.println(result);
                doc = Jsoup.parse(result, ENCODING, "");
                ele = doc.getElementById(ID_RESULTBOX);
                System.out.println("----------------------------------------");
                System.out.println(ele.text());
                System.out.println("----------------------------------------");
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

}
