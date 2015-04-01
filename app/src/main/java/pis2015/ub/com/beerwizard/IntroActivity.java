package pis2015.ub.com.beerwizard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class IntroActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
    }

    public void enterMenu(View vw) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}
