package pis2015.ub.com.beerwizard;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import pis2015.ub.com.beerwizard.util.ImageAdapter;

/**
 * Created by jordi on 4/3/15.
 */
public class AvatarChooserActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_choose);
        GridView gridView = (GridView) findViewById(R.id.avatarChooser);
        gridView.setAdapter(new ImageAdapter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onClick_acceptAvatar(view);
            }
        });
    }

    public void onClick_acceptAvatar(View view) {
        finish();
    }
}

