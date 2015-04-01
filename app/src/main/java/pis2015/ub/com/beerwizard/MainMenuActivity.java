package pis2015.ub.com.beerwizard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;


public class MainMenuActivity extends Activity {
    public final static int tutorialActivity = 1;
    Button btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Get ListView object from xml
        ListView listView = (ListView) findViewById(R.id.listvw_available_rooms);
        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, getResources().getStringArray(R.array.str_array_available_rooms));


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        btnAbout = (Button) findViewById(R.id.btn_about);
        btnAbout.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                LayoutInflater layoutInflater
                        = (LayoutInflater) getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.popup_about, null);
                final PopupWindow popupWindow = new PopupWindow(
                        popupView,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                Button btnDismiss = (Button) popupView.findViewById(R.id.dismiss);
                btnDismiss.setOnClickListener(new Button.OnClickListener() {
                    //                TextView wwa= (TextView)popupView.findViewById(R.id.)
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                popupWindow.showAtLocation(btnAbout, Gravity.CENTER, 0, 0);

            }
        });

        Button btnTutorial = (Button) findViewById(R.id.btn_tutorial);
        btnTutorial.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, TutorialActivity.class);
                startActivityForResult(intent, tutorialActivity);
            }
        });
    }

    public void btnEvent_newGame(View vw) {
        Intent intent = new Intent(this, RoomCreateActivity.class);
        startActivity(intent);
    }

    public void btnEvent_profile(View vw) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void btnEvent_tutorial(View vw) {
        Intent intent = new Intent(this, TutorialActivity.class);
        startActivity(intent);
    }
}