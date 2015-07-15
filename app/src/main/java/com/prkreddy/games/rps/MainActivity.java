package com.prkreddy.games.rps;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView selectedImage; // to load the image based on user actions (shake or button press)
    private Button nextButton; // alternative to Shake gesture
    private static final String TAG = "RPS "; // for logging purposes
    // minimum and maximum boundaries for the random number generator
    private static final int MIN = 1;
    private static final int MAX = 3;
    int[] imageResourceIds = new int[MAX+1];
    Random rand = new Random();

    // default constructor to initialize a few things
    public MainActivity()
    {
        // leave imageResourceIds[0] as empty to match MIN and MAX range
        imageResourceIds[1] = R.drawable.rps_rock;
        imageResourceIds[2] = R.drawable.rps_paper;
        imageResourceIds[3] = R.drawable.rps_scissors;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // inflates the view using activity_main.xml
        Log.i(TAG, "onCreate(...) called");

        // handle to widgets
        selectedImage = (ImageView) findViewById(R.id.RPS_image);
        nextButton = (Button) findViewById(R.id.next_button);

        // load the randomly chosen image on landing page
        loadRandomImage();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Next button is clicked");
                // Every time this button is clicked, chose the "thing" randomly
                loadRandomImage();
            }
        });
    }

    // get the image resource based on ID
    private Drawable getImageUsingResId(int resId)
    {
        Drawable drawable;
        if(android.os.Build.VERSION.SDK_INT >= 21){
            drawable = getResources().getDrawable(resId, getTheme());
        } else {
            drawable = getResources().getDrawable(resId);
        }
        return drawable;
    }
    // load the image based on random logic
    private void loadRandomImage()
    {
        int number = rand.nextInt(MAX - MIN + 1) + MIN;
        // load the randomly chosen image
        selectedImage.setImageDrawable(getImageUsingResId(imageResourceIds[number]));
    }

    // Activity life-cycle related methods
    @Override
    public void onStart() {
        super.onStart();
        Log.v(TAG, "onStart() called");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.v(TAG, "onResume() called");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.v(TAG, "onPause() called");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.v(TAG, "onStop() called");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "onDestroy() called");
    }

    // boiler plate code
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    // boiler plate code
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
