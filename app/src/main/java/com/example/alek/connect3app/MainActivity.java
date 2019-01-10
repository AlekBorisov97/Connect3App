package com.example.alek.connect3app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int player = 0;
    boolean playable = true;
    int[] positions = { 2, 2, 2, 2, 2, 2, 2, 2, 2 };
    int[][] win_matrix = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void actionFunction(View view){
        if(playable) {
            ImageView img = (ImageView) view;
            if (positions[Integer.parseInt(img.getTag().toString())] == 2) {
                img.setTranslationY(-1000f);
                if (player == 0) {
                    img.setImageResource(R.drawable.yellow);
                    player = 1;
                    positions[Integer.parseInt(img.getTag().toString())] = 0;
                } else {
                    img.setImageResource(R.drawable.red);
                    player = 0;
                    positions[Integer.parseInt(img.getTag().toString())] = 1;
                }
                img.animate().translationYBy(1000f).setDuration(300);
            }
            checkGame();
        }
    }
    public void checkGame(){
        boolean isTie = true;
        String winnerName = "";
        for(int[] win_combo:win_matrix){
            if(positions[win_combo[0]] == positions[win_combo[1]]
                    && positions[win_combo[1]] == positions[win_combo[2]]
                    && positions[win_combo[0]] != 2){


                    if (positions[win_combo[0]] == 1) {
                        winnerName = "Red is the ";
                    } else {
                        winnerName = "Yellow is the ";
                    }
                    TextView txt = (TextView) findViewById(R.id.textView);
                    txt.setText(winnerName + "Winner!");
                    playable = false;
                    isTie = false;
                    LinearLayout layOut = (LinearLayout) findViewById(R.id.winnerLayout);
                    layOut.setVisibility(View.VISIBLE);
            }
        }
        if(isTie) {
            int numberOf2s = 0;
            for (int pos : positions) {
                if (pos != 2) {
                    numberOf2s++;
                }
            }
            if (numberOf2s == 9) {
                TextView txt = (TextView) findViewById(R.id.textView);
                txt.setText("DRAW");
                playable = false;
                LinearLayout layOut = (LinearLayout) findViewById(R.id.winnerLayout);
                layOut.setVisibility(View.VISIBLE);
            }
        }
    }

    public void playAgainFunction(View view){
        playable = true;
        LinearLayout layOut = (LinearLayout) findViewById(R.id.winnerLayout);
        layOut.setVisibility(View.INVISIBLE);
        GridLayout grLay = (GridLayout) findViewById(R.id.gridLay);
        for( int i = 0 ; i < grLay.getChildCount() ; i++){
            ((ImageView) grLay.getChildAt(i)).setImageResource(0);
        }
        for(int p = 0 ; p < 9 ; p++){
           positions[p]=2;
        }

    }

    public void exitApp(){
        finish();
        System.exit(0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
