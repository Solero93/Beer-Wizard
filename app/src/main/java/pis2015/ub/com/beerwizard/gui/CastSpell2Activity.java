package pis2015.ub.com.beerwizard.gui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import pis2015.ub.com.beerwizard.R;

public class CastSpell2Activity extends ActionBarActivity {
    int idSpell, idUser;
    String textRule;
    boolean send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_spell2);
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        idSpell = b.getInt("spell");//id spell de 1 a 8
        send = false;

        //description and Quote
        TextView description = (TextView) findViewById(R.id.descriptionText);
        TextView title = (TextView) findViewById(R.id.titleText);
        TextView quote = (TextView) findViewById(R.id.quoteText);
        title.setText(GUIFacade.getSpellName(idSpell - 1));
        description.setText(GUIFacade.getSpellDescription(idSpell - 1));
        quote.setText(GUIFacade.getSpellQuote(idSpell - 1));
        getActionBar().hide();

    }

    //Event onClick
    public void onClickCast(View v) {

        //En cada caso hara una cosa
        if (idSpell == 1) {//CAN TO THE FACE
            //SELECT USER
            initPopupUser(v);
            initPopupAccept(v);
            if (send) {
                GUIFacade.castSpell(idUser, idSpell - 1, "");
            }
        } else if (idSpell == 2) {//Duel
            initPopupUser(v);
            initPopupAccept(v);
            if (send) {
                GUIFacade.castSpell(idUser, idSpell - 1, "");
            }
        } else if (idSpell == 3) {//beerkineesis
            initPopupUser(v);
            initPopupAccept(v);
            if (send) {
                GUIFacade.castSpell(idUser, idSpell - 1, "");
            }
        } else if (idSpell == 4) {//Shild
            initPopupAccept(v);
            if (send) {
                GUIFacade.castSpell(idUser, idSpell - 1, "");
            }
        } else if (idSpell == 5) {//rule
            initPopupRule(v);
            initPopupAccept(v);
            if (send) {
                GUIFacade.castSpell(idUser, idSpell - 1, textRule);
            }
        } else if (idSpell == 6) {//Truth
            initPopupUser(v);
            initPopupRule(v);
            initPopupAccept(v);
            if (send) {
                GUIFacade.castSpell(idUser, idSpell - 1, textRule);
            }
        } else if (idSpell == 7) {//Hat
            initPopupUser(v);
            initPopupUser(v);
            initPopupAccept(v);
            if (send) {
                GUIFacade.castSpell(idUser, idSpell - 1, textRule);
            }
        } else if (idSpell == 8) {//all
            initPopupAccept(v);
            if (send) {
                GUIFacade.castSpell(idUser, idSpell - 1, "");
            }
        }
        if (send) finish();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cast_spell2, menu);
        return true;
    }

    public void initPopupUser(View v) {
        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.activity_game_users, null);


        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        ListView listvw_user = (ListView) popupView.findViewById(R.id.listView_users);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                GUIFacade.getAllUsers());
        // Assign adapter to ListView
        listvw_user.setAdapter(arrayAdapter);

        listvw_user.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
                                    long id) {

/*                // We know the View is a TextView so we can cast it
                  // IMPORTANT TO REMEMBER THIS!!
                TextView clickedView = (TextView) view;
*/
                idUser = position;
                popupWindow.dismiss();
            }
        });
        popupWindow.setAnimationStyle(R.style.popup_animation);
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

    }


    public void initPopupRule(View v) {
        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.activity_game_setrule, null);


        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        Button btAcept = (Button) popupView.findViewById(R.id.buttonSetRule);
        final TextView ruleView = (TextView) popupView.findViewById(R.id.textRule);
        btAcept.setOnClickListener(new Button.OnClickListener() {
            //                TextView wwa= (TextView)popupView.findViewById(R.id.)
            @Override
            public void onClick(View v) {
                textRule = ruleView.getText().toString();
                popupWindow.dismiss();
            }
        });
        popupWindow.setAnimationStyle(R.style.popup_animation);
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

    }

    public void initPopupAccept(View v) {
        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.activity_game_send_spell, null);


        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        TextView name = (TextView) popupView.findViewById(R.id.sent_spell_Name);
        TextView text = (TextView) popupView.findViewById(R.id.sent_spell_text);
        name.setText(GUIFacade.getSpellName(idSpell) + "");
        text.setText("Are you sure want to cast " + GUIFacade.getSpellName(idSpell) + "?");
        Button btOk = (Button) popupView.findViewById(R.id.btn_cast_spell);
        Button btNo = (Button) popupView.findViewById(R.id.btn_NO_cast_spell);
        btOk.setOnClickListener(new Button.OnClickListener() {
            //                TextView wwa= (TextView)popupView.findViewById(R.id.)
            @Override
            public void onClick(View v) {
                send = true;
                popupWindow.dismiss();
            }
        });
        btNo.setOnClickListener(new Button.OnClickListener() {
            //                TextView wwa= (TextView)popupView.findViewById(R.id.)
            @Override
            public void onClick(View v) {
                send = false;
                popupWindow.dismiss();
            }
        });

        popupWindow.setAnimationStyle(R.style.popup_animation);
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

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
}
