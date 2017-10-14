package com.example.android.connect3;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements WinningDIalogFragment.NoticeDialogListener {

    int activePlayer = 0;
    //0=yellow, 1=red
    //2 means unplayed
    boolean gameover=false;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;


        int tappedCounter = gameState[Integer.parseInt(counter.getTag().toString())];
        if (tappedCounter == 2 && gameover==false) {
            counter.setTranslationY(-1000f);
            gameState[Integer.parseInt(counter.getTag().toString())] = activePlayer;
            if (activePlayer == 0) {
                activePlayer = 1;
                counter.setImageResource(R.drawable.yellow);
            } else {
                activePlayer = 0;
                counter.setImageResource(R.drawable.red);
            }
            counter.animate().translationYBy(1000f).rotation(3600).setDuration(600);

            Log.d("key","value");

            for(int[] winningPosition :winningPositions)
            {
                if(gameState[winningPosition[0]]==gameState[winningPosition[1]]
                   && gameState[winningPosition[1]]==gameState[winningPosition[2]]
                   && gameState[winningPosition[0]]!=2)
                {
                    Log.d("key1","true");
                    String winnerName ="Yellow";
                    if(gameState[winningPosition[0]]==1){
                        winnerName="Red";

                    }


                    WinningDIalogFragment dialog = WinningDIalogFragment.newInstance(winnerName);
                    dialog.setCancelable(false);
                    dialog.show(getFragmentManager(),"tag");
                    gameover=true;
                }else
                    Log.d("key2","false");
            }
            boolean complete=false;
            if(gameover==false){
                for(int i=0;i<gameState.length;i++)
                {
                    if(gameState[i]==2)
                    {
                        complete=false;
                        break;
                    }
                    else
                    {

                        complete=true;

                    }

                }
                if(complete==true) {

                    WinningDIalogFragment dialog = WinningDIalogFragment.newInstance("-2");
                    dialog.setCancelable(false);
                    dialog.show(getFragmentManager(),"tag1");
                }


            }
                    }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Log.d("positive btn clicked","dismissed");
        activePlayer=0;
        gameover=false;
        for(int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;

        }

        GridLayout grid = (GridLayout) findViewById(R.id.gridLayoutGame);
        for(int i=0;i<grid.getChildCount();i++)
        {
            ((ImageView)grid.getChildAt(i)).setImageResource(0);
        }
        dialog.dismiss();
    }
}
