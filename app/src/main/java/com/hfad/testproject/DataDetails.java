package com.hfad.testproject;

import java.io.Serializable;

/**
 * Created by CHITRANGI on 11/22/2017.
 */

public class DataDetails implements Serializable{
    public String data_text1;
    public String data_text2;
    public int data_image;

    public DataDetails(String text1, String text2, int image){
        this.data_text1=text1;
        this.data_text2=text2;
        this.data_image=image;
    }
}
