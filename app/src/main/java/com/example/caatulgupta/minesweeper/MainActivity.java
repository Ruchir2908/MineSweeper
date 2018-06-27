package com.example.caatulgupta.minesweeper;

import android.content.Intent;
import android.graphics.Color;
import android.provider.FontRequest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout rootLayout;
    public int ROWS = 10;
    public int COLS = 10;
    public int NUM_MINES;
    public boolean currentStatus = true;
    public ArrayList<LinearLayout> rows;
    public MButton[][] board;
    public int r[];
    public int c[];
    public boolean firstClick;

    public static final int MINE = -1;
    public static final int BLANK = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        Toast.makeText(this,name,Toast.LENGTH_LONG).show();
        int size = intent.getIntExtra("Size",FrontActivity.r1.getId());
//        switch(size){
//            case FrontActivity.r2.getId(): ROWS = 12; COLS = 12; break;
//            case FrontActivity.r3.getId(): ROWS = 14; COLS = 14; break;
//            default: ROWS = 10; COLS = 10;
//        }
        if(size==FrontActivity.r1.getId()){
            ROWS = 10; COLS = 10;
        }else if(size== FrontActivity.r2.getId()){
            ROWS = 12; COLS = 12;
        }else if(size==FrontActivity.r3.getId()){
            ROWS = 14; COLS = 14;
        }
        rootLayout = findViewById(R.id.rootLayout);
        setUpBoard();
        firstClick = false;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch(id){
            case R.id.reset: setUpBoard(); break;
            case R.id.size10: ROWS = 10; COLS = 10; setUpBoard(); break;
            case R.id.size12: ROWS = 12; COLS = 12; setUpBoard(); break;
            case R.id.size14: ROWS = 14; COLS = 14; setUpBoard(); break;
            //case R.id.resetIcon: setUpBoard(); break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void createBoard(){
        Random rand = new Random();

        for(int i=0;i<NUM_MINES;i++){
            r[i] = rand.nextInt(ROWS-1);
            c[i] = rand.nextInt(COLS-1);
            //Log.i("Mine","Row: "+r[i]+" Col: "+c[i]);
        }
        for(int i=0;i<NUM_MINES;i++){
            //int r = rand.nextInt(ROWS);
            //for(int j=0;j<NUM_MINES;j++){
                //int c = rand.nextInt(COLS);
                board[r[i]][c[i]].setValue(MINE);
                board[r[i]][c[i]].setTextColor(Color.rgb(255,0,0));
                board[r[i]][c[i]].visited = true;
                board[r[i]][c[i]].blankVisited = true;
                //Log.i("Board","Value: "+ board[r[i]][c[i]].getValue());
            //}
        }
        for(int i=0;i<ROWS;i++){
            for(int j=0;j<COLS;j++){
                if(board[i][j].getValue()!=MINE){
                    if(i!=0){
                        if(j!=0){
                            if(board[i-1][j-1].getValue()==MINE){
                                int val = board[i][j].getValue();
                                val++;
                                board[i][j].setValue(val);
                            }
                        }
                        if(board[i-1][j].getValue()==MINE){
                            int val = board[i][j].getValue();
                            val++;
                            board[i][j].setValue(val);
                        }
                        if(j!=COLS-1){
                            if(board[i-1][j+1].getValue()==MINE){
                                int val = board[i][j].getValue();
                                val++;
                                board[i][j].setValue(val);
                            }
                        }
                    }
                    if(j!=0){
                        if(board[i][j-1].getValue()==MINE){
                            int val = board[i][j].getValue();
                            val++;
                            board[i][j].setValue(val);
                        }
                    }
                    if(board[i][j].getValue()==MINE){
                        int val = board[i][j].getValue();
                        val++;
                        board[i][j].setValue(val);
                    }
                    if(j!=COLS-1){
                        if( board[i][j+1].getValue()==MINE){
                            int val = board[i][j].getValue();
                            val++;
                            board[i][j].setValue(val);
                        }
                    }
                    if(i!=ROWS-1){
                        if(j!=0){
                            if(board[i+1][j-1].getValue()==MINE){
                                int val = board[i][j].getValue();
                                val++;
                                board[i][j].setValue(val);
                            }
                        }
                        if(board[i+1][j].getValue()==MINE){
                            int val = board[i][j].getValue();
                            val++;
                            board[i][j].setValue(val);
                        }
                        if(j!=COLS-1){
                            if( board[i+1][j+1].getValue()==MINE){
                                int val = board[i][j].getValue();
                                val++;
                                board[i][j].setValue(val);
                            }
                        }
                    }
//                    if(i!=0 && j!=COLS-1 && j!=0){
//                        if(board[i-1][j-1].getValue()==MINE || board[i-1][j].getValue()==MINE || board[i-1][j+1].getValue()==MINE){
//
//                        }
//                    }
//                    if(j!=COLS && j!=0){
//                        if(board[i][j-1].getValue()==MINE || board[i][j+1].getValue()==MINE || board[i+1][j-1].getValue()==MINE){
//
//                        }
//                    }
//                    if(i!=ROWS-1 && j!=0 && j!=COLS-1){
//                        if(board[i+1][j-1].getValue()==MINE || board[i+1][j].getValue()==MINE || board[i+1][j+1].getValue()==MINE){
//
//                        }
//                    }
//                    if(board[i-1][j-1].getValue()==MINE || board[i-1][j].getValue()==MINE || board[i-1][j+1].getValue()==MINE || board[i][j-1].getValue()==MINE || board[i][j+1].getValue()==MINE || board[i+1][j-1].getValue()==MINE || board[i+1][j].getValue()==MINE || board[i+1][j+1].getValue()==MINE){
//                        board[i][j].setValue(board[i][j].getValue()++);
//                    }
                }
            }
        }
    }

    public void setUpBoard(){

        NUM_MINES = (ROWS * COLS)/4;
        currentStatus = true;
        rows  = new ArrayList<>();
        board = new MButton[ROWS][COLS];
        r = new int[NUM_MINES];
        c = new int[NUM_MINES];
        rootLayout.removeAllViews();
        for(int i=0;i<ROWS;i++){
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1);
            linearLayout.setLayoutParams(layoutParams);
            rootLayout.addView(linearLayout);
            rows.add(linearLayout);
        }

        for(int i=0;i<ROWS;i++){
            for(int j=0;j<COLS;j++){
                final MButton button = new MButton(MainActivity.this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1);
                button.setLayoutParams(layoutParams);
                //button.setPadding(0,0,0,0);
                button.setBackground(getDrawable(R.drawable.button_bg));
                button.setOnClickListener(MainActivity.this);
                button.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        if(button.getText()=="F"){
                            //button.setEnabled(false);
                            //button.cancelLongPress();
                            button.setText(" ");
                            button.setBackground(getDrawable(R.drawable.button_bg));
                            button.setTextColor(Color.rgb(0,0,0));
                        }else{
                            //button.cancelLongPress();
                            //button.setEnabled(true);
                            button.setText("F");
                            button.setBackground(getDrawable(R.drawable.flag));
                            button.setTextColor(Color.rgb(255,0,0));

                        }

//                        button.flag();
                        return true;
                    }
                });
                LinearLayout row = rows.get(i);
                row.addView(button);
                board[i][j] = button;
                button.i = i;
                button.j = j;
            }
        }
        createBoard();
    }


    @Override
    public void onClick(View view) {
        if(currentStatus) {
            MButton button = (MButton) view;
            //int id = view.getId();
            if (button.getValue() == MINE) {
//                if(!firstClick){
//                    setUpBoard();
//                    firstClick = true;
//                }
                Toast.makeText(this, "GAME OVER !!", Toast.LENGTH_LONG).show();
                currentStatus = false;
                showBoard();
                return;
            }
            if(button.getText()=="F"){
                return;
            }
            button.setTextColor(Color.rgb(0,0,0));
            //button.setText(toString().valueOf(button.getValue()));
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
//                    if (id == board[i][j].getId()) {
                        //Log.i("board", "i: " + id + " j: " + board[i][j].getId());
                        reveal(button,button.i,button.j);
                        button.visited = true;
                        if(!checkGame()){
                            Toast.makeText(this, "YOU WON !!", Toast.LENGTH_LONG).show();
                            currentStatus = false;
                            showBoard();
                            return;
                        }
                        //Log.i("board", "i: " + i + " j: " + j);
//                        break;
//                    }
                }
            }
        }
        return;
    }

    private boolean checkGame() {
        boolean flag = false;
        for(int i=0;i<ROWS;i++){
            for(int j=0;j<COLS;j++){
                if(!board[i][j].visited){
                    flag = true;
                    break;
                }
            }
            if(flag){
                break;
            }
        }
        return flag;
    }

    private void showBoard(){

        for(int i=0;i<ROWS;i++){
            for(int j=0;j<COLS;j++){
                if(board[i][j].getText()=="F" && board[i][j].getValue()!=-1){
                    board[i][j].setBackground(getDrawable(R.drawable.flag_button_bg));
                    board[i][j].setTextColor(Color.rgb(0,0,0));
                }else if(board[i][j].getText()=="F" && board[i][j].getValue()==-1){
                    board[i][j].setBackground(getDrawable(R.drawable.flag));
                }else if(board[i][j].getValue()==-1 && board[i][j].getText()!="F"){
                    board[i][j].setBackground(getDrawable(R.drawable.mine1));
                }else{
                    board[i][j].setBackground(getDrawable(R.drawable.button_bg));
                }

                board[i][j].setText(toString().valueOf(board[i][j].getValue()));
            }
        }
    }

    private void reveal(MButton button, int i, int j) {
//        if(i==-1 || j==-1 || i==ROWS || j==COLS || button.blankVisited){
//            Log.i("Blank in 1st if","i: "+i+" j: "+j);
//            return;
//        }
//        if(button.getValue()!=MINE) {
        button.setBackground(getDrawable(R.drawable.reveal_button_bg));

            if(button.blankVisited){
                return;
            }

            button.blankVisited = true;
            if (button.getValue() == BLANK) {
                if (i + 1 < ROWS) {
                    reveal(board[i + 1][j], i + 1, j);
                    if (j + 1 < COLS) {
                        reveal(board[i+1][j + 1], i+1, j + 1);
                    }
                }
                if (i - 1 >= 0) {
                    reveal(board[i - 1][j], i - 1, j);
                    if (j - 1 >= 0) {
                        reveal(board[i-1][j - 1], i-1, j - 1);
                    }
                }
                if (j + 1 < COLS) {
                    reveal(board[i][j + 1], i, j + 1);
                    if(i-1>=0){
                        reveal(board[i - 1][j+1], i - 1, j+1);
                    }
                }
                if (j - 1 >= 0) {
                    reveal(board[i][j - 1], i, j - 1);
                    if(i+1<ROWS){
                        reveal(board[i + 1][j-1], i + 1, j-1);
                    }
                }

            }
            else/*(button.getValue() != BLANK)*/ {
                button.setText(toString().valueOf(button.getValue()));
                //Log.i("Blank in 2nd if", "i: " + i + " j: " + j);
                button.blankVisited = true;
                return;
            }
            button.setText(toString().valueOf(button.getValue()));
            //Log.i("Blank main", "i: " + i + " j: " + j);



//        }
        //return;
//        reveal(board[i-1][j-1],i-1,j-1);
//        reveal(board[i+1][j+1],i+1,j+1);
//        reveal(board[i-1][j+1],i-1,j+1);
//        reveal(board[i+1][j-1],i+1,j-1);
//        reveal(board[i-1][j],i-1,j);
//        reveal(board[i+1][j],i+1,j);
//        reveal(board[i][j+1],i,j+1);
//        reveal(board[i][j-1],i,j-1);

    }
}
