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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;

import pis2015.ub.com.beerwizard.R;


public class CastSpellActivity extends ActionBarActivity {
    int idSpell;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        this.idSpell = b.getInt("id");
        setTitle(idSpell+"");
        setContentView(R.layout.activity_game_send_spell);
        ListView wizards = (ListView) findViewById(R.id.wizards);

        ArrayAdapter<String> wizardList = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                getResources().getStringArray(R.array.str_wizards_test_data));

        wizards.setAdapter(wizardList);
        wizards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onClick_Cast();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cast_spell, menu);
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

    public void onClick_Cast() {
        LayoutInflater layoutInflater
                = (LayoutInflater) getBaseContext()
                .getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = layoutInflater.inflate(R.layout.popup_sent_spell, null);
        final PopupWindow popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        Button btn_castSpell = (Button) popupView.findViewById(R.id.btn_cast_spell);
        btn_castSpell.setOnClickListener(new Button.OnClickListener() {
            //                TextView wwa= (TextView)popupView.findViewById(R.id.)
            @Override
            public void onClick(View v) {
                popupWindow.setAnimationStyle(R.style.popup_animation);
                popupWindow.dismiss();
                finish();
            }
        });
        Button btn_NO_castSpell = (Button) popupView.findViewById(R.id.btn_NO_cast_spell);
        btn_NO_castSpell.setOnClickListener(new Button.OnClickListener() {
            //                TextView wwa= (TextView)popupView.findViewById(R.id.)
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

            }
        });
        popupWindow.showAtLocation(findViewById(R.id.layout_cast_speell), Gravity.CENTER, 0, 0);


    }
}
