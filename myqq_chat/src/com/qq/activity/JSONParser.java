package com.qq.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import android.util.Log;

public class JSONParser {
      static InputStream is = null;
      static String json = "";
      public static String PHPSESSID = null;
      public JSONParser(){
      }
      public String makeHttpRequest(String url, String method, List<NameValuePair> params){
    	  try{
    		  HttpPost httpPost = new HttpPost(url);
    		  httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
    		  if (null != PHPSESSID){
    			  httpPost.setHeader("Cookie", "PHPSESSID=" + PHPSESSID);
    		  }
    		  DefaultHttpClient httpClient = new DefaultHttpClient();
    		  HttpResponse httpResponse = httpClient.execute(httpPost);
    		  HttpEntity httpEntity = httpResponse.getEntity();
    		  is = httpEntity.getContent();
    		  CookieStore mCookieStore = httpClient.getCookieStore();
    		  List<Cookie> cookies = mCookieStore.getCookies();
    		  for (int i = 0; i < cookies.size(); i++){
    			  if("PHPSESSID".equals(cookies.get(i).getName())){
    				  PHPSESSID = cookies.get(i).getValue();
    				  break;
    			  }
    		  }
    	  } catch (UnsupportedEncodingException e){
    		  e.printStackTrace();
    	  } catch (ClientProtocolException e){
    		  e.printStackTrace();
    	  } catch (IOException e){
    		  e.printStackTrace();
    	  }
    	  try{
    		  BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
    		  StringBuilder sb = new StringBuilder();
    		  String line = null;
    		  while((line = reader.readLine()) != null){
    			  sb.append(line + "\n");
    		  }
    		  is.close();
    		  json = sb.toString();
    	  } catch (Exception e){
    		  Log.e("Buffer Error", "Error converting result " + e.toString());
    		  Log.d("json", json.toString());
    	  }
    	  return json;
      }
}
