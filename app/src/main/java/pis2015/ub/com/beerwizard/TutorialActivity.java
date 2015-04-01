package pis2015.ub.com.beerwizard;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class TutorialActivity extends Activity {
    Button buttonNext;
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
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tutorial);
//        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/fonts.ttf");
        //boto que desa el que hi ha escrit i torna a l'activity main
        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Button edt = (Button) findViewById(R.id.button);
                    Float resultok = Float.valueOf(edt.getText().toString());
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("OK", resultok);
                    setResult(RESULT_OK, returnIntent);
                    finish();
                    //Si no han introduit res, es queixa
                } catch (java.lang.NumberFormatException exception) {
                    Context context = getApplicationContext();

                    Toast.makeText(
                            context, "Error, introdueix les taxes",
                            Toast.LENGTH_LONG)
                            .show();

                }
            }
        });

        buttonNext = (Button) findViewById(R.id.btn);
//        buttonNext.setTypeface(font);
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

        buttonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (curIndex == imageResources.length - 1) {

                    curIndex = 0;
                    imageSwitcher.setImageResource(imageResources[curIndex]);

                } else {
                    imageSwitcher.setImageResource(imageResources[++curIndex]);
                }
            }
        });
    }

}
