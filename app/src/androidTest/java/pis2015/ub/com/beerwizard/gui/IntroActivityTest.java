package pis2015.ub.com.beerwizard.gui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import pis2015.ub.com.beerwizard.R;

/**
 * Activity of the Intro
 */
public class IntroActivityTest extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_intro);
    }

    /**
     * onClick ActionListener of the whole Activity
     * Returns to the menu by finishing the Activity since
     * MainMenuActivity opens this one.
     */
    public void onClick_enterMenu(View vw) {
        this.finish();
    }
}