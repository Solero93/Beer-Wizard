package pis2015.ub.com.beerwizard.gui;

import android.app.Activity;
import android.graphics.drawable.Drawable;
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

/**
 * Activity where the wizar can cast his spells
 */
public class CastSpellActivity extends Activity {
    private int idSpell, idUser;
    private String textRule;
    private boolean oRule;

    //TODO Create class FullScreenActivity that inherits from Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_spell);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Bundle b = new Bundle(); //FIXME could be deleted
        b = getIntent().getExtras();
        idSpell = b.getInt("spell");//id spell de 1 a
        idUser = 0;
        textRule = "";

        //description and Quote
        //FIXME could be contracted to be done without declaration
        TextView description = (TextView) findViewById(R.id.descriptionText);
        TextView title = (TextView) findViewById(R.id.titleText);
        TextView quote = (TextView) findViewById(R.id.quoteText);
        title.setText(SpellManager.getSpellName(idSpell));
        description.setText(SpellManager.getSpellDescription(idSpell));
        quote.setText(SpellManager.getSpellQuote(idSpell));

    }

    /**
     * Redefine the Back button
     */
    public void onBackPressed() {
        setResult(-2, null);
        finish();
    }

    /**
     * Event onClick
     * Every spell has a different case with his popups
     */
    public void onClickCast(View v) {
        oRule = false;
        switch (idSpell) {
            case SpellManager.CAN_TO_THE_FACE:
                initPopupUser(v);
                break;
            case SpellManager.WIZARD_DUEL:
                oRule = true;
                initPopupUser(v);
                break;
            case SpellManager.BEEREKINESIS:
                initPopupUser(v);
                break;
            case SpellManager.SHIELD:
                initPopupAccept(v);
                break;
            case SpellManager.CREATE_RULE:
                oRule = true;
                initPopupRule(v);
                break;
            case SpellManager.TRUTH_OR_SHOT:
                oRule = true;
                initPopupUser(v);
                break;
            case SpellManager.HAT_OF_SHAME:
                initPopupUser(v);
                break;
            case SpellManager.ALL_IN_BEER:
                initPopupAccept(v);
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cast_spell2, menu);
        return true;
    }

    /**
     * POPUP where you can select an user to cast the spell
     */
    public void initPopupUser(final View v) {
        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.activity_game_users, null);

        //FIXME Should apply builder pattern to create popUps
        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        Drawable bg = getBaseContext().getResources().getDrawable(R.drawable.popup_border);
        popupWindow.setBackgroundDrawable(bg);
        popupWindow.setOutsideTouchable(true);
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
                TextView clickedView = (TextView) view; FIXME wtf ?!
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

    /**
     * POPUP where you can
     *
     * @param v
     */
    public void initPopupRule(View v) {
        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.activity_game_setrule, null);

        /*
        Popup shown when one played cast a spell and needs some feedback from player
         */
        //FIXME rename stuff here + builder pattern
        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        Drawable bg = getBaseContext().getResources().getDrawable(R.drawable.popup_border);
        popupWindow.setBackgroundDrawable(bg);
        popupWindow.setOutsideTouchable(true);
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

    /**
     * POPUP where you can accept
     *
     * @param v
     */
    public void initPopupAccept(View v) {
        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.popup_sent_spell, null);

        //FIXME goddamn builder pattern
        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        TextView name = (TextView) popupView.findViewById(R.id.sent_spell_Name);
        TextView text = (TextView) popupView.findViewById(R.id.sent_spell_text);
        name.setText(getText(SpellManager.getSpellName(idSpell)) + "");
        text.setText("Are you sure want to cast " + getText(SpellManager.getSpellName(idSpell)) + "?");
        Button btOk = (Button) popupView.findViewById(R.id.btn_cast_spell);
        Button btNo = (Button) popupView.findViewById(R.id.btn_NO_cast_spell);
        btOk.setOnClickListener(new Button.OnClickListener() {
            //                TextView wwa= (TextView)popupView.findViewById(R.id.)
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                GUIFacade.castSpell(idUser, idSpell, textRule);
                setResult(idSpell, null);
                finish();
            }
        });
        btNo.setOnClickListener(new Button.OnClickListener() {
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
