package com.example.firing;

public class store_data {
    String name;
    String contact_no,place;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public  store_data(String name, String contact_no, String place){
         this.contact_no=contact_no;
         this.place=place;
         this.name=name;
     }
}
