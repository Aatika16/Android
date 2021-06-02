package com.example.batch1807c1;

public class Course_getter {
    String id;
    String coursename;
    byte[] courseimage;

    public Course_getter(String id,String coursename,  byte[] courseimage) {
        this.id = id;
        this.coursename = coursename;
        this.courseimage = courseimage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public  byte[] getCourseimage() {
        return courseimage;
    }

    public void setCourseimage( byte[] courseimage) {
        this.courseimage = courseimage;
    }
}
