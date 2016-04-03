package com.example.prayag.forecastsearch;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by prayag on 12/6/15.
 */


public class HttpGetter {

    private static final byte[] out = new byte[4096];

    public static String down(String urlParams) throws IOException{
        URL url = new URL(urlParams);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.connect();

        InputStream in = conn.getInputStream();
        ByteArrayOutputStream yo = new ByteArrayOutputStream();

        int c;
        while((c = in.read(out))!= -1) yo.write(out,0,c);
        return new String(yo.toByteArray());

    }
}
