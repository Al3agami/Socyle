package com.agamidev.socyletask;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText etEmail;
    EditText etPassword;
    SQLiteDatabase myDatabase ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = (EditText)findViewById(R.id.etEmail);
        etPassword = (EditText)findViewById(R.id.etPassword);

        try{

            myDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);

            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR , email VARCHAR , password VARCHAR , phone INT(11))");



        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void registration(View view){
        Intent i = new Intent(MainActivity.this, registrationActivity.class);
        startActivity(i);
    }
    public void login(View view){

//        getInfo();

        boolean isValid = false;
        try{

            Cursor c = myDatabase.rawQuery("SELECT * FROM users" , null);

            int emailIndex = c.getColumnIndex("email");
            int passwordIndex = c.getColumnIndex("password");



            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            c.moveToFirst();

            while (c != null){

                if(email.equals(c.getString(emailIndex)) && password.equals(c.getString(passwordIndex))){
                    isValid = true;
                    Intent homeIntent = new Intent(MainActivity.this,HomeActivity.class);
//                    homeIntent.putExtra("Username",c.getString(nameIndex));
                    startActivity(homeIntent);
                    break;
                }else {
                    c.moveToNext();
                }
            }
            if (isValid == false) {
                Toast.makeText(MainActivity.this, "Invalid email or password!", Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
//    public void getInfo() {
//        String Url = "http://socyle.com/intership/index.php/login/?email="+etEmail.getText().toString()+"&password="
//                +etPassword.getText().toString();
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                JSONObject results = null;
//                if (response != null) {
//                    try {
//                        results = new JSONObject(response);
//                        int x=results.getInt("result");
//                        if(x==1){
//                            Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_LONG).show();
//                        }
//                        else{
//                            Toast.makeText(MainActivity.this, "Wrong username or password", Toast.LENGTH_LONG).show();
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }else{
//                    Toast.makeText(MainActivity.this, "No Data", Toast.LENGTH_LONG).show();
//                    return;
//                }
//            }
//
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
//
//            }
//
//
//        });
//        Volley.newRequestQueue(MainActivity.this).add(stringRequest);
//
//
//    }

}
