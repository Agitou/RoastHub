package dankbox.roasthub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonRoast;
    Button buttonWatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pressedRoast(View view){
        Intent roastIntent = new Intent (this,Roast.class);
        startActivity(roastIntent);
    }
    public void pressedWatch(View view){

    }
}
