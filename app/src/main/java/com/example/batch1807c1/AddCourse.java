package com.example.batch1807c1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddCourse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        EditText edtxt_coursename = (EditText) findViewById(R.id.course_name);
        EditText edtxt_course_image = (EditText) findViewById(R.id.course_image);
        Button btn_course_insert = (Button) findViewById(R.id.add_course);
        Button btn_course_edit = (Button) findViewById(R.id.edit_course);
        Button btn_course_show = (Button) findViewById(R.id.show_courses);
        ListView lv = (ListView) findViewById(R.id.course_lv);
        MyDataBaseClass db = new MyDataBaseClass(getApplicationContext());
        String pg_name=getIntent().getStringExtra("pagename");
        if(pg_name==null){
            Toast.makeText(this, "Insert wala", Toast.LENGTH_SHORT).show();
            btn_course_insert.setVisibility(View.VISIBLE);
            btn_course_edit.setVisibility(View.GONE);
            btn_course_insert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Boolean res = db.InsertCourse(edtxt_coursename.getText().toString(), edtxt_course_image.getText().toString());
                    if (res) {
                        Toast.makeText(AddCourse.this, "Course Inserted Successfully", Toast.LENGTH_SHORT).show();
                        edtxt_course_image.setText("");
                        edtxt_coursename.setText("");
                    } else {
                        Toast.makeText(AddCourse.this, "Insertion Failed please try again", Toast.LENGTH_SHORT).show();
                        edtxt_course_image.setText("");
                        edtxt_coursename.setText("");
                    }
                }
            });
        }
        else if(pg_name.equals("edit")){
            Toast.makeText(this, "update wala", Toast.LENGTH_SHORT).show();
            edtxt_coursename.setText(getIntent().getStringExtra("name"));
            edtxt_course_image.setText(getIntent().getStringExtra("image"));
            btn_course_insert.setVisibility(View.GONE);
            btn_course_edit.setVisibility(View.VISIBLE);
            btn_course_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   boolean res= db.UpdateCourse(getIntent().getStringExtra("Id"),edtxt_coursename.getText().toString(),edtxt_course_image.getText().toString());
                   if(res){
                       Toast.makeText(AddCourse.this, "Data Updated", Toast.LENGTH_SHORT).show();
                   }
                   else{
                       Toast.makeText(AddCourse.this, "Updation Failed", Toast.LENGTH_SHORT).show();
                   }
                }
            });
        }

        btn_course_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Course_getter>  sc_list=new ArrayList<Course_getter>();
               //to refresh list view
                lv.setAdapter(null);
                sc_list.clear();
                Cursor data = db.Show_courses();
                if (data.getCount() == 0) {
                    Toast.makeText(AddCourse.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                } else {
                    while(data.moveToNext()){
                        String cs_id=data.getString(0);
                        String cs_name=data.getString(1);
                        String cs_image=data.getString(2);
                        sc_list.add(new Course_getter(cs_id,cs_name,cs_image));
                    }
                    customAdapter cs=new customAdapter(getApplicationContext(),sc_list);
                    lv.setAdapter(cs);

                }
            }
        });
    }
}