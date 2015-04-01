package pis2015.ub.com.beerwizard;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 *
 */
public class IntroActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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