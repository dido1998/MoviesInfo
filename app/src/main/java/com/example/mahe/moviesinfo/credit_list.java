package com.example.mahe.moviesinfo;

import android.graphics.Bitmap;

/**
 * Created by Mahe on 6/7/2017.
 */

public class credit_list {
    String name ;
    String character;
    String image_path;
    public credit_list(String n,String c,String b)
    {
        name=n;
        character=c;
        image_path=b;
    }
    public String getName()
    {
        return name;
    }
    public String getCharacter()
    {
        return character;
    }
    public String getImage()
    {
        return image_path;
    }
}
