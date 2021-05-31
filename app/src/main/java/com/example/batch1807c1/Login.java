package com.example.batch1807c1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
static final String sharedPref="SharedPref";
static final String sp_name="Username";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText edtxt_username_lg=(EditText) findViewById(R.id.lg_username);
        EditText edtxt_password_lg=(EditText) findViewById(R.id.lg_password);
        Button btn_login=(Button) findViewById(R.id.login);
        Button btn_sign=(Button) findViewById(R.id.signup);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
MyDataBaseClass db=new MyDataBaseClass(getApplicationContext());
Cursor res=db.login(edtxt_username_lg.getText().toString(),edtxt_password_lg.getText().toString());
if(res.getCount()==0){
    Toast.makeText(Login.this, "Sorry! Login Failed", Toast.LENGTH_SHORT).show();
}
else{
    SharedPreferences sharedPreferences=getSharedPreferences(sharedPref,MODE_PRIVATE);
    SharedPreferences.Editor eed=sharedPreferences.edit();
    eed.putString(sp_name,edtxt_username_lg.getText().toString());
    eed.apply();
    while(res.moveToNext()){
        String role_name=res.getString(3);
        if(role_name.equals("Admin")){
            Toast.makeText(Login.this, "Congratulations! You are now Logged in", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Login.this,AdminDashbaord.class));
        }
        else{
            Toast.makeText(Login.this, "Congratulations! You are now Logged in", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Login.this,Dashboard.class));
        }
    }

}
            }
        });
        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}