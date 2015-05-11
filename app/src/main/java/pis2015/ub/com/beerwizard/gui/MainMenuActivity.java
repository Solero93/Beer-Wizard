package pis2015.ub.com.beerwizard.gui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;

import pis2015.ub.com.beerwizard.R;

/**
 * Activity of the Main Menu
 */
public class MainMenuActivity extends Activity {
    Button btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        this.setTheme(R.style.mainMenuTheme);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.activity_main_menu);

        this.init_listvw_available_rooms(); // Show available rooms
        this.run_IntroActivity_at_launch(); // Run intro activity at launch

        // Initializes btn_about
        btnAbout = (Button) findViewById(R.id.btn_about);
        btnAbout.setWidth(25);
        btnAbout.setHeight(1);
        btnAbout.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.activity_main_menu_popup_about, null);


                final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setFocusable(true);
                Button btnDismiss = (Button) popupView.findViewById(R.id.dismiss);
                btnDismiss.setOnClickListener(new Button.OnClickListener() {
                    //                TextView wwa= (TextView)popupView.findViewById(R.id.)
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                //popupView.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), android.R.anim.slide_in_left));
                popupWindow.setAnimationStyle(R.style.popup_animation);
                popupWindow.showAtLocation(btnAbout, Gravity.CENTER, 0, 0);
            }
        });
    }

    /**
     * Initializes the available rooms' list
     * Fills the list with currently available rooms
     * and associates onClick event to entering the room you chose
     */
    public void init_listvw_available_rooms() {
        // Get ListView object from xml
        ListView listvw_available_rooms = (ListView) findViewById(R.id.listvw_available_rooms);
        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Fourth - the Array of data

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                GUIFacade.getAllGames());
        // Assign adapter to ListView
        listvw_available_rooms.setAdapter(arrayAdapter);

        listvw_available_rooms.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
                                    long id) {

/*                // We know the View is a TextView so we can cast it
                  // IMPORTANT TO REMEMBER THIS!!
                TextView clickedView = (TextView) view;
*/
                Intent intent = new Intent(view.getContext(), SpellsActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Runs IntroActivity at launch
     * and never again.
     */
    public void run_IntroActivity_at_launch() {
        SharedPreferences prefs = this.getSharedPreferences("appName", 0);
        SharedPreferences.Editor editor = prefs.edit();
        if (prefs.getBoolean("isInitialAppLaunch", true)) {
            editor.putBoolean("isInitialAppLaunch", false);
            editor.commit();
            Intent intent = new Intent(this, IntroActivity.class);
            startActivity(intent);
        }
    }

    // ActionListeners

    /**
     * OnClick ActionListener of btn_newGame
     * Enters to a new game, but before has to create a Room.
     */
    public void onClick_newGame(View vw) {
        Intent intent = new Intent(this, RoomCreateActivity.class);
        startActivity(intent);
    }

    /**
     * OnClick ActionListener of btn_profile
     * Lets edit your Profile
     */
    public void onClick_profile(View vw) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    /**
     * OnClick ActionListener of btn_tutorial
     * Opens the Tutorial
     */
    public void onClick_tutorial(View vw) {
        Intent intent = new Intent(this, TutorialActivity.class);
        startActivity(intent);
    }
}