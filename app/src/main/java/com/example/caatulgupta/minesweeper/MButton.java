package com.example.caatulgupta.minesweeper;

import android.content.Context;
import android.widget.Button;

import java.time.chrono.MinguoChronology;

public class MButton extends Button {

    private int value = MainActivity.BLANK;
    public boolean visited = false;
    public boolean blankVisited = false;
    public int i;
    public int j;

    public MButton(Context context) {
        super(context);
    }

    public void setValue(int value){
//        if(value == MainActivity.MINE){
//            this.value = -1;
//            setText(toString().valueOf(value));
//        }else if(value == MainActivity.BLANK){
//            this.value = 0;
//            setText(toString().valueOf(value));
//        }else{
            this.value = value;
            //setText(toString().valueOf(value));
//        }

    }

    public int getValue(){
        return value;
    }


    public void flag(){
        setEnabled(false);
        setText("R");
    }

}
