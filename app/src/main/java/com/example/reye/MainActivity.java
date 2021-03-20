package com.example.reye;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    int[] gamestate = {2,2,2,2,2,2,2,2,2};
    int[][] winningstate = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activeplayer=0;
    Boolean active=true;
    public void dropin(View view) {
        ImageView counter = (ImageView) view;

        int tagcounter = Integer.parseInt(counter.getTag().toString());
        if (gamestate[tagcounter] == 2 && active) {
            gamestate[tagcounter] = activeplayer;
            counter.setTranslationY(-1500);
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.yellowtrans);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activeplayer = 0;
            }

            counter.animate().translationYBy(1500).setDuration(300);
            for (int[] winningposition : winningstate) {
                if (gamestate[winningposition[0]] == gamestate[winningposition[1]] && gamestate[winningposition[1]] == gamestate[winningposition[2]] && gamestate[winningposition[0]] != 2) {
                    active = false;
                    String winner = "";
                    if (activeplayer == 0) {
                        winner = "Red";
                    } else {
                        winner = "Yellow";
                    }

                    Button playagainbutton = (Button) findViewById(R.id.playagainbutton);
                    TextView winnertextview = (TextView) findViewById(R.id.winnertextview);
                    winnertextview.setText("Yeah "+ winner+" has won!!");
                    playagainbutton.setVisibility(View.VISIBLE);
                    winnertextview.setVisibility(View.VISIBLE);

                }
            }

        }
    }

  public void playagain(View view){
            Button playagainbutton = (Button) findViewById(R.id.playagainbutton);
            TextView winnertextview = (TextView) findViewById(R.id.winnertextview);

            playagainbutton.setVisibility(View.INVISIBLE);
            winnertextview.setVisibility(View.INVISIBLE);
      androidx.gridlayout.widget.GridLayout gridlayout = findViewById(R.id.gridLayout);
            for(int i=0;i<gridlayout.getChildCount();i++){
                ImageView counter = (ImageView) gridlayout.getChildAt(i);
                counter.setImageDrawable(null);
            }
            for(int i=0;i<gamestate.length;i++){
                gamestate[i]=2;
            }
            activeplayer=0;
            active=true;
        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}