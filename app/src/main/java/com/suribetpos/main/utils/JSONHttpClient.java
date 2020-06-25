/*
package com.suribetpos.main.utils;

import android.util.Log;

import com.google.gson.GsonBuilder;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class JSONHttpClient {

    public <T> T PostObjectStr(final String url, final Object object, final Class<T> objectClass) {
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        try {
            StringEntity stringEntity = new StringEntity(new GsonBuilder().create().toJson(object));
            httpPost.setEntity(stringEntity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("Accept-Encoding", "gzip");

            HttpResponse httpResponse = defaultHttpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                InputStream inputStream = httpEntity.getContent();
                Header contentEncoding = httpResponse.getFirstHeader("Content-Encoding");
                if (contentEncoding != null && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
                    inputStream = new GZIPInputStream(inputStream);
                }
                String resultString = convertStreamToString(inputStream);
                //Log.e(">>>>>>>>>", "resultString>>>" + resultString);
                // resultString = resultString.replace("\n", "");
                inputStream.close();
                return new GsonBuilder().create().fromJson(resultString, objectClass);
            }
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClientProtocolException ex) {
            ex.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException ex) {
            ex.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    public <T> T PostObject(final String url, final T object, final Class<T> objectClass) {
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        try {
            StringEntity stringEntity = new StringEntity(new GsonBuilder().create().toJson(object));
            Log.e(">>>>>>>>>", "stringEntity" +new GsonBuilder().create().toJson(object));
            httpPost.setEntity(stringEntity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("Accept-Encoding", "gzip");

            HttpResponse httpResponse = defaultHttpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                InputStream inputStream = httpEntity.getContent();
                Header contentEncoding = httpResponse.getFirstHeader("Content-Encoding");
                if (contentEncoding != null && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
                    inputStream = new GZIPInputStream(inputStream);
                }
                String resultString = convertStreamToString(inputStream);
                //Log.e(">>>>>>>>>", "resultString>>>" + resultString);
                // resultString = resultString.replace("\n", "");
                inputStream.close();
                return new GsonBuilder().create().fromJson(resultString, objectClass);
            }
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClientProtocolException ex) {
            ex.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException ex) {
            ex.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    public <T> T PostObject_Json(final String url, final T object, final Class<T> objectClass) {
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        try {
            StringEntity stringEntity = new StringEntity(new GsonBuilder().create().toJson(object));
            Log.e(">>>>>>>>>", "stringEntity---" + stringEntity);
            httpPost.setEntity(stringEntity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("Accept-Encoding", "gzip");

            HttpResponse httpResponse = defaultHttpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                InputStream inputStream = httpEntity.getContent();
                Header contentEncoding = httpResponse.getFirstHeader("Content-Encoding");
                if (contentEncoding != null && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
                    inputStream = new GZIPInputStream(inputStream);
                }
                Common.JsonParsingStr = convertStreamToString(inputStream);
                //Log.e(">>>>>>>>>", "resultString-json>>>" + Common.JsonParsingStr);
                // resultString = resultString.replace("\n", "");
                inputStream.close();
                //return null;
                return new GsonBuilder().create().fromJson(Common.JsonParsingStr, objectClass);
            }
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClientProtocolException ex) {
            ex.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException ex) {
            ex.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    public <T> T PostParams(String url, final List<NameValuePair> params, final Class<T> objectClass) {
        String paramString = URLEncodedUtils.format(params, "utf-8");
        url += "?" + paramString;
        return PostObject(url, null, objectClass);
    }

    private String convertStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        return stringBuilder.toString();
    }

    public <T> T Get(String url, List<NameValuePair> params, final Class<T> objectClass) {
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        String paramString = URLEncodedUtils.format(params, "utf-8");
        url += "?" + paramString;
        HttpGet httpGet = new HttpGet(url);
        try {

            httpGet.setHeader("Accept", "application/json");
            httpGet.setHeader("Accept-Encoding", "gzip");

            HttpResponse httpResponse = defaultHttpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                InputStream inputStream = httpEntity.getContent();
                Header contentEncoding = httpResponse.getFirstHeader("Content-Encoding");
                if (contentEncoding != null && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
                    inputStream = new GZIPInputStream(inputStream);
                }

                String resultString = convertStreamToString(inputStream);
                inputStream.close();
                return new GsonBuilder().create().fromJson(resultString, objectClass);

            }

        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClientProtocolException ex) {
            ex.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException ex) {
            ex.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    public boolean Delete(String url, final List<NameValuePair> params) {
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        String paramString = URLEncodedUtils.format(params, "utf-8");
        url += "?" + paramString;
        HttpDelete httpDelete = new HttpDelete(url);

        HttpResponse httpResponse = null;
        try {
            httpResponse = defaultHttpClient.execute(httpDelete);
            return httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_NO_CONTENT;
        } catch (IOException ex) {
            ex.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return false;
    }

}
*/
