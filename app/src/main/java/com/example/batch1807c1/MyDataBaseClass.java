package com.example.batch1807c1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDataBaseClass extends SQLiteOpenHelper {
 static final String name="Institute.db";

static final String tb0_name="Role_Table";
static final String tb0_col1_Id="Id";
static final String tb0_col2_Rolename="Rolename";


 static final String tb1_name="Users";
 static final String tb1_col1_Id="Id";
 static final String tb1_col2_Username="Username";
 static final String tb1_col3_Password="Password";
 static final String tb1_col4_Role_name="Role_name";



    static final String tb2_name="Courses";
    static final String tb2_col1_Id="Id";
    static final String tb2_col2_Course_Name="Course_Name";
    static final String tb2_col3_Course_Image="Course_Image";

    public MyDataBaseClass(@Nullable Context context) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL("create table "+tb0_name+" ("+tb0_col1_Id+" Integer Primary key autoincrement, "+tb0_col2_Rolename+" text unique) ");
db.execSQL("create table "+tb1_name+" ("+tb1_col1_Id+" Integer Primary key autoincrement, "+tb1_col2_Username+" text unique, "+tb1_col3_Password+" text,"+tb1_col4_Role_name+" Integer, foreign key ("+tb1_col4_Role_name+") references "+tb0_name+" ("+tb0_col1_Id+")) ");
db.execSQL("create table "+tb2_name+" ("+tb2_col1_Id+" Integer Primary key autoincrement, "+tb2_col2_Course_Name+" text unique, "+tb2_col3_Course_Image+" text) ");

db.execSQL("insert into "+tb0_name+"(Rolename) values ('Admin')");
db.execSQL("insert into "+tb0_name+"(Rolename) values ('Users')");
db.execSQL("insert into "+tb1_name+"(Username,Password,Role_name) values ('Admin','Admin','Admin')");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("Drop table if exists "+tb1_name);
db.execSQL("Drop table if exists "+tb2_name);
    }

    public boolean InsertUsers(String User,String Pass,String fk_role){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(tb1_col2_Username,User);
        cv.put(tb1_col3_Password,Pass);
        cv.put(tb1_col4_Role_name,fk_role);
      long res= db.insert(tb1_name,null,cv);
      if(res==-1){
          return false;
      }
      else{
          return true;
      }
    }
    public boolean InsertCourse(String cs,String cs_image){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(tb2_col2_Course_Name,cs);
        cv.put(tb2_col3_Course_Image,cs_image);
        long res= db.insert(tb2_name,null,cv);
        if(res==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean DeleteCourse(String id){
        SQLiteDatabase db=getWritableDatabase();
        long res=db.delete(tb2_name,tb2_col1_Id+"=?",new String[]{id});
        if(res==-1)
            return false;
        else
            return true;
    }

    public boolean UpdateCourse(String id,String cs,String cs_image){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(tb2_col2_Course_Name,cs);
        cv.put(tb2_col3_Course_Image,cs_image);
        long res= db.update(tb2_name,cv,"Id = ?",new String[]{id});
        if(res==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor login(String UsernamE,String Password){
        SQLiteDatabase db=getWritableDatabase();
        Cursor data=db.rawQuery("select * from "+tb1_name+" where "+tb1_col2_Username+" = ? and "+tb1_col3_Password+" = ? ",new String[]{UsernamE,Password});
        return data;
    }
    public Cursor Show_courses(){
        SQLiteDatabase db=getWritableDatabase();
        Cursor data=db.rawQuery("select * from "+tb2_name,null);
        return data;
    }

    public boolean checking_unique(String Cs_name,String tbname){
        SQLiteDatabase db=getWritableDatabase();
        Cursor data=db.rawQuery("select * from "+tbname+" where "+tb2_col2_Course_Name+" = ? ",new String[]{Cs_name});
     if(data.getCount()>0){
         return true;
     }
     else{
         return false;
     }

    }
}
