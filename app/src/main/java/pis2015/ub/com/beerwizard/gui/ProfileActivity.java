package pis2015.ub.com.beerwizard.gui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import pis2015.ub.com.beerwizard.R;
import pis2015.ub.com.beerwizard.util.Constants;


public class ProfileActivity extends Activity {
    private Button avatar;
    private int idAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_profile);
        Button btn_avatarChooser;
        final FrameLayout block = null;
        ((EditText) findViewById(R.id.profileName)).setText(GUIFacade.getUserName());
        idAvatar = GUIFacade.getUserAvatar();

        btn_avatarChooser = (Button) findViewById(R.id.avatarImage);
        btn_avatarChooser.setBackgroundResource(GUIFacade.getUserAvatar());
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
                popupWindow.setAnimationStyle(R.style.popup_animation);
                popupWindow.showAtLocation(findViewById(R.id.layout_activity_profile), Gravity.CENTER, 0, 0);

            }
        });
    }


    public void onBackPressed() {
        setResult(RESULT_OK, null);
        finish();
    }
    public void changeprofileimg(View v){
        switch (v.getId()){
            case R.id.img2:
                avatar =(Button)findViewById(R.id.avatarImage);
                avatar.setBackgroundResource(R.drawable.cara);
                idAvatar = R.drawable.cara;
                break;
            case R.id.img3:
                avatar =(Button)findViewById(R.id.avatarImage);
                avatar.setBackgroundResource(R.drawable.cara1);
                idAvatar = R.drawable.cara1;
                break;
            case R.id.img4:
                avatar =(Button)findViewById(R.id.avatarImage);
                avatar.setBackgroundResource(R.drawable.cara2);
                idAvatar = R.drawable.cara2;
                break;
            case R.id.img5:
                avatar =(Button)findViewById(R.id.avatarImage);
                avatar.setBackgroundResource(R.drawable.cara3);
                idAvatar = R.drawable.cara3;
                break;
            case R.id.img6:
                avatar =(Button)findViewById(R.id.avatarImage);
                avatar.setBackgroundResource(R.drawable.cara4);
                idAvatar = R.drawable.cara4;
                break;
            case R.id.img7:
                avatar =(Button)findViewById(R.id.avatarImage);
                avatar.setBackgroundResource(R.drawable.cara5);
                idAvatar = R.drawable.cara5;
                break;
            case R.id.img8:
                avatar =(Button)findViewById(R.id.avatarImage);
                avatar.setBackgroundResource(R.drawable.cara6);
                idAvatar = R.drawable.cara6;
                break;
            case R.id.img9:
                avatar =(Button)findViewById(R.id.avatarImage);
                avatar.setBackgroundResource(R.drawable.cara7);
                idAvatar = R.drawable.cara7;
                break;
        }
    }

    public void onClick_saveChanges(View view) {
        String name = ((EditText) findViewById(R.id.profileName)).getText().toString();
        SharedPreferences.Editor editor = getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, MODE_PRIVATE)
        .edit();
        editor.putString("name", name);
        editor.putInt("avatar", idAvatar);
        editor.commit();
        GUIFacade.modifyUserProfile(name, idAvatar);
        setResult(RESULT_OK, null);
        finish();
    //overridePendingTransition(R.anim.card_flip_left_in, R.anim.card_flip_left_out);
    }
}