package com.example.batch1807c1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText edtxt_username=(EditText) findViewById(R.id.username);
        EditText edtxt_password=(EditText) findViewById(R.id.password);
        Button btn_signup=(Button) findViewById(R.id.button);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDataBaseClass obj=new MyDataBaseClass(getApplicationContext());
                if(edtxt_username.getText().equals("") || edtxt_password.getText().equals("")){
                    Toast.makeText(MainActivity.this, "Please Fill all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean ans= obj.InsertUsers(edtxt_username.getText().toString(),edtxt_password.getText().toString());
                    if(ans){
                        Toast.makeText(MainActivity.this, "Your Account Has Been Created", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,Login.class));
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Insertion Failed", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}