package com.example.batch1807c1;

public class Course_getter {
    String coursename;
    String courseimage;

    public Course_getter(String coursename, String courseimage) {
        this.coursename = coursename;
        this.courseimage = courseimage;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getCourseimage() {
        return courseimage;
    }

    public void setCourseimage(String courseimage) {
        this.courseimage = courseimage;
    }
}