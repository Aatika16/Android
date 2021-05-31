package com.example.batch1807c1;

public class validations {
    public boolean fullname(String name) {
        String namepattern = "^[A-Za-z]{3,}";
        if (name.matches(namepattern)) {
            return true;
        } else {
            return false;
        }
    }

}
