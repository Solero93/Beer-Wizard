package pis2015.ub.com.beerwizard.gui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import pis2015.ub.com.beerwizard.R;
import pis2015.ub.com.beerwizard.game.SpellManager;

public class CastSpellActivity extends Activity {
    int idSpell, idUser;
    String textRule;
    boolean oRule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_spell);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        idSpell = b.getInt("spell");//id spell de 1 a
        idSpell = idSpell - 1;
        idUser = 0;
        textRule = "";

        //description and Quote
        TextView description = (TextView) findViewById(R.id.descriptionText);
        TextView title = (TextView) findViewById(R.id.titleText);
        TextView quote = (TextView) findViewById(R.id.quoteText);
        title.setText(GUIFacade.getSpellName(idSpell));
        description.setText(GUIFacade.getSpellDescription(idSpell));
        quote.setText(GUIFacade.getSpellQuote(idSpell));

    }

    //Event onClick
    public void onClickCast(View v) {
        oRule = false;
        //En cada caso hara una cosa
        if (idSpell == SpellManager.CAN_TO_THE_FACE) {//CAN TO THE FACE
            //SELECT USER

            initPopupUser(v);
        } else if (idSpell == SpellManager.WIZARD_DUEL) {//Duel
            oRule = true;
            initPopupUser(v);
        } else if (idSpell == SpellManager.BEEREKINESIS) {//beerkineesis

            initPopupUser(v);
        } else if (idSpell == SpellManager.SHIELD) {//Shild

            initPopupAccept(v);
        } else if (idSpell == SpellManager.CREATE_RULE) {//rule
            oRule = true;
            initPopupRule(v);

        } else if (idSpell == SpellManager.TRUTH_OR_SHOT) {//Truth
            oRule = true;
            initPopupUser(v);
        } else if (idSpell == SpellManager.HAT_OF_SHAME) {//Hat

            initPopupUser(v);
        } else if (idSpell == SpellManager.ALL_IN_BEER) {//all
            initPopupAccept(v);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cast_spell2, menu);
        return true;
    }

    public void initPopupUser(final View v) {
        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.activity_game_users, null);


        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        ListView listvw_user = (ListView) popupView.findViewById(R.id.listView_users);
        Button btCancel = (Button) popupView.findViewById(R.id.btn_cancel_user);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                GUIFacade.getAllUsers());
        // Assign adapter to ListView
        listvw_user.setAdapter(arrayAdapter);
        btCancel.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

            }
        });
        listvw_user.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
                                    long id) {

/*                // We know the View is a TextView so we can cast it
                  // IMPORTANT TO REMEMBER THIS!!
                TextView clickedView = (TextView) view;
*/
                idUser = position;
                popupWindow.dismiss();
                if (oRule) {
                    initPopupRule(v);
                } else {
                    initPopupAccept(v);
                }
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
        TextView titulo = (TextView) popupView.findViewById(R.id.textRule);
        switch (idSpell) {
            case SpellManager.WIZARD_DUEL:
                titulo.setText("Write the duel");
                break;
            case SpellManager.CREATE_RULE:
                titulo.setText("Write the rule");
                break;
            case SpellManager.TRUTH_OR_SHOT:
                titulo.setText("Write the truth");
                break;

        }
        Button btAcept = (Button) popupView.findViewById(R.id.buttonSetRule);
        final EditText ruleView = (EditText) popupView.findViewById(R.id.editTextRule);
        btAcept.setOnClickListener(new Button.OnClickListener() {
            //                TextView wwa= (TextView)popupView.findViewById(R.id.)
            @Override
            public void onClick(View v) {
                textRule = ruleView.getText().toString();
                popupWindow.dismiss();
                initPopupAccept(v);
            }
        });
        popupWindow.setAnimationStyle(R.style.popup_animation);
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

    }

    public void initPopupAccept(View v) {
        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.popup_sent_spell, null);


        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        TextView name = (TextView) popupView.findViewById(R.id.sent_spell_Name);
        TextView text = (TextView) popupView.findViewById(R.id.sent_spell_text);
        name.setText(getText(GUIFacade.getSpellName(idSpell)) + "");
        text.setText("Are you sure want to cast " + getText(GUIFacade.getSpellName(idSpell)) + "?");
        Button btOk = (Button) popupView.findViewById(R.id.btn_cast_spell);
        Button btNo = (Button) popupView.findViewById(R.id.btn_NO_cast_spell);
        btOk.setOnClickListener(new Button.OnClickListener() {
            //                TextView wwa= (TextView)popupView.findViewById(R.id.)
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                GUIFacade.castSpell(idUser, idSpell, textRule);
                finish();
            }
        });
        btNo.setOnClickListener(new Button.OnClickListener() {
            //                TextView wwa= (TextView)popupView.findViewById(R.id.)
            @Override
            public void onClick(View v) {
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
