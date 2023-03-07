package com.example.firing;

import java.util.ArrayList;

public class searchModel {
    private ArrayList<Imagemodel> photos;

    public searchModel(ArrayList<Imagemodel> photos) {
        this.photos = photos;
    }

    public ArrayList<Imagemodel> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Imagemodel> photos) {
        this.photos = photos;
    }


}
