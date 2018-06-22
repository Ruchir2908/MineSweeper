package com.example.caatulgupta.minesweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class FrontActivity extends AppCompatActivity {
    EditText et;
    RadioGroup rg;
    static RadioButton r1,r2,r3;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);
        et = findViewById(R.id.et);
        rg = findViewById(R.id.radio);
        r1 = findViewById(R.id.r1);
        r2 = findViewById(R.id.r2);
        r3 = findViewById(R.id.r3);
        b = findViewById(R.id.button);
    }

    public void startGame(View view){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("Name",et.getText().toString());
        intent.putExtra("Size",rg.getCheckedRadioButtonId());
        startActivity(intent);
        finish();
    }

}




