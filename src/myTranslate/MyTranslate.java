/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myTranslate;

import java.io.IOException;
import java.io.InputStream;
import static myTranslate.TranslateUtil.ENCODING;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author _pianoflu
 */
public class MyTranslate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
    
        TranslateUtil.cn2en("一叶知秋");
        System.out.println(TranslateUtil.cn2en("一叶知秋"));
    }
    
}
