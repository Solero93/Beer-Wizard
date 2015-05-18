package pis2015.ub.com.beerwizard.gui;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ViewSwitcher;

import pis2015.ub.com.beerwizard.R;

public class TutorialActivity extends Activity {
    Button buttonNext;
    Button buttonPrevious;
    ImageSwitcher imageSwitcher;
    Animation slide_in_left, slide_out_right;

    int imageResources[] = {
            R.drawable.tutorial_1,
            R.drawable.tutorial_2,
            R.drawable.tutorial_3,
            R.drawable.tutorial_4,
    };

    int curIndex;

    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_menu_tutorial);
        //Typeface font = Typeface.createFromAsset(getAssets(), "fonts/fonts.ttf");
        //boto que desa el que hi ha escrit i torna a l'activity main

        buttonNext = (Button) findViewById(R.id.btn_next);
        buttonPrevious = (Button) findViewById(R.id.btn_previous);

        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);

        slide_in_left = AnimationUtils.loadAnimation(this,
                android.R.anim.slide_in_left);
        slide_out_right = AnimationUtils.loadAnimation(this,
                android.R.anim.slide_out_right);

        imageSwitcher.setOutAnimation(slide_out_right);
        imageSwitcher.setInAnimation(slide_in_left);


        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {


            @Override
            public View makeView() {

                ImageView imageView = new ImageView(TutorialActivity.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

                ViewGroup.LayoutParams params = new ImageSwitcher.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                imageView.setLayoutParams(params);
                return imageView;

            }
        });

        curIndex = 0;
        imageSwitcher.setImageResource(imageResources[curIndex]);

        buttonPrevious.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (curIndex == 0) {
                } else {
                    imageSwitcher.setImageResource(imageResources[--curIndex]);
                }
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (curIndex == imageResources.length - 1) {
                    LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    View popupView = layoutInflater.inflate(R.layout.activity_main_menu_tutorial_popup_ended, null);
                    final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);


                    popupWindow.setFocusable(true);
                    Drawable bg = getBaseContext().getResources().getDrawable(R.drawable.popup_border);
                    popupWindow.setBackgroundDrawable(bg);
                    popupWindow.setOutsideTouchable(true);
                    Button end_tutorial = (Button) popupView.findViewById(R.id.end_turorial);
                    end_tutorial.setOnClickListener(new Button.OnClickListener() {
                        //                TextView wwa= (TextView)popupView.findViewById(R.id.)
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                            finish();
                            //overridePendingTransition(R.anim.card_flip_left_in, R.anim.card_flip_left_out);
                        }
                    });
                    Button repeat_tutorial = (Button) popupView.findViewById(R.id.repeat_tutorial);
                    repeat_tutorial.setOnClickListener(new Button.OnClickListener() {
                        //                TextView wwa= (TextView)popupView.findViewById(R.id.)
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();

                            curIndex = 0;
                            imageSwitcher.setImageResource(imageResources[curIndex]);

                        }
                    });
                    popupWindow.setAnimationStyle(R.style.popup_animation);
                    popupWindow.showAtLocation(findViewById(R.id.layout_tutorial), Gravity.CENTER, 0, 0);


                } else {
                    imageSwitcher.setImageResource(imageResources[++curIndex]);
                }
            }
        });
    }

}