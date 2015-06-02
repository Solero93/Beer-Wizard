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

/**
 * Class which shows images to help new players understand how to play this app
 * BETA VERSION
 */
public class TutorialActivity extends Activity {
    private Button buttonNext;
    private Button buttonPrevious;
    private ImageSwitcher imageSwitcher;
    private Animation slide_in_left, slide_out_right;
    private int[] imageResources;
    private int curIndex;

    /**
     * Here we create an array with images
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        this.imageResources = new int[]{
                R.drawable.tutorial_1,
                R.drawable.tutorial_2,
                R.drawable.tutorial_3,
                R.drawable.tutorial_4,
        };

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_menu_tutorial);

        //Buttons to see next or previous image
        buttonNext = (Button) findViewById(R.id.btn_next);
        buttonPrevious = (Button) findViewById(R.id.btn_previous);
        //Object where images shown
        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        //animation between images
        slide_in_left = AnimationUtils.loadAnimation(this,
                android.R.anim.slide_in_left);
        slide_out_right = AnimationUtils.loadAnimation(this,
                android.R.anim.slide_out_right);

        imageSwitcher.setOutAnimation(slide_out_right);
        imageSwitcher.setInAnimation(slide_in_left);


        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            /**
             * Method to select the image and show it
             * @return
             */
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
        /**
         * Method to show the previous image, if is the first, this button does nothing
         */
        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (curIndex == 0) {
                } else {
                    imageSwitcher.setImageResource(imageResources[--curIndex]);
                }
            }
        });
        /**
         * Method to show the next image, if is the last, goes to the first element and ask player with a popup to go previous activity.
         */
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