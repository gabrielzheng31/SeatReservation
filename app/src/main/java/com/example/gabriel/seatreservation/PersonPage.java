package com.example.gabriel.seatreservation;

/**
 * Created by gabriel on 17-12-5.
 */

public class PersonPage {

    private int name;
    private int imageId;

    public PersonPage(int name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public int getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
