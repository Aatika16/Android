package com.example.batch1807c1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class customAdapter extends BaseAdapter {
    Context c;
    ArrayList<Course_getter> arr;
    LayoutInflater lf;

    public customAdapter(Context c, ArrayList<Course_getter> arr) {
        this.c = c;
        this.arr = arr;
        this.lf = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=View.inflate(c,R.layout.course_resourse,null);
        TextView tv_csname=convertView.findViewById(R.id.cs_name);
        TextView tv_csiamge=convertView.findViewById(R.id.cs_iamge);
        ImageView edit=convertView.findViewById(R.id.c_edit);
        ImageView delete=convertView.findViewById(R.id.c_delt);
       //assiging tag
        delete.setTag(position);
        Course_getter obj=arr.get(position);
        tv_csname.setText(obj.getCoursename());
        tv_csiamge.setText(obj.getCourseimage());
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View x) {
                MyDataBaseClass ob=new MyDataBaseClass(c);
                boolean ans=ob.DeleteCourse(obj.getId());
                if(ans){
                    //tag ki value index mai save kara rahia hai
                    Integer index = (Integer) x.getTag();
                    arr.remove(index.intValue());
                    notifyDataSetChanged();
                    Toast.makeText(c, "Data Deleted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(c, "Sorry! Some Error Occured", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return convertView;
    }
}
