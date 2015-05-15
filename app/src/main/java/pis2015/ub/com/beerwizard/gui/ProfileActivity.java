package pis2015.ub.com.beerwizard.gui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import pis2015.ub.com.beerwizard.R;


public class ProfileActivity extends ActionBarActivity {

    public static void enableDisableViewGroup(ViewGroup viewGroup, boolean enabled) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = viewGroup.getChildAt(i);
            view.setEnabled(enabled);
            if (view instanceof ViewGroup) {
                enableDisableViewGroup((ViewGroup) view, enabled);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_profile);
        Button btn_avatarChooser;
        final FrameLayout block = null;
        btn_avatarChooser = (Button) findViewById(R.id.avatarImage);
        btn_avatarChooser.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.activity_main_menu_profile_change_avatar_popup, null);
                final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setFocusable(true);
                Button dismiss_popup = (Button) popupView.findViewById(R.id.btn_back);
                dismiss_popup.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                Button btn_back;
                btn_back = (Button) findViewById(R.id.btn_back);
                btn_back.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                popupWindow.setAnimationStyle(R.style.popup_animation);
                popupWindow.showAtLocation(findViewById(R.id.layout_activity_profile), Gravity.CENTER, 0, 0);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

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

    public void onClick_saveChanges(View view) {
        //String name = ((EditText) findViewById(R.id.editText2)).getText().toString();
        //GUIFacade.modifyUserProfile(name, -1);
        finish();
    }
}

