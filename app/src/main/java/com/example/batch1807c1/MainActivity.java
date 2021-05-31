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
        edtxt_username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                validations validation = new validations();
                String name=edtxt_username.getText().toString();
                if(!hasFocus){
                    if (name.isEmpty()){
                        edtxt_username.setError("Enter Your Name");
                    }
                    else if (!validation.fullname(name)){
                        edtxt_username.setError("atleast 3 digist must be there");
                    }
                    else  {
                        edtxt_username.setCompoundDrawablesWithIntrinsicBounds(0, 0,R.drawable.ic_baseline_check_circle_24, 0);
                    }
                }

            }
        });


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDataBaseClass obj=new MyDataBaseClass(getApplicationContext());
                if(edtxt_username.getText().equals("") || edtxt_password.getText().equals("")){
                    Toast.makeText(MainActivity.this, "Please Fill all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean ans= obj.InsertUsers(edtxt_username.getText().toString(),edtxt_password.getText().toString(),"Users");
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