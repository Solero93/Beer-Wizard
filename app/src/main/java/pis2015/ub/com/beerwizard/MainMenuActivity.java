package pis2015.ub.com.beerwizard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;


public class MainMenuActivity extends Activity {
    Button btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        this.init_listvw_available_rooms();

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
    }

    public void init_listvw_available_rooms() {
        // Get ListView object from xml
        ListView listvw_available_rooms = (ListView) findViewById(R.id.listvw_available_rooms);
        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, getResources().getStringArray(R.array.str_array_available_rooms));

        // Assign adapter to ListView
        listvw_available_rooms.setAdapter(adapter);

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

    public void onClick_newGame(View vw) {
        Intent intent = new Intent(this, RoomCreateActivity.class);
        startActivity(intent);
    }

    public void onClick_profile(View vw) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void onClick_tutorial(View vw) {
        Intent intent = new Intent(this, TutorialActivity.class);
        startActivity(intent);
    }
}