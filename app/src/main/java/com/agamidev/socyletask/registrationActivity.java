package com.agamidev.socyletask;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class registrationActivity extends AppCompatActivity {

    EditText etName;
    EditText etEmail;
    EditText etPassword;
    EditText etPhone;

    SQLiteDatabase myDatabase ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etName = (EditText)findViewById(R.id.etName);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPassword = (EditText)findViewById(R.id.etPassword);
        etPhone = (EditText)findViewById(R.id.etPhone);

        try{

            myDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR , email VARCHAR , password VARCHAR , phone INT(11))");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void dbInsertion(View view){

        String Name = etName.getText().toString();
        String Email = etEmail.getText().toString();
        String Password = etPassword.getText().toString();
        int Phone = Integer.parseInt(etPhone.getText().toString());

        if(etName.equals("") || etEmail.equals("") || etPassword.equals("") || etPhone.equals("")){
            Toast.makeText(registrationActivity.this, "Not Completed Info", Toast.LENGTH_SHORT).show();
        }else{
            myDatabase.execSQL("INSERT INTO users (name,email,password,phone) VALUES ('"+Name+"','"+Email+"','"+Password+"',"+Phone+")");
            Intent i = new Intent(registrationActivity.this, MainActivity.class);
            startActivity(i);
        }
    }
}
