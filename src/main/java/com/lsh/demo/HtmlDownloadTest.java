package com.lsh.demo;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

/**
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-17
 */
public class HtmlDownloadTest {

    public static void main(String[] args) throws Exception {
        String address = "https://www.google.com";

        URL url = new URL(address);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8787));
        HttpsURLConnection httpConn = (HttpsURLConnection) url.openConnection(proxy);

        if (httpConn.getResponseCode() == 200) {
            BufferedReader httpContentReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));
            StringBuffer strB = new StringBuffer();
            String patch = null;
            while ((patch = httpContentReader.readLine()) != null) {
                System.out.println(patch);
                strB.append(patch);
            }

        }

    }

}
