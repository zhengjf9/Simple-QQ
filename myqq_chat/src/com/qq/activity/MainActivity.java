package com.qq.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.R.integer;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener; 


public class MainActivity extends ActionBarActivity {
    private Button loginBt;
    private Button  registerBt;
    private EditText qqNo,qqPsw;
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    private String jsonData;
    private String message;
    private int success;
    public static String user_name;
    public static String BaseURL = "http://localhost/myqq/";
    
    private static String url_register = BaseURL + "register.php";
    private static String url_login = BaseURL + "login.php";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qqlogin);
        registerBt = (Button) findViewById(R.id.register_button);
        qqNo = (EditText) findViewById(R.id.login_edit_account);
        qqNo.requestFocus();
        qqPsw = (EditText ) findViewById(R.id.login_edit_pwd);
        registerBt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(qqNo.getText().toString().equals("") || qqPsw.getText().toString().equals("")) {
					Toast toast = Toast.makeText(getApplicationContext(),"«Î ‰»Î’À∫≈√‹¬Î", Toast.LENGTH_SHORT);
					toast.show();
				} else {
					new Register().execute();
				}
				
			}
		});
        loginBt =(Button) findViewById(R.id.login_button);
        loginBt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(qqNo.getText().toString().equals("") || qqPsw.getText().toString().equals("")) {
					Toast toast = Toast.makeText(getApplicationContext(),"«Î ‰»Î’À∫≈√‹¬Î", Toast.LENGTH_SHORT);
					toast.show();
				} else {
					new Login().execute();
				}
				
			}
		});
    }
   class Register extends AsyncTask<String, String, String>{
	   @Override 
	   protected void onPreExecute(){
		   super.onPreExecute();
		   pDialog = new ProgressDialog(MainActivity.this);
		   pDialog.setMessage("’˝‘⁄◊¢≤·..");
		   pDialog.setIndeterminate(false);
		   pDialog.setCancelable(true);
		   pDialog.show();
	   }

	   @Override
	   protected String doInBackground(String... arg0) {
	      List<NameValuePair> params = new ArrayList<NameValuePair>();
	      params.add(new BasicNameValuePair("user_name", qqNo.getText().toString()));
	      params.add(new BasicNameValuePair("user_passwd", qqPsw.getText().toString()));
	      try{
	    	  jsonData = jsonParser.makeHttpRequest(url_register, "POST", params);
	      } catch (Exception e) {
	    	  e.printStackTrace();
	      }
	      try {
	    	  JSONObject jsonObject = new JSONObject(jsonData);
	    	  message = jsonObject.getString("message");
	    	  success = jsonObject.getInt("success");
	      } catch (JSONException e){
	    	  Log.e("log_tag","Error parsing data" + e.toString());
	      }
	      return null;
	      }
	
	   protected void onPostExecuted(String file_url){
	    	  pDialog.dismiss();
	    	  String str = "" + success;
	    	  Toast toast = Toast.makeText(getApplicationContext(), "∑µªÿ¬Î" + str + ":" + message, Toast.LENGTH_LONG);
	          toast.show();
	   }
   }
    
   class Login extends AsyncTask<String, String, String>{
	   @Override 
	   protected void onPreExecute(){
		   super.onPreExecute();
		   pDialog = new ProgressDialog(MainActivity.this);
		   pDialog.setMessage("’˝‘⁄µ«¬º..");
		   pDialog.setIndeterminate(false);
		   pDialog.setCancelable(true);
		   pDialog.show();
	   }

	   @Override
	   protected String doInBackground(String... arg0) {
	      List<NameValuePair> params = new ArrayList<NameValuePair>();
	      params.add(new BasicNameValuePair("user_name", qqNo.getText().toString()));
	      params.add(new BasicNameValuePair("user_passwd", qqPsw.getText().toString()));
	      try{
	    	  jsonData = jsonParser.makeHttpRequest(url_login, "POST", params);
	      } catch (Exception e) {
	    	  e.printStackTrace();
	      }
	      try {
	    	  JSONObject jsonObject = new JSONObject(jsonData);
	    	  message = jsonObject.getString("message");
	    	  success = jsonObject.getInt("success");
	      } catch (JSONException e){
	    	  Log.e("log_tag", "Error parsing data" + e.toString());
	      }
	      return null;
	      }
	
	   protected void onPostExecuted(String file_url){
	    	  pDialog.dismiss();
	    	  String str = "" + success;
	    	  Toast toast = Toast.makeText(getApplicationContext(), "∑µªÿ¬Î" + str + ":" + message, Toast.LENGTH_LONG);
	          toast.show();
	          if(success == 1){
	        	  user_name = qqNo.getText().toString();
	        	  //Intent intent = new Intent(MainActivity.this, QqmainActivity.class);
	        	  //startActivity(intent);
	          }
	   }
   }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
