package com.example.batch1807c1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
        Course_getter obj=arr.get(position);
        tv_csname.setText(obj.getCoursename());
        tv_csiamge.setText(obj.getCourseimage());
        return convertView;
    }
}
