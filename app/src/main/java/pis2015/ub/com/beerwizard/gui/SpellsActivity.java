package pis2015.ub.com.beerwizard.gui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import pis2015.ub.com.beerwizard.R;
import pis2015.ub.com.beerwizard.network.Constants;
import pis2015.ub.com.beerwizard.network.GameData;

/*
The activity where you can select a spell.
 */
public class SpellsActivity extends ActionBarActivity {
    //temporal, cuando tengan los metodos lvl hechos
    int[] tText= new int[]{R.id.textViewSpell1, R.id.textViewSpell2, R.id.textViewSpell3, R.id.textViewSpell4, R.id.textViewSpell5, R.id.textViewSpell6, R.id.textViewSpell7, R.id.textViewSpell8};
    int[] tImage = new int[]{R.id.imageViewSpell1, R.id.imageViewSpell2, R.id.imageViewSpell3, R.id.imageViewSpell4, R.id.imageViewSpell5, R.id.imageViewSpell6, R.id.imageViewSpell7, R.id.imageViewSpell8};
    String edited;
    TextView changetext2;
    String edit;
    public Handler spellsHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message inputMessage) {
            switch (inputMessage.what) {
                case Constants.MSG_DECIDE_LEVEL:
                    final String targetUser1 = (String) inputMessage.obj;
                    /**
                     * Here we create a popup using a LayoutInflater to allow players to lvl up
                     * sending a request to another player, this player can accept the level up
                     * or deny it
                     */
                    //Here we create the layout inflater
                    LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    //Here we associate the layout inflater with the layout we need, in this case popup_lvlup
                    View popupView = layoutInflater.inflate(R.layout.popup_lvlup, null);
                    //Now we create the popup window
                    final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    //Time to only interact with the popup
                    popupWindow.setFocusable(true);

                    //Now we change the text on the TextView to show WHO wants to level up
                    final String who_lvl = GUIFacade.getUserName(targetUser1);
                    String texto = getResources().getString(R.string.level_up_popup_name);
                    String strMeatMsg = String.format(texto, who_lvl);
                    TextView changetext = (TextView) popupView.findViewById(R.id.name_lvl);
                    changetext.setText(strMeatMsg);

                    //Now we declarate the 2 possible buttons, player can level ul, or player cannot level up
                    //this button increases the player level in 1
                    Button btnlvlup = (Button) popupView.findViewById(R.id.btn_lvlup);
                    btnlvlup.setOnClickListener(new Button.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            GUIFacade.levelUp(targetUser1);
                            popupWindow.dismiss();
                        }
                    });
                    // If player tries to fake a level up, the player who receives this popup can dismiss his intent to level up
                    Button btn_NO_lvlup = (Button) popupView.findViewById(R.id.btn_NO_lvlup);
                    btn_NO_lvlup.setOnClickListener(new Button.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            popupWindow.dismiss();
                        }
                    });

                    //We set the animation to show the popup
                    popupWindow.setAnimationStyle(R.style.popup_animation);
                    //Here we show the position where will appear the popup
                    popupWindow.showAtLocation(findViewById(R.id.handle), Gravity.CENTER, 0, 0);

                    /* TODO Alberto
                        - Crear el popUp de decidir lvlUp (lo tienes por allí en layouts)
                        - Implementar botones
                            - YES => llamar GUIFacade.levelUp(targetUser, true)
                            - NO => llamar GUIFacade.levelUp(targetUser, false)
                     */

                    break;
                case Constants.MSG_LEVEL_UP:
                    lvlUp();
                    break;

                case Constants.MSG_LEVEL_DOWN:
                    lvlDown();
                    break;
                case Constants.MSG_CASTED_SPELL:

                    // Needs to be taken out from Message - just default value
                    int idSpell = 0;
                    String targetUser = "idUser";

                    String descr = getResources().getString(GUIFacade.getSpellDescription(idSpell));

                    // idSpell needs to changed to targetUser
                    String name = GUIFacade.getUserName(targetUser);

                    String spellName = getResources().getString(GUIFacade.getSpellName(idSpell));

                    /**
                     * Here we create a popup using a LayoutInflater to allow players to lvl up
                     * sending a request to another player, this player can accept the level up
                     * or deny it
                     */
                    //Here we create the layout inflater
                    LayoutInflater layoutInflater2 = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    //Here we associate the layout inflater with the layout we need, in this case popup_lvlup
                    View popupView2 = layoutInflater2.inflate(R.layout.popup_received_spell, null);
                    //Now we create the popup window
                    final PopupWindow popupWindow2 = new PopupWindow(popupView2, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    //Time to only interact with the popup
                    popupWindow2.setFocusable(true);

                    //here we edit the popup to show what spell the casted
                    switch (idSpell) {
                        case 0: // Can to the face
                            descr = getResources().getString(R.string.short_desc_can);
                            name = GUIFacade.getUserName(targetUser);
                            spellName = getResources().getString(GUIFacade.getSpellName(idSpell));

                            edit = getResources().getString(R.string.popup_received_spell_user_spell);
                            changetext2 = (TextView) popupView2.findViewById(R.id.name_spell);
                            edited = String.format(edit, name, spellName);
                            changetext2.setText(edited);

                            edit = getResources().getString(R.string.popup_received_spell_descr);
                            changetext2 = (TextView) popupView2.findViewById(R.id.order);
                            edited = String.format(edit, descr);
                            changetext2.setText(edited);

                            break;

                        case 1: // Wizard Duel
                            descr = getResources().getString(R.string.short_desc_duel);
                            name = GUIFacade.getUserName(targetUser);
                            spellName = getResources().getString(GUIFacade.getSpellName(idSpell));

                            edit = getResources().getString(R.string.popup_received_spell_user_spell);
                            changetext2 = (TextView) popupView2.findViewById(R.id.name_spell);
                            edited = String.format(edit, name, spellName);
                            changetext2.setText(edited);

                            edit = getResources().getString(R.string.popup_received_spell_descr);
                            changetext2 = (TextView) popupView2.findViewById(R.id.order);
                            edited = String.format(edit, descr);
                            changetext2.setText(edited);
                            break;

                        case 2: // Beerekinesis
                            descr = getResources().getString(R.string.short_desc_beerk);
                            name = GUIFacade.getUserName(targetUser);
                            spellName = getResources().getString(GUIFacade.getSpellName(2));

                            edit = getResources().getString(R.string.popup_received_spell_user_spell);
                            changetext2 = (TextView) popupView2.findViewById(R.id.name_spell);
                            edited = String.format(edit, name, spellName);
                            changetext2.setText(edited);

                            edit = getResources().getString(R.string.popup_received_spell_descr);
                            changetext2 = (TextView) popupView2.findViewById(R.id.order);
                            edited = String.format(edit, descr);
                            changetext2.setText(edited);
                            break;

                        case 6: // Truth
                            descr = getResources().getString(R.string.short_desc_truth);
                            name = GUIFacade.getUserName(targetUser);
                            spellName = getResources().getString(GUIFacade.getSpellName(idSpell));

                            edit = getResources().getString(R.string.popup_received_spell_user_spell);
                            changetext2 = (TextView) popupView2.findViewById(R.id.name_spell);
                            edited = String.format(edit, name, spellName);
                            changetext2.setText(edited);

                            edit = getResources().getString(R.string.popup_received_spell_descr);
                            changetext2 = (TextView) popupView2.findViewById(R.id.order);
                            edited = String.format(edit, descr);
                            changetext2.setText(edited);
                            break;

                        case 7: // Hat of Shame
                            descr = getResources().getString(R.string.short_desc_hat);
                            name = GUIFacade.getUserName(targetUser);
                            spellName = getResources().getString(GUIFacade.getSpellName(idSpell));

                            edit = getResources().getString(R.string.popup_received_spell_user_spell);
                            changetext2 = (TextView) popupView2.findViewById(R.id.name_spell);
                            edited = String.format(edit, name, spellName);
                            changetext2.setText(edited);

                            edit = getResources().getString(R.string.popup_received_spell_descr);
                            changetext2 = (TextView) popupView2.findViewById(R.id.order);
                            edited = String.format(edit, descr);
                            changetext2.setText(edited);
                            break;

                        case 8: // All in Shot
                            descr = getResources().getString(R.string.short_desc_all);
                            name = GUIFacade.getUserName(targetUser);
                            spellName = getResources().getString(GUIFacade.getSpellName(idSpell));

                            edit = getResources().getString(R.string.popup_received_spell_user_spell);
                            changetext2 = (TextView) popupView2.findViewById(R.id.name_spell);
                            edited = String.format(edit, name, spellName);
                            changetext2.setText(edited);

                            edit = getResources().getString(R.string.popup_received_spell_descr);
                            changetext2 = (TextView) popupView2.findViewById(R.id.order);
                            edited = String.format(edit, descr);
                            changetext2.setText(edited);
                            break;
                    }

                    Button got = (Button) popupView2.findViewById(R.id.btn_got);
                    got.setOnClickListener(new Button.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            popupWindow2.dismiss();
                        }
                    });

                    //We set the animation to show the popup
                    popupWindow2.setAnimationStyle(R.style.popup_animation);
                    //Here we show the position where will appear the popup
                    popupWindow2.showAtLocation(findViewById(R.id.button2), Gravity.CENTER, 0, 0);

                    /* TODO Alberto
                        - Crear el popUp de cuándo alguien te lanzó hechizo (lo tienes por allí en layouts)
                        - Ten en cuenta que hay cambios en función del hechizo
                            - Por ejemplo,
                     */

                    break;
            }
        }
    };
    private int lvl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main_screen);
        getSupportActionBar().setIcon(R.drawable.cara);//change the icon, avatar
        //setTitle("nick");//change the Nickname
        lvl=1;
        GameData.setSpellsActivityHandler(this.spellsHandler); // Assigns Handler to GameData
    }

    /*
    Action bar constructor
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_spells, menu);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        return super.onCreateOptionsMenu(menu);

    }

    /*
    event of the spells
     */
    public void onClickSpell(View v) {
        int id = v.getId();
        if((id == R.id.spell1 ) && (this.lvl>=2)){
            Intent intent = new Intent(this, CastSpellActivity.class);
            intent.putExtra("spell", 1); //Your id
            startActivity(intent);
        }if((id == R.id.spell2)&& (this.lvl>=3)){
            Intent intent = new Intent(this, CastSpellActivity.class);
            intent.putExtra("spell", 2); //Your id
            startActivity(intent);
        }if((id == R.id.spell3) && (this.lvl>=4)){
            Intent intent = new Intent(this, CastSpellActivity.class);
            intent.putExtra("spell", 3); //Your id
            startActivity(intent);
        }if((id == R.id.spell4) && (this.lvl>=5)){
            Intent intent = new Intent(this, CastSpellActivity.class);
            intent.putExtra("spell", 4); //Your id
            startActivity(intent);
        }if((id == R.id.spell5) && (this.lvl>=6)){
            Intent intent = new Intent(this, CastSpellActivity.class);
            intent.putExtra("spell", 5); //Your id
            startActivity(intent);
        }if((id == R.id.spell6) && (this.lvl>=7)){
            Intent intent = new Intent(this, CastSpellActivity.class);
            intent.putExtra("spell", 6); //Your id
            startActivity(intent);
        }if((id == R.id.spell7) && (this.lvl>=8)){
            Intent intent = new Intent(this, CastSpellActivity.class);
            intent.putExtra("spell", 7); //Your id
            startActivity(intent);
        }if((id == R.id.spell8) && (this.lvl>=9)){
            Intent intent = new Intent(this, CastSpellActivity.class);
            intent.putExtra("spell", 8); //Your id
            startActivity(intent);
        }
    }

    public void lvlUp() {

        if (lvl < 9) {
            lvl++;
            TextView textLvl = (TextView) findViewById(R.id.textLvl);
            ImageView image = (ImageView) findViewById(tImage[lvl - 2]);
            TextView text = (TextView) findViewById(tText[lvl - 2]);
            textLvl.setText("Level " + lvl);
            text.setText(GUIFacade.getSpellName(lvl - 2));
            image.setImageResource(R.drawable.duel_of_wizards);
        }
    }

    public void lvlDown() {

        if (lvl > 1) {
            lvl--;
            TextView textLvl = (TextView) findViewById(R.id.textLvl);
            ImageView image = (ImageView) findViewById(tImage[lvl - 1]);
            TextView text = (TextView) findViewById(tText[lvl - 1]);
            textLvl.setText("Level " + lvl);
            text.setText(GUIFacade.getSpellLockedText(lvl - 1));
            image.setImageResource(R.drawable.candado);
        }
    }

    public void masterUp() {
        TextView textLvl = (TextView) findViewById(R.id.textLvl);
        textLvl.setText("MASTER");
    }

    public void masterDown() {
        TextView textLvl = (TextView) findViewById(R.id.textLvl);
        textLvl.setText("Level " + lvl);
    }

    /*
    options and settings
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //PROILE
        if (id == R.id.action_lvl_up) {
            GUIFacade.levelUp();

        }
        if (id == R.id.action_profile) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
            return true;
        }

        //TUTORIAL
        if (id == R.id.action_tutorial) {
            Intent intent = new Intent(this, TutorialActivity.class);
            startActivity(intent);
            return true;
        }

        //Exit to Menu
        if (id == R.id.action_exit) {
            GUIFacade.exitGame(this);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}