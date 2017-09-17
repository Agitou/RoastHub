package dankbox.roasthub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Roast extends AppCompatActivity {

    TextView player1;
    TextView player2;
    ProgressBar vsbar;
    Button left, right;
    int vote1 = 0;
    int vote2 = 0;
    double progress=0;
    int value = 50;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roast);
        player1 = (TextView) findViewById(R.id.playerName1);
        player2 = (TextView) findViewById(R.id.playerName2);
        vsbar = (ProgressBar) findViewById(R.id.progressBar);
        left = (Button) findViewById(R.id.buttonL);
        right = (Button) findViewById(R.id.buttonR);

    }

    public void pressLeft(View view){
        vote1++;
        player1.setText(Integer.toString(vote1));
        progress = (double)vote1/(vote1+vote2)*100;
        vsbar.setProgress((int)progress);
    }
    public void pressRight(View view){
        vote2++;
        player2.setText(Integer.toString(vote2));
        progress = (double)vote1/(vote1+vote2)*100;
        vsbar.setProgress((int)progress);
    }

}
