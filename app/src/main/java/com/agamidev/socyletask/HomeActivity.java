package com.agamidev.socyletask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {


    TextView tvHello;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvHello = (TextView)findViewById(R.id.tvHEllo);

//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        if(bundle != null){
//            String Name = (String) bundle.get("Username");
//            tvHello.setText("HEllo "+Name);
//        }
    }
}
